package com.playground.android_architect_playground.inject.modules;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.playground.android_architect_playground.AppProject;
import com.playground.android_architect_playground.database.AppDatabase;
import com.playground.android_architect_playground.database.dao.LogDao;
import com.playground.android_architect_playground.database.dao.PlanetsDao;
import com.playground.android_architect_playground.inject.ApplicationContext;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 07. 02..
 */
@Module
public abstract class ApplicationModule {

    @Binds
    @ApplicationContext
    abstract Context provideContext(AppProject application);

    @Singleton
    @Provides
    static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DATABASE_NAME)
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    @Singleton
    @Provides
    static LogDao provideLogDao(AppDatabase appDatabase) {
        return appDatabase.logDao();
    }

    @Singleton
    @Provides
    static PlanetsDao provdePlanetsDao(AppDatabase appDatabase) {
        return appDatabase.planetsDao();
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE 'planets' ('id' INTEGER, 'planetName' TEXT, PRIMARY KEY('id'))");
        }
    };
}
