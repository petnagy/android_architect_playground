package com.playground.android_architect_playground.pages.roomlivedata.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.playground.android_architect_playground.R;
import com.playground.android_architect_playground.database.dao.PlanetsDao;
import com.playground.android_architect_playground.database.entitiy.RecordItem;
import com.playground.android_architect_playground.decor.SpaceItemDecor;
import com.playground.android_architect_playground.pages.roomlivedata.RoomLiveDataDemoActivity;
import com.playground.android_architect_playground.pages.roomlivedata.adapter.RoomLiveDataAdapter;
import com.playground.android_architect_playground.pages.roomlivedata.model.Planets;

import java.util.List;
import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by petnagy on 2017. 08. 10..
 */

public class RoomLiveDataViewImpl implements RoomLiveDataView, DeleteCallback {

    private RoomLiveDataDemoActivity activity;

    private PlanetsDao planetsDao;

    private RoomLiveDataAdapter adapter;

    public RoomLiveDataViewImpl(RoomLiveDataDemoActivity activity, PlanetsDao planetsDao, RoomLiveDataAdapter adapter) {
        this.activity = activity;
        this.planetsDao = planetsDao;
        this.adapter = adapter;
        this.adapter.setDeleteCallback(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onCreate() {
        Button addMoreRecords = activity.findViewById(R.id.addData);
        addMoreRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordItem item = generateRandomRecord();
                saveInDatabase(item);
            }
        });

        RecyclerView list = activity.findViewById(R.id.recordList);
        list.setLayoutManager(new LinearLayoutManager(activity));
        list.addItemDecoration(new SpaceItemDecor(8));
        list.setAdapter(adapter);
    }

    @NonNull
    private RecordItem generateRandomRecord() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(9);
        RecordItem item = new RecordItem();
        item.setPlanetName(Planets.values()[randomInt].name());
        return item;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        LiveData<List<RecordItem>> records = planetsDao.loadAllRecords();
        records.observe(activity, new Observer<List<RecordItem>>() {
            @Override
            public void onChanged(@Nullable List<RecordItem> recordItems) {
                adapter.setData(recordItems);
            }
        });
    }

    @Override
    public void onItemDeletePressed(RecordItem item) {
        deleteInDatabase(item);
    }

    private void saveInDatabase(final RecordItem record) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                planetsDao.save(record);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    private void deleteInDatabase(final RecordItem record) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                planetsDao.delete(record);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
