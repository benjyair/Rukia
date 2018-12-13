package io.benjyair.rukia;

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

public class MainActivity extends AppCompatActivity implements TimeListener {

    private static final String TAG = "MainActivity";

    private static final int WHAT_TIME = 0x01;
    private static final int WHAT_WEATHER = 0x02;

    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    private static final SimpleDateFormat SDF_DATE =
            new SimpleDateFormat("yyyy年MM月dd日  E  a", Locale.CHINA);

    private TextView ftv_date;
    private TextView ftv_time;
    private TextView ftv_weather;
    private TextView ftv_notice;
    private TimeHandler timeHandler = new TimeHandler();
    private final HttpUtil httpUtil = new HttpUtil();
    private Gson gson = new GsonBuilder().create();
    private boolean pauseFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ftv_date = findViewById(R.id.ftv_date);
        ftv_time = findViewById(R.id.ftv_time);
        ftv_weather = findViewById(R.id.ftv_weather);
        ftv_notice = findViewById(R.id.ftv_notice);

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
    public boolean onUpdate(int what) {
        Log.d(TAG, "onUpdate() called with: " + "what = [" + what + "]");
        switch (what) {
            case WHAT_TIME:
                ftv_time.setText(SDF_TIME.format(new Date(System.currentTimeMillis())));
                ftv_date.setText(SDF_DATE.format(new Date(System.currentTimeMillis())));
                break;
            case WHAT_WEATHER:
                getWeather("101010300" /* Beijing */);
                break;
        }
        return true;
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
                        switchWeather(false);
                        return;
                    }
                    final Weather weather = gson.fromJson(result, Weather.class);
                    if (weather == null || weather.getStatus() != 200) {
                        switchWeather(false);
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
                            switchWeather(true);
                        }
                    });
                } catch (Exception e) {
                    switchWeather(false);
                }
            }
        }).start();
    }


    private void switchWeather(boolean status) {
        ftv_weather.setVisibility(status ? View.VISIBLE : View.GONE);
        ftv_notice.setVisibility(status ? View.VISIBLE : View.GONE);
    }

}
