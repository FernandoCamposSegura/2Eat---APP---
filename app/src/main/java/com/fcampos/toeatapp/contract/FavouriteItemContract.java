package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Favourite;

import java.util.List;

public interface FavouriteItemContract {
    interface Model {
        interface OnGetFavouriteListener {
            void onGetFavouriteSuccess(List<Favourite> favouriteList);
            void onGetFavouriteError(String message);
        }
        void getFavourite(FavouriteItemContract.Model.OnGetFavouriteListener listener, long user_id, long establishment_id);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void getFavourite(long user_id, long establishment_id);
    }
}
