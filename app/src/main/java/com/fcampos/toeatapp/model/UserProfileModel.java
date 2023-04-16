package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.UserProfileContract;
import com.fcampos.toeatapp.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileModel implements UserProfileContract.Model {
    @Override
    public void findUser(OnSearchUserListener listener, long id) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<User> callUser = toEatApi.getUserById(id);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.i("comments", "llamada desde register ok -> " + response.code());
                listener.onSearchUserSuccess(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onSearchUsertError(message);
            }
        });
    }
}
