package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.User;

import java.util.List;

public interface EstablishmentListContract {
    interface Model {
        interface OnListEstablishmentListener {
            void onListEstablishmentSuccess(List<Establishment> establishmentList);
            void onListEstablishmentError(String message);
        }
        void loadEstablishments(OnListEstablishmentListener listener);
    }

    interface View {
        void showEstablishments(List<Establishment> establishmentList);
    }

    interface Presenter {
        void loadEstablishments();
    }
}
