package com.playground.android_architect_playground.pages.roomlivedata;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.roomlivedata.view.RoomLiveDataView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by petnagy on 2017. 08. 10..
 */

public class RoomLiveDataDemoActivity extends DaggerAppCompatActivity implements LifecycleRegistryOwner {

    @Inject
    LogLifecycleObserver logger;

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    RoomLiveDataView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomlivedata_activity);
        getLifecycle().addObserver(logger);
        getLifecycle().addObserver(view);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    public static Intent launchIntent(Context context) {
        return new Intent(context, RoomLiveDataDemoActivity.class);
    }
}
