package com.playground.android_architect_playground.inject.modules;

import android.arch.lifecycle.LifecycleRegistry;

import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.database.dao.PlanetsDao;
import com.playground.android_architect_playground.inject.PerActivity;
import com.playground.android_architect_playground.logger.LogLifecycleObserver;
import com.playground.android_architect_playground.pages.roomlivedata.RoomLiveDataDemoActivity;
import com.playground.android_architect_playground.pages.roomlivedata.adapter.RoomLiveDataAdapter;
import com.playground.android_architect_playground.pages.roomlivedata.view.RoomLiveDataView;
import com.playground.android_architect_playground.pages.roomlivedata.view.RoomLiveDataViewImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 08. 10..
 */
@Module
public class RoomLiveDataDemoActivityModule {

    @PerActivity
    @Provides
    LifecycleRegistry provideLifeCycleRegistry(RoomLiveDataDemoActivity activity) {
        return new LifecycleRegistry(activity);
    }

    @PerActivity
    @Provides
    LogLifecycleObserver provideLogLifecycleObserver(LogDao logDao) {
        return new LogLifecycleObserver(RoomLiveDataDemoActivity.class, logDao);
    }

    @PerActivity
    @Provides
    RoomLiveDataAdapter provideRoomLiveDataAdapter() {
        return new RoomLiveDataAdapter();
    }

    @PerActivity
    @Provides
    RoomLiveDataView provideRoomLiveDataView(RoomLiveDataDemoActivity activity, PlanetsDao planetsDao, RoomLiveDataAdapter adapter) {
        return new RoomLiveDataViewImpl(activity, planetsDao, adapter);
    }
}
