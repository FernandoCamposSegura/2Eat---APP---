package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.MyFavouriteListContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.model.MyFavouriteListModel;
import com.fcampos.toeatapp.view.EstablishmentListView;
import com.fcampos.toeatapp.view.MyFavouriteListView;

public class MyFavouriteListPresenter implements MyFavouriteListContract.Presenter, MyFavouriteListContract.Model.OnListMyFavouriteListener {

    private MyFavouriteListModel model;
    private EstablishmentListView view;

    public MyFavouriteListPresenter(EstablishmentListView view) {
        this.model = new MyFavouriteListModel();
        this.view = view;
    }

    @Override
    public void onListMyFavouriteSuccess(Establishment establishment) {
        view.loadEstablishment(establishment);
    }

    @Override
    public void onListMyFavouriteError(String message) {

    }

    @Override
    public void loadMyFavourite(long id) {
        model.loadMyFavourites(this, id);
    }
}
