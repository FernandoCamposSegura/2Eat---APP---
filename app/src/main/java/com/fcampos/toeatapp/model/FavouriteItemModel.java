package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.FavouriteItemContract;
import com.fcampos.toeatapp.domain.Favourite;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteItemModel implements FavouriteItemContract.Model {
    @Override
    public void getFavourite(OnGetFavouriteListener listener, long user_id, long establishment_id) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<List<Favourite>> callFavourites = toEatApi.getFavouriteByUserAndEstablishment(user_id, establishment_id);
        callFavourites.enqueue(new Callback<List<Favourite>>() {
            @Override
            public void onResponse(Call<List<Favourite>> call, Response<List<Favourite>> response) {
                List<Favourite> favouriteList = response.body();
                Log.i("favourites", "llamada desde getFavourite ok -> " + response.code());
                listener.onGetFavouriteSuccess(favouriteList);
            }

            @Override
            public void onFailure(Call<List<Favourite>> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onGetFavouriteError(message);
            }
        });
    }
}
