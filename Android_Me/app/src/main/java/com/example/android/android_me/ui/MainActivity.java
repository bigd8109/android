package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by hp.luong on 4/2/2018.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnClickImageListener{
    private static final String TAG = MainActivity.class.getSimpleName();

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                headFragment.setListIndex(headIndex);

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                bodyFragment.setListIndex(bodyIndex);

                BodyPartFragment legsFragment = new BodyPartFragment();
                legsFragment.setImageIds(AndroidImageAssets.getLegs());
                legsFragment.setListIndex(legIndex);

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();
                fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment).commit();
                fragmentManager.beginTransaction().add(R.id.legs_container, legsFragment).commit();
            }
        }
        else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "pos = " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position / 12;
        int listIndex = position - bodyPartNumber*12;

        if (mTwoPane) {
            BodyPartFragment newFragment = new BodyPartFragment();
            switch (bodyPartNumber) {
                case 0:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.head_container, newFragment).commit();
                    break;
                case 1:
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, newFragment).commit();
                    break;
                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.legs_container, newFragment).commit();
                    break;
                default:
                    break;
            }
        }
        else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }
        }

        Bundle bundle = new Bundle();
        bundle.putInt("headIndex", headIndex);
        bundle.putInt("bodyIndex", bodyIndex);
        bundle.putInt("legIndex", legIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(bundle);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "starActivity: " + intent);
                startActivity(intent);
            }
        });
    }
}
