package com.playground.android_architect_playground.database.entitiy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.playground.android_architect_playground.database.FormattedDateConverter;

import java.util.Date;

/**
 * Created by petnagy on 2017. 07. 01..
 */
@Entity(tableName = "logs", indices = {@Index(value = "event")})
public class LogRecord {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long timeStamp;

    private String formattedDate;

    private String logMessage;

    private String event;

    public LogRecord() {
    }

    @Ignore
    public LogRecord(long timeStamp, String logMessage, String event) {
        this.timeStamp = timeStamp;
        Date date = new Date(timeStamp);
        formattedDate = FormattedDateConverter.toFormattedString(date);
        this.logMessage = logMessage;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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
}
