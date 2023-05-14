package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.FavouriteRegisterContract;
import com.fcampos.toeatapp.domain.Favourite;
import com.fcampos.toeatapp.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteRegisterModel implements FavouriteRegisterContract.Model {

    @Override
    public void registerFavourite(OnRegisterFavouriteListener listener, Favourite favourite) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<Favourite> favouriteCall = toEatApi.addFavourite(favourite);
        favouriteCall.enqueue(new Callback<Favourite>() {
            @Override
            public void onResponse(Call<Favourite> call, Response<Favourite> response) {
                Favourite favourite = response.body();
                Log.i("TAG", "llamada desde modelo ok -> " + response.code());
                listener.onRegisterFavouriteSuccess(favourite);
            }

            @Override
            public void onFailure(Call<Favourite> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onRegisterFavouriteError(message);
            }
        });
    }
}
