package com.bigd8109.app.camerafacedetection;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class DisplayPictureActivity extends AppCompatActivity {
    private static String TAG = DisplayPictureActivity.class.getSimpleName();

    private ImageView displayPictureView;
    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_picture);

        displayPictureView = (ImageView) findViewById(R.id.display_picture);
        goBackButton = (Button) findViewById(R.id.go_back);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "go back");
                finish();
            }
        });


        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        Log.d(TAG, "dislay pic: " + path);
//        displayPictureView.setImageBitmap(BitmapFactory.decodeFile(path));
        Uri uri = Uri.fromFile(new File(path));
        Picasso.with(getApplicationContext()).load(uri).into(displayPictureView);

    }
}
