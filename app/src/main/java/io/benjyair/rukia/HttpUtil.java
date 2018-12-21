package io.benjyair.rukia;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class HttpUtil {

    private static final String TAG = "Http";
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final int BUFFER_SIZE = 1024;

    public String get(String url) throws Exception {
        Log.i(TAG, "----------------------------------HTTP----------------------------------");
        Log.i(TAG, "URL  : " + url);
        HttpURLConnection connection = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();

            int httpCode = connection.getResponseCode();
            Log.i(TAG, "Code : " + httpCode);

            bis = new BufferedInputStream(connection.getInputStream());

            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while ((count = bis.read(buffer)) > 0) {
                baos.write(buffer, 0, count);
            }
            String result = new String(baos.toByteArray(), CHARSET);
            Log.i(TAG, "Res  ： " + result);
            return result;
        } catch (IOException e) {
            Log.e(TAG, "post()", e);
            throw new Exception(e);
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                // Nothing
            }
            Log.i(TAG, "----------------------------------HTTP----------------------------------");
        }
    }

}
