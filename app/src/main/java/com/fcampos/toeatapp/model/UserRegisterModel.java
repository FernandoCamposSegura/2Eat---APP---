package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.UserLoginContract;
import com.fcampos.toeatapp.contract.UserRegisterContract;
import com.fcampos.toeatapp.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegisterModel implements UserRegisterContract.Model {
    @Override
    public void registerUser(UserRegisterContract.Model.OnRegisterUserListener listener, User user) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<User> callUser = toEatApi.addUser(user);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.i("TAG", "llamada desde modelo ok -> " + response.code());
                listener.onRegisterUserSuccess(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onRegisterUserError(message);
            }
        });
    }
}
