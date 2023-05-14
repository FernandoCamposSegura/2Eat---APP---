package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.Favourite;
import com.fcampos.toeatapp.domain.MyFavourite;

import java.util.List;

public interface MyFavouriteListContract {
    interface Model {
        interface OnListMyFavouriteListener {
            void onListMyFavouriteSuccess(Establishment establishment);
            void onListMyFavouriteError(String message);
        }
        void loadMyFavourites(MyFavouriteListContract.Model.OnListMyFavouriteListener listener, long id);
    }

    interface View {
        void loadEstablishment(Establishment establishment);
    }

    interface Presenter {
        void loadMyFavourite(long id);
    }
}
