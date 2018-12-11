package com.bigd8109.downloadapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DownloadActivity extends AppCompatActivity implements DownloadService.ResultCallback {
    Button downloadButton;
    Button stopButton;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        downloadButton = (Button) findViewById(R.id.downloadButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        serviceIntent = new Intent(this, DownloadService.class);
        serviceIntent.putExtra("id", "download_service");

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(serviceIntent);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(serviceIntent);
            }
        });
    }

    @Override
    public void getBitmap(Bitmap bmp) {

    }
}
