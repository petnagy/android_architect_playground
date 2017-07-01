package com.playground.android_architect_playground.pages;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private LogLifecycleObserver logger;

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logger = new LogLifecycleObserver(MainActivity.class, this);
        getLifecycle().addObserver(logger);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getLifecycle().removeObserver(logger);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
