package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.UserUpdateContract;
import com.fcampos.toeatapp.domain.User;
import com.fcampos.toeatapp.model.UserUpdateModel;
import com.fcampos.toeatapp.view.UserUpdateView;

public class UserUpdatePresenter implements UserUpdateContract.Presenter, UserUpdateContract.Model.OnUpdateUserListener {

    private UserUpdateModel model;
    private UserUpdateView view;

    public UserUpdatePresenter(UserUpdateView view) {
        this.view = view;
        this.model = new UserUpdateModel();
    }

    @Override
    public void onUpdateUserSuccess(User user) {
        view.showMessage("Profile update successfully!");
    }

    @Override
    public void onUpdateUserError(String message) {
        view.showMessage(message);
    }

    @Override
    public void updateUser(long id, User user) {
        model.updateUser(this, id, user);
    }
}
