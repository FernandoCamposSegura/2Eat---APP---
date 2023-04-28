package com.fcampos.toeatapp.api;

import androidx.room.Delete;

import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.Favourite;
import com.fcampos.toeatapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ToEatAPIInterface {

    @GET("user")
    Call<User> getUserByUsernameAndPassword(@Query("username") String username, @Query("password") String password);

    @GET("favourites")
    Call<List<Favourite>> getFavouriteByUserAndEstablishment(@Query("user_id") long user_id, @Query("establishment_id") long establishment_id);

    @GET("users/{user_id}")
    Call<User> getUserById(@Path("user_id") long user_id);

    @POST("users")
    Call<User> addUser(@Body User user);

    @GET("establishments")
    Call<List<Establishment>> getEstablishments();

    @GET("establishments/{establishment_id}")
    Call<Establishment> getEstablishmentById(@Path("establishment_id") long establishment_id);

    @GET("establishments/{establishment_id}/comments")
    Call<List<Comment>> getCommentsByEstablishmentId(@Path("establishment_id") long establishment_id);

    @POST("comments")
    Call<Comment> addComment(@Body Comment comment);

    @POST("favourites")
    Call<Favourite> addFavourite(@Body Favourite favourite);

    @PUT("establishments/{establishment_id}")
    Call<Establishment> updateEstablishment(@Path("establishment_id") long id, @Body Establishment establishment);

    @PUT("comments/{comment_id}")
    Call<Comment> updateComment(@Path("comment_id") long id, @Body Comment comment);

    @DELETE("users/{user_id}")
    Call<Void> deleteUser(@Path("user_id") long id);

    @DELETE("favourites/{favourite_id}")
    Call<Void> deleteFavourite(@Path("favourite_id") long id);
}
