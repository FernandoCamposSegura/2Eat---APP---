package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.UserProfileContract;
import com.fcampos.toeatapp.domain.User;
import com.fcampos.toeatapp.model.UserProfileModel;
import com.fcampos.toeatapp.view.UserProfileView;

public class UserProfilePresenter implements UserProfileContract.Presenter, UserProfileContract.Model.OnSearchUserListener{

    private UserProfileModel model;
    private UserProfileView view;

    public UserProfilePresenter(UserProfileView view) {
        this.model = new UserProfileModel();
        this.view = view;
    }

    @Override
    public void onSearchUserSuccess(User user) {
        view.loadUser(user);
    }

    @Override
    public void onSearchUsertError(String message) {
        view.showMessage(message);
    }

    @Override
    public void findUser(long id) {
        model.findUser(this, id);
    }
}
