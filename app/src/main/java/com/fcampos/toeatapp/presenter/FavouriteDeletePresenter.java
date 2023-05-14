package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.adapter.EstablishmentAdapter;
import com.fcampos.toeatapp.contract.FavouriteDeleteContract;
import com.fcampos.toeatapp.model.FavouriteDeleteModel;

public class FavouriteDeletePresenter implements FavouriteDeleteContract.Presenter, FavouriteDeleteContract.Model.OnDeleteFavouriteListener {

    private FavouriteDeleteModel model;
    private EstablishmentAdapter.EstablishmentHolder view;

    public FavouriteDeletePresenter(EstablishmentAdapter.EstablishmentHolder view) {
        this.model = new FavouriteDeleteModel();
        this.view = view;
    }

    @Override
    public void onDeleteFavouriteSuccess() {
        view.showMessage("Deleted to your favourite list!");
    }

    @Override
    public void onDeleteMyFavouriteError(String message) {
        view.showMessage(message);
    }

    @Override
    public void deleteFavourite(long id) {
        model.deleteFavourite(this, id);
    }
}
