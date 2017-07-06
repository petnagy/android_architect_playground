package com.playground.android_architect_playground.pages.colorpage;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.colorpage.data.ColorEnum;
import com.playground.android_architect_playground.pages.colorpage.model.ColorViewModel;
import com.playground.android_architect_playground.pages.colorpage.view.ColorFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by petnagy on 2017. 07. 05..
 */

public class ColorActivity extends DaggerAppCompatActivity implements LifecycleRegistryOwner {

    @Inject
    LogLifecycleObserver logger;

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_activity);
        getLifecycle().addObserver(logger);

        ColorViewModel colorViewModel = ViewModelProviders.of(this).get(ColorViewModel.class);
        colorViewModel.getBaseColor().setValue(ColorEnum.BLUE);

        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.fragment_one_holder, ColorFragment.newInstance(0));
        ft1.commit();

        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft2.replace(R.id.fragment_two_holder, ColorFragment.newInstance(1));
        ft2.commit();

        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
        ft3.replace(R.id.fragment_three_holder, ColorFragment.newInstance(3));
        ft3.commit();

        FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
        ft4.replace(R.id.fragment_four_holder, ColorFragment.newInstance(2));
        ft4.commit();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    public static Intent launchIntent(Context context) {
        return new Intent(context, ColorActivity.class);
    }
}
