package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.UserLoginContract;
import com.fcampos.toeatapp.domain.User;
import com.fcampos.toeatapp.model.UserLoginModel;
import com.fcampos.toeatapp.view.UserLoginView;

public class UserLoginPresenter implements UserLoginContract.Presenter,
    UserLoginContract.Model.OnLoginUserListener {

    UserLoginModel model;
    UserLoginView view;

    public UserLoginPresenter(UserLoginView view) {
        this.model = new UserLoginModel();
        this.view = view;
    }

    @Override
    public void onLoginUserSuccess(User user) {
        if(user != null) {
            view.showMessage("Enhorabuena");
        } else {
            view.showMessage("Usuario o contraseña incorrectos");
        }
    }

    @Override
    public void onLoginUserError(String message) {
        view.showMessage(message);
    }

    @Override
    public void loadUserByUsernameAndPassword(String username, String password) {
        model.loginUser(this, username, password);
    }
}
