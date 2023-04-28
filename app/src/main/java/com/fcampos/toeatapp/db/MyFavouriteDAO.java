package com.fcampos.toeatapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.fcampos.toeatapp.domain.MyFavourite;

import java.util.List;

@Dao
public interface MyFavouriteDAO {

    @Query("SELECT * FROM myfavourite")
    List<MyFavourite> getAll();

    @Query("SELECT * FROM myfavourite WHERE establishment_id = :establishment_id")
    MyFavourite getMyFavouriteById(long establishment_id);

    @Insert
    void insert(MyFavourite myFavourite);

    @Delete
    void delete(MyFavourite myFavourite);
}
