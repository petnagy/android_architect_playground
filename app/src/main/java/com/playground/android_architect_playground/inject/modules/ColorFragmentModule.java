package com.playground.android_architect_playground.inject.modules;

import android.arch.lifecycle.LifecycleRegistry;

import com.playground.android_architect_playground.inject.PerActivity;
import com.playground.android_architect_playground.pages.colorpage.ColorActivity;
import com.playground.android_architect_playground.pages.colorpage.view.ColorFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 07. 06..
 */
@Module
public class ColorFragmentModule {

    @Provides
    LifecycleRegistry provideLifeCycleRegistry(ColorFragment fragment) {
        return new LifecycleRegistry(fragment);
    }

}
