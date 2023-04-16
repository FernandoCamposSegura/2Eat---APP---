package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.UserDeleteContract;
import com.fcampos.toeatapp.model.UserDeleteModel;
import com.fcampos.toeatapp.view.UserProfileView;

public class UserDeletePresenter implements UserDeleteContract.Presenter, UserDeleteContract.Model.OnUserDeleteListener {

    private UserDeleteModel model;
    private UserProfileView view;

    public UserDeletePresenter(UserProfileView view) {
        this.view = view;
        this.model = new UserDeleteModel();
    }

    @Override
    public void onUserDeleteSuccess() {
        view.showMessage("The account has been deleted!");
    }

    @Override
    public void onUserDeleteError(String message) {
        view.showMessage(message);
    }

    @Override
    public void deleteUser(long id) {
        model.deleteUser(this, id);
    }
}
