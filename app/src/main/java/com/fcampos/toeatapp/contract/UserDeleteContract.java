package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.User;

public interface UserDeleteContract {
    interface Model {
        interface OnUserDeleteListener {
            void onUserDeleteSuccess();
            void onUserDeleteError(String message);
        }
        void deleteUser(UserDeleteContract.Model.OnUserDeleteListener listener, long id);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void deleteUser(long id);
    }
}
