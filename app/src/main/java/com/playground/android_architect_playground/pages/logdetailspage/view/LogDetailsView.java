package com.playground.android_architect_playground.pages.logdetailspage.view;

import com.playground.android_architect_playground.common.view.View;
import com.playground.android_architect_playground.database.entitiy.LogRecord;

import java.util.List;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public interface LogDetailsView extends View {

    void showLogRecords(List<LogRecord> logRecords);

}
