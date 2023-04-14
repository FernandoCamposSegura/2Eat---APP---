package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.User;

import java.util.List;

public interface UserLoginContract {
    interface Model {
        interface OnLoginUserListener {
            void onLoginUserSuccess(User user);
            void onLoginUserError(String message);
        }
        void loginUser(OnLoginUserListener listener, String username, String password);
    }

    interface View {
        //void showTasks(List<User> tasks);
        void showMessage(String message);
    }

    interface Presenter {
        void loadUserByUsernameAndPassword(String username, String password);
    }
}
