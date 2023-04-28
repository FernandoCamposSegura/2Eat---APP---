package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.EstablishmentUpdateContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.model.EstablishmentUpdateModel;
import com.fcampos.toeatapp.view.EstablishmentUpdateView;

public class EstablishmentUpdatePresenter implements EstablishmentUpdateContract.Presenter, EstablishmentUpdateContract.Model.OnUpdateEstablishmentListener {

    private EstablishmentUpdateModel model;
    private EstablishmentUpdateView view;

    public EstablishmentUpdatePresenter(EstablishmentUpdateView view) {
        this.model = new EstablishmentUpdateModel();
        this.view = view;
    }

    @Override
    public void onUpdateEstablishmentSuccess(Establishment establishment) {
        view.showMessage("Establishment update successfully!");
    }

    @Override
    public void onUpdateEstablishmentError(String message) {
        view.showMessage(message);
    }

    @Override
    public void updateEstablishment(long id, Establishment establishment) {
        model.updateEstablishment(this, id, establishment);
    }
}
