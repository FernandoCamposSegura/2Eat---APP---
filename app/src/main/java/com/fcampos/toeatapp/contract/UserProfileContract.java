package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.domain.User;

public interface UserProfileContract {
    interface Model {
        interface OnSearchUserListener {
            void onSearchUserSuccess(User user);
            void onSearchUsertError(String message);
        }
        void findUser(UserProfileContract.Model.OnSearchUserListener listener, long id);
    }

    interface View {
        void showMessage(String message);
        void loadUser(User user);
    }

    interface Presenter {
        void findUser(long id);
    }
}
