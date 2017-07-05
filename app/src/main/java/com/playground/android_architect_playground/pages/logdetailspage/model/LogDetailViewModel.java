package com.playground.android_architect_playground.pages.logdetailspage.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.database.entitiy.LogRecord;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by petnagy on 2017. 07. 03..
 */

public class LogDetailViewModel extends ViewModel {

    private String filterValue;

    private MutableLiveData<List<LogRecord>> logRecords;

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue, LogDao logDao) {
        this.filterValue = filterValue;
        loadLogRecords(logDao);
    }

    public LiveData<List<LogRecord>> getLogRecords(LogDao logDao) {
        if (logRecords == null) {
            logRecords = new MutableLiveData<>();
        }
        if (logRecords.getValue() == null || logRecords.getValue().size() == 0) {
            loadLogRecords(logDao);
        }
        return logRecords;
    }

    private void loadLogRecords(final LogDao logDao) {
        Single.fromCallable(new Callable<List<LogRecord>>() {
            @Override
            public List<LogRecord> call() throws Exception {
                if (filterValue == null) {
                    filterValue = "";
                }
                Thread.sleep(5000);
                return logDao.loadLogsByFilter(filterValue);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<List<LogRecord>>() {
                    @Override
                    public void onSuccess(@NonNull List<LogRecord> logList) {
                        if (logRecords.getValue() != null) {
                            logRecords.getValue().clear();
                        }
                        logRecords.setValue(logList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
