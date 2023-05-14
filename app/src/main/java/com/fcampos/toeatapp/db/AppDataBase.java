package com.fcampos.toeatapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.fcampos.toeatapp.domain.MyFavourite;

@Database(entities = {MyFavourite.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MyFavouriteDAO myFavouriteDAO();
}
