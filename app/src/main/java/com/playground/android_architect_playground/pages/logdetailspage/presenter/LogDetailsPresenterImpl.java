package com.playground.android_architect_playground.pages.logdetailspage.presenter;

import com.playground.android_architect_playground.common.view.View;
import com.playground.android_architect_playground.database.entitiy.LogRecord;
import com.playground.android_architect_playground.pages.logdetailspage.view.LogDetailsView;

import java.util.List;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class LogDetailsPresenterImpl implements LogDetailsPresenter {

    private LogDetailsView view;

    public LogDetailsPresenterImpl(LogDetailsView view) {
        this.view = view;
    }

    @Override
    public void showLogRecords(List<LogRecord> logRecords) {
        view.showLogRecords(logRecords);
    }
}
