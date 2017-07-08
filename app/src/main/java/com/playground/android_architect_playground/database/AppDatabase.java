package com.playground.android_architect_playground.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.playground.android_architect_playground.database.entitiy.LogRecord;
import com.playground.android_architect_playground.database.dao.LogDao;

/**
 * Created by petnagy on 2017. 07. 01..
 */
@Database(entities = {LogRecord.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "log_database";

    public abstract LogDao logDao();
}
