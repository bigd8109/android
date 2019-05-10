package com.bigd8109.downloadapp;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;

public class DownloadService extends Service {
    private static final String TAG = DownloadService.class.getSimpleName();
    private String url;
    static ResultCallback callback;

    public interface ResultCallback {
        public void getBitmap(Bitmap bmp);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    private static DownloadService self = null;
    public static DownloadService getServiceObject(ResultCallback cb){
        callback = cb;
        return self;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return Service.START_NOT_STICKY;
    }

    public static void downloadUrl(String url) {
        DownloadTask downloadTask = new DownloadTask(callback);
//        downloadTask.execute("https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4");
        downloadTask.execute(url);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
