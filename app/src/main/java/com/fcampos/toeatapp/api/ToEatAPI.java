package com.fcampos.toeatapp.api;

import static com.fcampos.toeatapp.api.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToEatAPI {
    public static ToEatAPIInterface buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ToEatAPIInterface.class);
    }
}
