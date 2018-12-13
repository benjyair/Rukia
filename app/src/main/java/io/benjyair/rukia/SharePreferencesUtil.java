package io.benjyair.rukia;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesUtil {

    private final SharedPreferences sharedPreferences;

    public SharePreferencesUtil(Context context) {
        sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE);
    }

    public String getCurrentCityName() {
        return sharedPreferences.getString("name", "北京市");
    }

    public String getCurrentCityCode() {
        return sharedPreferences.getString("code", "101010100");
    }

    public void setCurrentCity(String name, String code) {
        sharedPreferences.edit().putString("name", name).putString("code", code).apply();
    }

}
