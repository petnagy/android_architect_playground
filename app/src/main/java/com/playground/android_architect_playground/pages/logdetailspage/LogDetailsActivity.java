package com.playground.android_architect_playground.pages.logdetailspage;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.database.entitiy.LogRecord;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.logdetailspage.presenter.LogDetailsPresenter;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsCallback;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsView;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_details_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLifecycle().addObserver(view);
        getLifecycle().addObserver(logger);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Single.fromCallable(new Callable<List<LogRecord>>() {
            @Override
            public List<LogRecord> call() throws Exception {
                return logDao.loadAllLogs();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<List<LogRecord>>() {
                    @Override
                    public void onSuccess(@NonNull List<LogRecord> logRecords) {
                        presenter.showLogRecords(logRecords);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        getLifecycle().removeObserver(logger);
        getLifecycle().removeObserver(view);
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
        Single.fromCallable(new Callable<List<LogRecord>>() {
            @Override
            public List<LogRecord> call() throws Exception {
                return logDao.loadLogsByFilter(filter);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<List<LogRecord>>() {
                    @Override
                    public void onSuccess(@NonNull List<LogRecord> logRecords) {
                        presenter.showLogRecords(logRecords);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
