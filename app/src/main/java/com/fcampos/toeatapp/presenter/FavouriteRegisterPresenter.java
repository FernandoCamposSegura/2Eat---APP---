package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.adapter.EstablishmentAdapter;
import com.fcampos.toeatapp.contract.FavouriteRegisterContract;
import com.fcampos.toeatapp.domain.Favourite;
import com.fcampos.toeatapp.model.FavouriteRegisterModel;
import com.fcampos.toeatapp.view.EstablishmentListView;

public class FavouriteRegisterPresenter implements FavouriteRegisterContract.Presenter, FavouriteRegisterContract.Model.OnRegisterFavouriteListener {

    private FavouriteRegisterModel model;
    private EstablishmentAdapter view;

    public FavouriteRegisterPresenter(EstablishmentAdapter view) {
        this.view = view;
        this.model = new FavouriteRegisterModel();
    }

    @Override
    public void onRegisterFavouriteSuccess(Favourite favourite) {
        view.showMessage("Added to your favourite list!");
    }

    @Override
    public void onRegisterFavouriteError(String message) {
        view.showMessage("An error occurred!");
    }

    @Override
    public void registerFavourite(Favourite favourite) {
        model.registerFavourite(this, favourite);
    }
}
