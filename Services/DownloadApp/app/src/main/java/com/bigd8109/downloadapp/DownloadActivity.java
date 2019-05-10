package com.bigd8109.downloadapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

public class DownloadActivity extends AppCompatActivity{
    private final static String TAG = DownloadActivity.class.getSimpleName();
    Button downloadButton;
    Button stopButton;
    ImageView imgView;
    Intent serviceIntent;
    DownloadService mService;

    DownloadService.ResultCallback callback = new DownloadService.ResultCallback() {
        @Override
        public void getBitmap(Bitmap bmp) {
            Log.d(TAG, "download is done");
            imgView.setImageBitmap(bmp);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        downloadButton = (Button) findViewById(R.id.downloadButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        imgView = (ImageView) findViewById(R.id.imgView);
        serviceIntent = new Intent(this, DownloadService.class);
        serviceIntent.putExtra("id", "download_service");
        startService(serviceIntent);


        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mService = DownloadService.getServiceObject(callback);
                mService.downloadUrl("https://sample-videos.com/img/Sample-jpg-image-200kb.jpg");
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(serviceIntent);
            }
        });
    }
}
