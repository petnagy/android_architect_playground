package com.playground.android_architect_playground.database.entitiy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.playground.android_architect_playground.util.DateUtil;

import java.util.Date;

/**
 * Created by petnagy on 2017. 07. 01..
 */
@Entity(tableName = "logs", indices = {@Index(value = "event"), @Index(value = "logMessage")})
public class LogRecord {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private Date date;

    private String formattedDate;

    private String logMessage;

    private String event;

    public LogRecord() {
    }

    @Ignore
    public LogRecord(Date date, String logMessage, String event) {
        this.date = date;
        formattedDate = DateUtil.toFormattedString(date);
        this.logMessage = logMessage;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogRecord logRecord = (LogRecord) o;

        if (id != logRecord.id) return false;
        if (date != null ? !date.equals(logRecord.date) : logRecord.date != null) return false;
        if (formattedDate != null ? !formattedDate.equals(logRecord.formattedDate) : logRecord.formattedDate != null)
            return false;
        if (logMessage != null ? !logMessage.equals(logRecord.logMessage) : logRecord.logMessage != null)
            return false;
        return event != null ? event.equals(logRecord.event) : logRecord.event == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (formattedDate != null ? formattedDate.hashCode() : 0);
        result = 31 * result + (logMessage != null ? logMessage.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }
}
