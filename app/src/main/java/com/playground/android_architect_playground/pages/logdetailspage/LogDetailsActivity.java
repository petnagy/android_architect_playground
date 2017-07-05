package com.playground.android_architect_playground.pages.logdetailspage;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.database.entitiy.LogRecord;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.logdetailspage.model.LogDetailViewModel;
import com.playground.android_architect_playground.pages.logdetailspage.presenter.LogDetailsPresenter;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsCallback;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class LogDetailsActivity extends DaggerAppCompatActivity implements LifecycleRegistryOwner, LogDetailsCallback {

    @Inject
    LogLifecycleObserver logger;

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    LogDetailsView view;

    @Inject
    LogDetailsPresenter presenter;

    @Inject
    LogDao logDao;

    private LogDetailViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_details_activity);
        getLifecycle().addObserver(view);
        getLifecycle().addObserver(logger);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.displayLoadingProgress();
        model = ViewModelProviders.of(this).get(LogDetailViewModel.class);
        model.getLogRecords(logDao).observe(this, new Observer<List<LogRecord>>() {
            @Override
            public void onChanged(@Nullable List<LogRecord> logRecords) {
                presenter.showLogRecords(logRecords);
                Log.d("Logger", "load data from model");
            }
        });
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    public static Intent launchIntent(Context context) {
        return new Intent(context, LogDetailsActivity.class);
    }

    @Override
    public void onFilterChanged(final String filter) {
        presenter.displayLoadingProgress();
        model.setFilterValue(filter, logDao);
    }
}
