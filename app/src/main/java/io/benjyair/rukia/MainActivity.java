package io.benjyair.rukia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hanks.htextview.fall.FallTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TimeListener {

    private static final String TAG = "MainActivity";

    private static final int WHAT_TIME = 0x01;
    private static final int WHAT_WEATHER = 0x02;
    private static final int WHAT_LUNAR = 0x03;

    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    private static final SimpleDateFormat SDF_DATE =
            new SimpleDateFormat("yyyy年MM月dd日  E  a", Locale.CHINA);

    private FallTextView ftv_date;
    private FallTextView ftv_lunar;
    private FallTextView ftv_suit;
    private FallTextView ftv_taboo;
    private FallTextView ftv_time;
    private FallTextView ftv_weather;
    private FallTextView ftv_notice;
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
        ftv_lunar = findViewById(R.id.ftv_lunar);
        ftv_suit = findViewById(R.id.ftv_suit);
        ftv_taboo = findViewById(R.id.ftv_taboo);
        ftv_time = findViewById(R.id.ftv_time);
        ftv_weather = findViewById(R.id.ftv_weather);
        ftv_notice = findViewById(R.id.ftv_notice);

        timeHandler.setTimerListener(this);
        timeHandler.addMessage(WHAT_TIME, 1000L);
        timeHandler.addMessage(WHAT_WEATHER, 60 * 60 * 1000L);
        timeHandler.addMessage(WHAT_LUNAR, 60 * 60 * 1000L);
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
                ftv_time.animateText(SDF_TIME.format(new Date(System.currentTimeMillis())));
                ftv_date.animateText(SDF_DATE.format(new Date(System.currentTimeMillis())));
                break;
            case WHAT_WEATHER:
                getWeather();
                break;
            case WHAT_LUNAR:
                getLunar();
                break;
        }
        return true;
    }

    private void getWeather() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String apiUrl = String.format(
                            "http://t.weather.sojson.com/api/weather/city/101010300");
                    String result = httpUtil.get(apiUrl);
                    if (TextUtils.isEmpty(result)) {
                        return;
                    }
                    final Weather weather = gson.fromJson(result, Weather.class);
                    if (weather == null || weather.getStatus() != 200) {
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
                            ftv_weather.animateText(weatherStr);

                            String noticeStr = String.format(
                                    "空气质量：%s[%s]，%s。",
                                    weather.getData().getQuality(),
                                    today.getAqi(),
                                    today.getNotice());
                            ftv_notice.animateText(noticeStr);
                        }
                    });
                } catch (Exception e) {
                    // Nothing
                }
            }
        }).start();
    }

    private void getLunar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String apiUrl = "https://www.sojson.com/open/api/lunar/json.shtml";
                    String result = httpUtil.get(apiUrl);
                    if (TextUtils.isEmpty(result)) {
                        return;
                    }
                    final Lunar lunar = gson.fromJson(result, Lunar.class);
                    if (lunar == null || lunar.getStatus() != 200) {
                        return;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Lunar.DataBean data = lunar.getData();
                            String lunarStr = String.format(
                                    "%s年%s月%s [%s年]",
                                    data.getHyear(),
                                    data.getCnmonth(),
                                    data.getCnday(),
                                    data.getAnimal()
                            );
                            ftv_lunar.animateText(lunarStr);
                            ftv_suit.animateText("益：" + data.getSuit());
                            ftv_taboo.animateText("忌：" + data.getTaboo());
                        }
                    });
                } catch (Exception e) {
                    // Nothing
                }
            }
        }).start();
    }

}
