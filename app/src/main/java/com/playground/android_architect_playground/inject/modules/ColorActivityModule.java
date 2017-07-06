package com.playground.android_architect_playground.inject.modules;

import android.arch.lifecycle.LifecycleRegistry;

import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.inject.PerActivity;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.colorpage.ColorActivity;
import com.playground.android_architect_playground.pages.logdetailspage.LogDetailsActivity;
import com.playground.android_architect_playground.pages.logdetailspage.presenter.LogDetailsPresenter;
import com.playground.android_architect_playground.pages.logdetailspage.presenter.LogDetailsPresenterImpl;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsView;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsViewImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 07. 02..
 */
@Module
public class ColorActivityModule {

    @PerActivity
    @Provides
    LifecycleRegistry provideLifeCycleRegistry(ColorActivity activity) {
        return new LifecycleRegistry(activity);
    }

    @PerActivity
    @Provides
    LogLifecycleObserver provideLogLifecycleObserver(LogDao logDao) {
        return new LogLifecycleObserver(ColorActivity.class, logDao);
    }

}
