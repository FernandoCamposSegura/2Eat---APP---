package com.fcampos.toeatapp.api;

import com.fcampos.toeatapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ToEatAPIInterface {

    @GET("user")
    Call<User> getUserByUsernameAndPassword(@Query("username") String username, @Query("password") String password);
}
