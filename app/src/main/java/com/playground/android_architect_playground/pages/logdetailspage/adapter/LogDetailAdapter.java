package com.playground.android_architect_playground.pages.logdetailspage.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.database.entitiy.LogRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class LogDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LogRecord> logRecords;

    public LogDetailAdapter(List<LogRecord> logRecords) {
        //this.logRecords = logRecords;
        this.logRecords = new ArrayList<>();
        this.logRecords.addAll(logRecords);
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
        //this.logRecords = logRecords;
        //notifyDataSetChanged();
        List<LogRecord> oldItems = new ArrayList<>(this.logRecords);
        this.logRecords.clear();
        this.logRecords.addAll(logRecords);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new LogDetailsDiffCallback(oldItems, this.logRecords));
        result.dispatchUpdatesTo(this);
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

    public static class LogDetailsDiffCallback extends DiffUtil.Callback {

        private List<LogRecord> oldLogs;

        private List<LogRecord> newLogs;

        public LogDetailsDiffCallback(List<LogRecord> oldLogs, List<LogRecord> newLogs) {
            this.oldLogs = oldLogs;
            this.newLogs = newLogs;
        }

        @Override
        public int getOldListSize() {
            return oldLogs.size();
        }

        @Override
        public int getNewListSize() {
            return newLogs.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldLogs.get(oldItemPosition).equals(newLogs.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldLogs.get(oldItemPosition).equals(newLogs.get(newItemPosition));
        }
    }
}
