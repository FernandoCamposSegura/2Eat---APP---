package com.fcampos.toeatapp.api;

import static com.fcampos.toeatapp.api.Constants.BASE_URL;

import com.fcampos.toeatapp.domain.Role;
import com.fcampos.toeatapp.domain.RoleTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToEatAPI {
    public static ToEatAPIInterface buildInstance() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleTypeAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ToEatAPIInterface.class);
    }
}
