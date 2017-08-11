package com.playground.android_architect_playground.inject.modules;

import android.arch.lifecycle.LifecycleRegistry;

import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.database.entitiy.LogRecord;
import com.playground.android_architect_playground.inject.PerActivity;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.logdetailspage.LogDetailsActivity;
import com.playground.android_architect_playground.pages.logdetailspage.adapter.LogDetailAdapter;
import com.playground.android_architect_playground.pages.logdetailspage.presenter.LogDetailsPresenter;
import com.playground.android_architect_playground.pages.logdetailspage.presenter.LogDetailsPresenterImpl;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsView;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsViewImpl;

import java.util.Collections;

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
    LogDetailAdapter provideLogDetailAdapter() {
        return new LogDetailAdapter(Collections.<LogRecord>emptyList());
    }

    @PerActivity
    @Provides
    LogDetailsView provideLogDetailView(LogDetailsActivity activity, LogDetailAdapter adapter) {
        return new LogDetailsViewImpl(activity, adapter);
    }

    @PerActivity
    @Provides
    LogDetailsPresenter provideLogDetailPresenter(LogDetailsView logDetailsView) {
        return new LogDetailsPresenterImpl(logDetailsView);
    }
}
