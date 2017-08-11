package com.playground.android_architect_playground.inject.components;

import android.content.Context;

import com.playground.android_architect_playground.AppProject;
import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.database.dao.PlanetsDao;
import com.playground.android_architect_playground.inject.ApplicationContext;
import com.playground.android_architect_playground.inject.modules.ApplicationModule;
import com.playground.android_architect_playground.inject.modules.ContributesAndroidInjectorModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by petnagy on 2017. 07. 02..
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, ContributesAndroidInjectorModule.class})
public interface ApplicationComponent extends AndroidInjector<AppProject> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<AppProject> {
    }

    @ApplicationContext
    Context getContext();

    LogDao getLogDao();

    PlanetsDao getPlanetsDao();
}
