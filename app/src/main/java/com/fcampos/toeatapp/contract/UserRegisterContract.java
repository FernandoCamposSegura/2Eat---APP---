package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.User;

public interface UserRegisterContract {
    interface Model {
        interface OnRegisterUserListener {
            void onRegisterUserSuccess(User user);
            void onRegisterUserError(String message);
        }
        void registerUser(OnRegisterUserListener listener, User user);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void registerUser(User user);
    }
}
