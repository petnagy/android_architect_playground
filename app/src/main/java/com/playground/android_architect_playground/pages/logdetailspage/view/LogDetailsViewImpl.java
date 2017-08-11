package com.playground.android_architect_playground.pages.logdetailspage.view;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

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

    private ProgressBar progressBar;

    private LogDetailsCallback callback;

    private Button activateFilter;

    private EditText filter;

    public LogDetailsViewImpl(Activity activity, LogDetailAdapter adapter) {
        this.activity = activity;
        this.callback = (LogDetailsCallback) activity;
        this.adapter = adapter;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onCreate() {
        RecyclerView list = activity.findViewById(R.id.logList);
        progressBar = activity.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        list.setLayoutManager(new GridLayoutManager(activity, 1));
        list.addItemDecoration(new SpaceItemDecor(8));
        list.setAdapter(adapter);
        filter = activity.findViewById(R.id.filter_value);
        activateFilter = activity.findViewById(R.id.filter_activate_button);
        activateFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onFilterChanged(filter.getText().toString());
            }
        });
    }

    @Override
    public void showLogRecords(List<LogRecord> logRecords) {
        adapter.setLogRecords(logRecords);
        progressBar.setVisibility(View.GONE);
        activateFilter.setEnabled(true);
        filter.setEnabled(true);
    }

    @Override
    public void showLoadingProgress() {
        progressBar.setVisibility(View.VISIBLE);
        activateFilter.setEnabled(false);
        filter.setEnabled(false);
    }
}
