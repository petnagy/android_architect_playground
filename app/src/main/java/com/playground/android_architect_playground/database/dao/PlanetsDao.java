package com.playground.android_architect_playground.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.playground.android_architect_playground.database.entitiy.RecordItem;

import java.util.List;

/**
 * Created by petnagy on 2017. 08. 10..
 */
@Dao
public interface PlanetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(RecordItem recordItem);

    @Query("SELECT * FROM planets ORDER BY id DESC")
    LiveData<List<RecordItem>> loadAllRecords();

    @Delete
    void delete(RecordItem item);
}
