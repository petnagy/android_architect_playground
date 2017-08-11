package com.playground.android_architect_playground.database.entitiy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by petnagy on 2017. 08. 10..
 */
@Entity(tableName = "planets")
public class RecordItem {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String planetName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecordItem that = (RecordItem) o;

        if (id != that.id) return false;
        return planetName != null ? planetName.equals(that.planetName) : that.planetName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (planetName != null ? planetName.hashCode() : 0);
        return result;
    }
}
