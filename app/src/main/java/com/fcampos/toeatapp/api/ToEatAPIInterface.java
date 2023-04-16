package com.fcampos.toeatapp.api;

import androidx.room.Delete;

import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ToEatAPIInterface {

    @GET("user")
    Call<User> getUserByUsernameAndPassword(@Query("username") String username, @Query("password") String password);

    @GET("users/{user_id}")
    Call<User> getUserById(@Path("user_id") long user_id);

    @POST("users")
    Call<User> addUser(@Body User user);

    @GET("establishments")
    Call<List<Establishment>> getEstablishments();

    @GET("establishments/{establishment_id}/comments")
    Call<List<Comment>> getCommentsByEstablishmentId(@Path("establishment_id") long establishment_id);

    @POST("comments")
    Call<Comment> addComment(@Body Comment comment);

    @DELETE("users/{user_id}")
    Call<Void> deleteUser(@Path("user_id") long id);
}
