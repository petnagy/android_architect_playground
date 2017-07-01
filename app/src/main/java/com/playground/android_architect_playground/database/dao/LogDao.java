package com.playground.android_architect_playground.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.playground.android_architect_playground.database.entitiy.LogRecord;

/**
 * Created by petnagy on 2017. 07. 01..
 */
@Dao
public interface LogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(LogRecord logRecord);

}
