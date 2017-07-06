package com.playground.android_architect_playground.pages.mainpage.view;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.view.View;
import android.widget.Button;

import com.playground.android_architect_playground.R;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class MainActivityViewImpl implements MainActivityView {

    private Activity activity;

    private MainActivityCallback callback;

    public MainActivityViewImpl(Activity activity) {
        this.activity = activity;
        this.callback = (MainActivityCallback) activity;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onCreate() {
        Button btnGoNextPage = activity.findViewById(R.id.nextPage);
        btnGoNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onNextPageButtonPressed();
            }
        });

        Button btnGoColorPage = activity.findViewById(R.id.colorPage);
        btnGoColorPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onColorPageButtonPressed();
            }
        });
    }
}
