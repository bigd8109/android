package com.bigd8109.downloadapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Integer, Bitmap> {
    private final static String TAG = DownloadTask.class.getSimpleName();
    String url;
    Bitmap bitmap;
    InputStream in = null;
    int responseCode = -1;
    DownloadService.ResultCallback callback;

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d(TAG, "downloading..." + values);
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Log.d(TAG, "onPostExecute");
        super.onPostExecute(bitmap);
        callback.getBitmap(bitmap);
    }

    public DownloadTask(DownloadService.ResultCallback callback) {
        super();
        this.callback = callback;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();
            responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
