package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.EstablishmentListContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstablishmentListModel implements EstablishmentListContract.Model {
    @Override
    public void loadEstablishments(OnListEstablishmentListener listener) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<List<Establishment>> callEstablishments = toEatApi.getEstablishments();
        callEstablishments.enqueue(new Callback<List<Establishment>>() {
            @Override
            public void onResponse(Call<List<Establishment>> call, Response<List<Establishment>> response) {
                List<Establishment> establishmentList = response.body();
                Log.i("establishemnts", "llamada desde modelo ok -> " + response.code());
                listener.onListEstablishmentSuccess(establishmentList);
            }

            @Override
            public void onFailure(Call<List<Establishment>> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onListEstablishmentError(message);
            }
        });
    }
}
