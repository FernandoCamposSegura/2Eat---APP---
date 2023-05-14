package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.EstablishmentListContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.model.EstablishmentListModel;
import com.fcampos.toeatapp.view.EstablishmentListView;

import java.util.List;

public class EstablishmentListPresenter implements EstablishmentListContract.Presenter,
        EstablishmentListContract.Model.OnListEstablishmentListener {

    EstablishmentListModel model;
    EstablishmentListView view;

    public EstablishmentListPresenter(EstablishmentListView view) {
        this.model = new EstablishmentListModel();
        this.view = view;
    }

    @Override
    public void onListEstablishmentSuccess(List<Establishment> establishmentList) {
        view.showEstablishments(establishmentList);
    }

    @Override
    public void onListEstablishmentError(String message) {

    }

    @Override
    public void loadEstablishments(String filter) {
        model.loadEstablishments(this, filter);
    }
}
