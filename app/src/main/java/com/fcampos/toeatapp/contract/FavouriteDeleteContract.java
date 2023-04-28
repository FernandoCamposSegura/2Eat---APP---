package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Establishment;

public interface FavouriteDeleteContract {
    interface Model {
        interface OnDeleteFavouriteListener {
            void onDeleteFavouriteSuccess();
            void onDeleteMyFavouriteError(String message);
        }
        void deleteFavourite(FavouriteDeleteContract.Model.OnDeleteFavouriteListener listener, long id);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void deleteFavourite(long id);
    }
}
