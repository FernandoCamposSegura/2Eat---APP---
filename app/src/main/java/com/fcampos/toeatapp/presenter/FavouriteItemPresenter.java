package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.adapter.EstablishmentAdapter;
import com.fcampos.toeatapp.contract.FavouriteItemContract;
import com.fcampos.toeatapp.domain.Favourite;
import com.fcampos.toeatapp.model.FavouriteItemModel;

import java.util.List;

public class FavouriteItemPresenter implements FavouriteItemContract.Presenter, FavouriteItemContract.Model.OnGetFavouriteListener {

    private FavouriteItemModel model;
    private EstablishmentAdapter.EstablishmentHolder view;

    public FavouriteItemPresenter(EstablishmentAdapter.EstablishmentHolder view) {
        this.model = new FavouriteItemModel();
        this.view = view;
    }

    @Override
    public void onGetFavouriteSuccess(List<Favourite> favouriteList) {
        view.deleteFavourite(favouriteList);
    }

    @Override
    public void onGetFavouriteError(String message) {

    }

    @Override
    public void getFavourite(long user_id, long establishment_id) {
        model.getFavourite(this, user_id, establishment_id);
    }
}
