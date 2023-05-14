package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.User;

public interface UserUpdateContract {
    interface Model {
        interface OnUpdateUserListener {
            void onUpdateUserSuccess(User user);
            void onUpdateUserError(String message);
        }
        void updateUser(UserUpdateContract.Model.OnUpdateUserListener listener, long id, User user);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void updateUser(long id, User user);
    }
}
