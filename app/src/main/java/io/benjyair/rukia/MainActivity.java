package io.benjyair.rukia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.hanks.htextview.fall.FallTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TimeListener {

    private static final String TAG = "MainActivity";

    private static final int WHAT_TIME = 0x01;
    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

    private static final int WHAT_DATE = 0x02;
    private static final SimpleDateFormat SDF_DATE =
            new SimpleDateFormat("yyyy年MM月dd日  E  a", Locale.CHINA);

    private FallTextView ftv_time;
    private FallTextView ftv_date;
    private TimeHandler timeHandler = new TimeHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ftv_time = findViewById(R.id.ftv_time);
        ftv_date = findViewById(R.id.ftv_date);

        timeHandler.setTimerListener(this);
        timeHandler.addMessage(WHAT_TIME, 1000L);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume()");
        super.onResume();
        timeHandler.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause()");
        super.onPause();
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
            case WHAT_DATE:
                break;

        }
        return true;
    }

}
