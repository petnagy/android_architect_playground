package com.playground.android_architect_playground.pages.logdetailspage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.database.entitiy.LogRecord;

import java.util.List;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class LogDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LogRecord> logRecords;

    public LogDetailAdapter(List<LogRecord> logRecords) {
        this.logRecords = logRecords;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.log_detail_record, parent, false);
        return new LogDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LogRecord logRecord = logRecords.get(position);
        ((LogDetailViewHolder) holder).logFormattedDate.setText(logRecord.getFormattedDate());
        ((LogDetailViewHolder) holder).logMessage.setText(logRecord.getLogMessage());
        ((LogDetailViewHolder) holder).logEvent.setText(logRecord.getEvent());
    }

    @Override
    public int getItemCount() {
        return logRecords.size();
    }

    public void setLogRecords(List<LogRecord> logRecords) {
        this.logRecords = logRecords;
        notifyDataSetChanged();
    }

    public static class LogDetailViewHolder extends RecyclerView.ViewHolder {

        TextView logFormattedDate;

        TextView logMessage;

        TextView logEvent;

        public LogDetailViewHolder(View view) {
            super(view);
            logFormattedDate = view.findViewById(R.id.log_formatted_date);
            logMessage = view.findViewById(R.id.log_message);
            logEvent = view.findViewById(R.id.log_event);
        }
    }
}
