package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.UserDeleteContract;
import com.fcampos.toeatapp.contract.UserLoginContract;
import com.fcampos.toeatapp.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDeleteModel implements UserDeleteContract.Model {
    @Override
    public void deleteUser(UserDeleteContract.Model.OnUserDeleteListener listener, long id) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<Void> callUser = toEatApi.deleteUser(id);
        callUser.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("users", "llamada desde delete ok -> " + response.code());
                listener.onUserDeleteSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onUserDeleteError(message);
            }
        });
    }
}
