package com.playground.android_architect_playground.pages.logdetailspage.presenter;

import com.playground.android_architect_playground.common.presenter.Presenter;
import com.playground.android_architect_playground.database.entitiy.LogRecord;

import java.util.List;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public interface LogDetailsPresenter extends Presenter {

    void showLogRecords(List<LogRecord> logRecords);

    void displayLoadingProgress();
}
