package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.EstablishmentListContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.model.EstablishmentListModel;
import com.fcampos.toeatapp.view.EstablishmentListView;
import com.fcampos.toeatapp.view.EstablishmentMapView;

import java.util.List;

public class EstablishmentMapPresenter implements EstablishmentListContract.Presenter, EstablishmentListContract.Model.OnListEstablishmentListener {

    private EstablishmentListModel model;
    private EstablishmentMapView view;

    public EstablishmentMapPresenter(EstablishmentMapView view) {
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
    public void loadEstablishments() {
        model.loadEstablishments(this);
    }
}
