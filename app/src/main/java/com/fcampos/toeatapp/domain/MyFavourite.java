package com.fcampos.toeatapp.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyFavourite {
    @PrimaryKey
    @NonNull
    private long establishment_id;

    public MyFavourite(long establishment_id) {
        this.establishment_id = establishment_id;
    }

    public long getEstablishment_id() {
        return establishment_id;
    }

    public void setEstablishment_id(long establishment_id) {
        this.establishment_id = establishment_id;
    }
}
