package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.UserLoginContract;
import com.fcampos.toeatapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginModel implements UserLoginContract.Model {
    @Override
    public void loginUser(OnLoginUserListener listener, String username, String password) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<User> callUser = toEatApi.getUserByUsernameAndPassword(username, password);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.d("user", "llamada desde modelo ok");
                listener.onLoginUserSuccess(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onLoginUserError(message);
            }
        });
    }
}
