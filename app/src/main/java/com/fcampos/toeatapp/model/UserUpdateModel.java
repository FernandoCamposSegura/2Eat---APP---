package com.fcampos.toeatapp.model;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.UserUpdateContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserUpdateModel implements UserUpdateContract.Model {
    @Override
    public void updateUser(OnUpdateUserListener listener, long id, User user) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<User> callUser = toEatApi.updateUser(id, user);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                listener.onUpdateUserSuccess(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onUpdateUserError(message);
            }
        });
    }
}
