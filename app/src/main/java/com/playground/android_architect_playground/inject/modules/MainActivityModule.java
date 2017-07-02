package com.playground.android_architect_playground.inject.modules;

import android.arch.lifecycle.LifecycleRegistry;

import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.inject.PerActivity;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 07. 02..
 */
@Module
public class MainActivityModule {

    @PerActivity
    @Provides
    LifecycleRegistry provideLifeCycleRegistry(MainActivity activity) {
        return new LifecycleRegistry(activity);
    }

    @PerActivity
    @Provides
    LogLifecycleObserver provideLogLifecycleObserver(LogDao logDao) {
        return new LogLifecycleObserver(MainActivity.class, logDao);
    }

}
