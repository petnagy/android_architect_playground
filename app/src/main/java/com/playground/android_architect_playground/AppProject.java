package com.playground.android_architect_playground;

import com.playground.android_architect_playground.inject.components.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class AppProject extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);
    }

}
