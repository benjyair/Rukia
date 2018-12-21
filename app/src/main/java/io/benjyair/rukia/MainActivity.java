package io.benjyair.rukia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TimeListener, View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    private static final SimpleDateFormat SDF_DATE =
            new SimpleDateFormat("yyyy年MM月dd日  E  a", Locale.CHINA);

    private static final int WHAT_TIME = 0x01;
    private static final int WHAT_WEATHER = 0x02;

    private final HttpUtil httpUtil = new HttpUtil();
    private final Gson gson = new GsonBuilder().create();
    private final TimeHandler timeHandler = new TimeHandler();

    private SharePreferencesUtil preferencesUtil;
    private boolean pauseFlag = false;

    private TextView tv_setting;

    private TextView ftv_date;
    private TextView ftv_time;

    private TextView ftv_weather;
    private TextView ftv_notice;
    private TextView tv_update_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        preferencesUtil = new SharePreferencesUtil(this);

        tv_setting = findViewById(R.id.tv_setting);
        tv_setting.setText(preferencesUtil.getCurrentCityName());
        tv_setting.setOnClickListener(this);

        tv_update_time = findViewById(R.id.tv_update_time);
        tv_update_time.setOnClickListener(this);

        ftv_date = findViewById(R.id.tv_date);
        ftv_time = findViewById(R.id.tv_time);

        ftv_weather = findViewById(R.id.tv_weather);
        ftv_notice = findViewById(R.id.tv_notice);
        tv_update_time = findViewById(R.id.tv_update_time);

        timeHandler.setTimerListener(this);
        timeHandler.addMessage(WHAT_TIME, 1000L);
        timeHandler.addMessage(WHAT_WEATHER, 60 * 60 * 1000L);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume()");
        super.onResume();
        if (pauseFlag) {
            pauseFlag = false;
            timeHandler.onResume();
        }
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause()");
        super.onPause();
        pauseFlag = true;
        timeHandler.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting:
                startActivityForResult(new Intent(this, CityActivity.class), 0x01);
                break;
            case R.id.tv_update_time:
                refreshWeather();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshWeather();
    }

    @Override
    public boolean onUpdate(int what) {
        Log.d(TAG, "onUpdate() called with: " + "what = [" + what + "]");
        switch (what) {
            case WHAT_TIME:
                ftv_time.setText(SDF_TIME.format(new Date(System.currentTimeMillis())));
                ftv_date.setText(SDF_DATE.format(new Date(System.currentTimeMillis())));
                break;
            case WHAT_WEATHER:
                getWeather(preferencesUtil.getCurrentCityCode());
                break;
        }
        return true;
    }

    private void refreshWeather() {
        timeHandler.removeMessage(WHAT_WEATHER);
        timeHandler.addMessage(WHAT_WEATHER, 60 * 60 * 1000L);
    }

    private void getWeather(final String cityCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String apiUrl = String.format(
                            "http://t.weather.sojson.com/api/weather/city/%s", cityCode);
                    String result = httpUtil.get(apiUrl);
                    if (TextUtils.isEmpty(result)) {
                        Log.i(TAG, "No weather data");
                        return;
                    }
                    final Weather weather = gson.fromJson(result, Weather.class);
                    if (weather == null || weather.getStatus() != 200) {
                        Log.i(TAG, "No weather data");
                        return;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Weather.DataBean.ForecastBean today =
                                    weather.getData().getForecast().get(0);
                            String weatherStr = String.format(
                                    "天气[%s]，温度[%s℃]，湿度[%s]，%s，%s，%s%s。",
                                    today.getType(),
                                    weather.getData().getWendu(),
                                    weather.getData().getShidu(),
                                    today.getHigh(),
                                    today.getLow(),
                                    today.getFx(), today.getFl());
                            ftv_weather.setText(weatherStr);

                            String noticeStr = String.format(
                                    "空气质量：%s[%s]，%s。",
                                    weather.getData().getQuality(),
                                    today.getAqi(),
                                    today.getNotice());
                            ftv_notice.setText(noticeStr);

                            String time = SDF_TIME.format(new Date(System.currentTimeMillis()));
                            tv_update_time.setText(String.format("%s 更新", time));

                            tv_setting.setText(preferencesUtil.getCurrentCityName());
                        }
                    });
                } catch (Exception e) {
                    Log.i(TAG, "No weather data");
                }
            }
        }).start();
    }

}
