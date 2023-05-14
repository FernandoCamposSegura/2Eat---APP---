package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.MyFavouriteListContract;
import com.fcampos.toeatapp.domain.Establishment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFavouriteListModel implements MyFavouriteListContract.Model {
    @Override
    public void loadMyFavourites(OnListMyFavouriteListener listener, long id) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<Establishment> callEstablishments = toEatApi.getEstablishmentById(id);
        callEstablishments.enqueue(new Callback<Establishment>() {
            @Override
            public void onResponse(Call<Establishment> call, Response<Establishment> response) {
                Establishment establishment = response.body();
                Log.i("establishemnts", "llamada desde modelo ok -> " + response.code());
                listener.onListMyFavouriteSuccess(establishment);
            }

            @Override
            public void onFailure(Call<Establishment> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onListMyFavouriteError(message);
            }
        });
    }
}
