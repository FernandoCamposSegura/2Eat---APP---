package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.UserRegisterContract;
import com.fcampos.toeatapp.domain.User;
import com.fcampos.toeatapp.model.UserRegisterModel;
import com.fcampos.toeatapp.view.UserRegisterView;

public class UserRegisterPresenter implements UserRegisterContract.Presenter,
        UserRegisterContract.Model.OnRegisterUserListener {

    private UserRegisterModel model;
    private UserRegisterView view;

    public UserRegisterPresenter(UserRegisterView view) {
        this.model = new UserRegisterModel();
        this.view = view;
    }

    @Override
    public void onRegisterUserSuccess(User user) {
        view.showMessage("Usuario registrado correctamente");
    }

    @Override
    public void onRegisterUserError(String message) {
        view.showMessage(message);
    }

    @Override
    public void registerUser(User user) {
        model.registerUser(this, user);
    }
}
