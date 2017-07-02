package com.playground.android_architect_playground.pages.mainpage;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.logdetailspage.LogDetailsActivity;
import com.playground.android_architect_playground.pages.mainpage.view.MainActivityCallback;
import com.playground.android_architect_playground.pages.mainpage.view.MainActivityView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements LifecycleRegistryOwner, MainActivityCallback {

    @Inject
    LogLifecycleObserver logger;

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    MainActivityView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLifecycle().addObserver(view);
        getLifecycle().addObserver(logger);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getLifecycle().removeObserver(logger);
        getLifecycle().removeObserver(view);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onNextPageButtonPressed() {
        Log.d("MainActivity", "Go Details Page Pressed");
        Intent intent = LogDetailsActivity.launchIntent(this);
        startActivity(intent);
    }
}
