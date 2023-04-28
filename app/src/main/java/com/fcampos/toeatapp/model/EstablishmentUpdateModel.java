package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.EstablishmentListContract;
import com.fcampos.toeatapp.contract.EstablishmentUpdateContract;
import com.fcampos.toeatapp.domain.Establishment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstablishmentUpdateModel implements EstablishmentUpdateContract.Model {
    @Override
    public void updateEstablishment(EstablishmentUpdateContract.Model.OnUpdateEstablishmentListener listener, long id, Establishment establishment) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<Establishment> callEstablishment = toEatApi.updateEstablishment(id, establishment);
        callEstablishment.enqueue(new Callback<Establishment>() {
            @Override
            public void onResponse(Call<Establishment> call, Response<Establishment> response) {
                Establishment establishment = response.body();
                listener.onUpdateEstablishmentSuccess(establishment);
            }

            @Override
            public void onFailure(Call<Establishment> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onUpdateEstablishmentError(message);
            }
        });
    }
}
