package com.playground.android_architect_playground.inject.modules;

import android.arch.lifecycle.LifecycleRegistry;

import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.inject.PerActivity;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.logdetailspage.LogDetailsActivity;
import com.playground.android_architect_playground.pages.logdetailspage.presenter.LogDetailsPresenter;
import com.playground.android_architect_playground.pages.logdetailspage.presenter.LogDetailsPresenterImpl;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsView;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsViewImpl;
import com.playground.android_architect_playground.pages.mainpage.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 07. 02..
 */
@Module
public class LogDetailsModule {

    @PerActivity
    @Provides
    LifecycleRegistry provideLifeCycleRegistry(LogDetailsActivity activity) {
        return new LifecycleRegistry(activity);
    }

    @PerActivity
    @Provides
    LogLifecycleObserver provideLogLifecycleObserver(LogDao logDao) {
        return new LogLifecycleObserver(LogDetailsActivity.class, logDao);
    }

    @PerActivity
    @Provides
    LogDetailsView provideLogDetailView(LogDetailsActivity activity) {
        return new LogDetailsViewImpl(activity);
    }

    @PerActivity
    @Provides
    LogDetailsPresenter provideLogDetailPresenter(LogDetailsView logDetailsView) {
        return new LogDetailsPresenterImpl(logDetailsView);
    }
}
