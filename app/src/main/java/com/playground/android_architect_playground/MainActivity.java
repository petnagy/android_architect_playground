package com.playground.android_architect_playground;

import android.arch.lifecycle.LifecycleActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends LifecycleActivity {

    private LogLifecycleObserver logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logger = new LogLifecycleObserver(MainActivity.class);
        getLifecycle().addObserver(logger);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getLifecycle().removeObserver(logger);
    }
}
