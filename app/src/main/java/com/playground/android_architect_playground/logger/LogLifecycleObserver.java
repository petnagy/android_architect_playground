package com.playground.android_architect_playground.logger;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.playground.android_architect_playground.database.AppDatabase;
import com.playground.android_architect_playground.database.entitiy.LogRecord;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by petnagy on 2017. 06. 30..
 */

public class LogLifecycleObserver implements LifecycleObserver {

    private static final String TAG = "LifecycleLogger";

    private String className;

    private AppDatabase database;

    public LogLifecycleObserver(Class clazz, Context context) {
        className = clazz.getSimpleName();
        database = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        String logMessage = className + " was created";
        Log.d(TAG, logMessage);
        LogRecord log = new LogRecord(SystemClock.currentThreadTimeMillis(), logMessage, Lifecycle.Event.ON_CREATE.name());
        saveInDatabase(log);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        String logMessage = className + " was started";
        Log.d(TAG, logMessage);
        LogRecord log = new LogRecord(SystemClock.currentThreadTimeMillis(), logMessage, Lifecycle.Event.ON_START.name());
        saveInDatabase(log);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        String logMessage = className + " was resumed";
        Log.d(TAG, logMessage);
        LogRecord log = new LogRecord(SystemClock.currentThreadTimeMillis(), logMessage, Lifecycle.Event.ON_RESUME.name());
        saveInDatabase(log);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        String logMessage = className + " was paused";
        Log.d(TAG, logMessage);
        LogRecord log = new LogRecord(SystemClock.currentThreadTimeMillis(), logMessage, Lifecycle.Event.ON_PAUSE.name());
        saveInDatabase(log);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        final String logMessage = className + " was stopped";
        Log.d(TAG, logMessage);
        LogRecord log = new LogRecord(SystemClock.currentThreadTimeMillis(), logMessage, Lifecycle.Event.ON_STOP.name());
        saveInDatabase(log);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        String logMessage = className + " was destoryed";
        Log.d(TAG, logMessage);
        LogRecord log = new LogRecord(SystemClock.currentThreadTimeMillis(), logMessage, Lifecycle.Event.ON_DESTROY.name());
        saveInDatabase(log);
    }

    private void saveInDatabase(final LogRecord log) {
        Single.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                database.logDao().save(log);
                return new Object();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
