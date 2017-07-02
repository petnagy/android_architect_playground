package com.playground.android_architect_playground.inject.modules;

import com.playground.android_architect_playground.inject.PerActivity;
import com.playground.android_architect_playground.pages.logdetailspage.LogDetailsActivity;
import com.playground.android_architect_playground.pages.mainpage.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by petnagy on 2017. 07. 02..
 */
@Module
public abstract class ContributesAndroidInjectorModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    public abstract MainActivity contributeMainActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = {LogDetailsModule.class})
    public abstract LogDetailsActivity contributeLogDetailsActivityInjector();
}
