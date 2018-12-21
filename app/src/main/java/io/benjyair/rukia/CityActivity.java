package io.benjyair.rukia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "MainActivity";

    private SharePreferencesUtil preferencesUtil;
    private List<City> cities;
    private String currentCityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_city);

        preferencesUtil = new SharePreferencesUtil(this);
        currentCityCode = preferencesUtil.getCurrentCityCode();

        ListView lv_city = findViewById(R.id.lv_city);
        try {
            cities = loadCity();
            Adapter adapter = new Adapter();
            lv_city.setAdapter(adapter);
            lv_city.setOnItemClickListener(this);
        } catch (IOException e) {
            Toast.makeText(this, "City list not found.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        City city = cities.get(position);
        preferencesUtil.setCurrentCity(city.getName(), city.getCode());
        setResult(RESULT_OK);
        finish();
    }

    private List<City> loadCity() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(getAssets().open("city.json")));
        String line;
        StringBuilder buffer = new StringBuilder();
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        String city = buffer.toString();
        if (TextUtils.isEmpty(city)) {
            throw new IOException("City not found");
        }
        List<City> cities = new Gson().fromJson(city, new TypeToken<List<City>>() {
        }.getType());

        List<City> availableCities = new ArrayList<>(cities.size());

        for (City c : cities) {
            if (TextUtils.isEmpty(c.getCode())) {
                continue;
            }
            availableCities.add(c);
        }
        return availableCities;
    }

    private class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cities.size();
        }

        @Override
        public City getItem(int position) {
            return cities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_city, null);

                viewHolder.setNameView((TextView) convertView.findViewById(R.id.tv_name));
                viewHolder.setStatusView((TextView) convertView.findViewById(R.id.tv_status));
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            City item = getItem(position);

            viewHolder.getNameView().setText(item.getName());
            viewHolder.getStatusView().setText(item.getCode().equals(currentCityCode) ? "√" : "");
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tv_name;
        TextView tv_status;

        TextView getNameView() {
            return tv_name;
        }

        void setNameView(TextView tv_name) {
            this.tv_name = tv_name;
        }

        TextView getStatusView() {
            return tv_status;
        }

        void setStatusView(TextView tv_status) {
            this.tv_status = tv_status;
        }
    }

}
