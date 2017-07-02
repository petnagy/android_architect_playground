package com.playground.android_architect_playground.pages.logdetailspage.view;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.database.entitiy.LogRecord;
import com.playground.android_architect_playground.decor.SpaceItemDecor;
import com.playground.android_architect_playground.pages.logdetailspage.adapter.LogDetailAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class LogDetailsViewImpl implements LogDetailsView {

    private Activity activity;

    private LogDetailAdapter adapter;

    public LogDetailsViewImpl(Activity activity) {
        this.activity = activity;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onCreate() {
        RecyclerView list = activity.findViewById(R.id.logList);
        adapter = new LogDetailAdapter(Collections.<LogRecord>emptyList());
        list.setLayoutManager(new GridLayoutManager(activity, 1));
        list.addItemDecoration(new SpaceItemDecor(8));
        list.setAdapter(adapter);
    }

    @Override
    public void showLogRecords(List<LogRecord> logRecords) {
        adapter.setLogRecords(logRecords);
    }
}
