package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Establishment;

import java.util.List;

public interface EstablishmentUpdateContract {
    interface Model {
        interface OnUpdateEstablishmentListener {
            void onUpdateEstablishmentSuccess(Establishment establishment);
            void onUpdateEstablishmentError(String message);
        }
        void updateEstablishment(EstablishmentUpdateContract.Model.OnUpdateEstablishmentListener listener, long id, Establishment establishment);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void updateEstablishment(long id, Establishment establishment);
    }
}
