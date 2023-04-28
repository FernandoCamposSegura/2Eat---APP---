package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.domain.Favourite;

public interface FavouriteRegisterContract {
    interface Model {
        interface OnRegisterFavouriteListener {
            void onRegisterFavouriteSuccess(Favourite favourite);
            void onRegisterFavouriteError(String message);
        }
        void registerFavourite(FavouriteRegisterContract.Model.OnRegisterFavouriteListener listener, Favourite favourite);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void registerFavourite(Favourite favourite);
    }
}
