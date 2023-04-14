package com.fcampos.toeatapp.api;

import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ToEatAPIInterface {

    @GET("user")
    Call<User> getUserByUsernameAndPassword(@Query("username") String username, @Query("password") String password);

    @POST("users")
    Call<User> addUser(@Body User user);

    @GET("establishments")
    Call<List<Establishment>> getEstablishments();
}
