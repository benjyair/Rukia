package io.benjyair.rukia;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;

public class TimeHandler extends Handler {

    private static final SparseArray<Long> mapping = new SparseArray<>(3);
    private TimeListener listener;

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (listener == null) {
            return;
        }
        int what = msg.what;
        listener.onUpdate(what);
        this.sendEmptyMessageDelayed(what, mapping.get(what));
    }

    public void setTimerListener(TimeListener listener) {
        this.listener = listener;
    }

    public void addMessage(int what, long delayMillis) {
        mapping.append(what, delayMillis);
        this.sendEmptyMessage(what);
    }

    public void removeMessage(int what) {
        mapping.remove(what);
    }

    public void onResume() {
        for (int i = 0; i < mapping.size(); i++) {
            int key = mapping.keyAt(i);
            this.sendEmptyMessageDelayed(key, mapping.get(key));
        }
    }

    public void onPause() {
        for (int i = 0; i < mapping.size(); i++) {
            this.removeMessages(mapping.keyAt(i));
        }
    }

}
