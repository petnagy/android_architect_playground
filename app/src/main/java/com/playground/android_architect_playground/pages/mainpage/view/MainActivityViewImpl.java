package com.playground.android_architect_playground.pages.mainpage.view;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.pages.colorpage.ColorActivity;
import com.playground.android_architect_playground.pages.logdetailspage.LogDetailsActivity;
import com.playground.android_architect_playground.pages.roomlivedata.RoomLiveDataDemoActivity;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class MainActivityViewImpl implements MainActivityView {

    private Activity activity;

    public MainActivityViewImpl(Activity activity) {
        this.activity = activity;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onCreate() {
        Button btnGoNextPage = activity.findViewById(R.id.nextPage);
        btnGoNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivityViewImpl", "Go Details Page Pressed");
                Intent intent = LogDetailsActivity.launchIntent(activity);
                activity.startActivity(intent);
            }
        });

        Button btnGoColorPage = activity.findViewById(R.id.colorPage);
        btnGoColorPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivityViewImpl", "Go to Color Page Pressed");
                Intent intent = ColorActivity.launchIntent(activity);
                activity.startActivity(intent);
            }
        });

        Button btnRoomLiveData = activity.findViewById(R.id.roomLiveData);
        btnRoomLiveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivityViewImpl", "Go to RoomLiveData");
                Intent intent = RoomLiveDataDemoActivity.launchIntent(activity);
                activity.startActivity(intent);
            }
        });
    }
}
