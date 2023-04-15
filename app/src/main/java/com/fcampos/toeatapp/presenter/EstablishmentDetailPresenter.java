package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.EstablishmentDetailContract;
import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.model.EstablishmentDetailModel;
import com.fcampos.toeatapp.view.EstablishmentDetailView;

import java.util.List;

public class EstablishmentDetailPresenter implements EstablishmentDetailContract.Presenter,
        EstablishmentDetailContract.Model.OnDetailEstablishmentListener{

    private EstablishmentDetailModel model;
    private EstablishmentDetailView view;

    public EstablishmentDetailPresenter(EstablishmentDetailView view) {
        this.model = new EstablishmentDetailModel();
        this.view = view;
    }

    @Override
    public void onDetailEstablishmentSuccess(List<Comment> commentList) {
        view.showComments(commentList);
    }

    @Override
    public void onDetailEstablishmentError(String message) {
        view.showMessage(message);
    }

    @Override
    public void loadComments(long id) {
        model.loadComments(this, id);
    }
}
