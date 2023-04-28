package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.FavouriteDeleteContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteDeleteModel implements FavouriteDeleteContract.Model {
    @Override
    public void deleteFavourite(OnDeleteFavouriteListener listener, long id) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<Void> callUser = toEatApi.deleteFavourite(id);
        callUser.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("comments", "llamada desde delete ok -> " + response.code());
                listener.onDeleteFavouriteSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onDeleteMyFavouriteError(message);
            }
        });
    }
}
