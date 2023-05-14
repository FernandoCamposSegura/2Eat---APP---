package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.CommentRegisterContract;
import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.model.CommentRegisterModel;
import com.fcampos.toeatapp.view.EstablishmentDetailView;

public class CommentRegisterPresenter implements CommentRegisterContract.Presenter,
    CommentRegisterContract.Model.OnRegisterCommentListener{

    private CommentRegisterModel model;
    private EstablishmentDetailView view;

    public CommentRegisterPresenter(EstablishmentDetailView view) {
        this.view = view;
        model = new CommentRegisterModel();
    }

    @Override
    public void onRegisterCommentSuccess(Comment comment) {
        view.showMessage("Comment published succesfully!");
    }

    @Override
    public void onRegisterCommentError(String message) {
        view.showMessage(message);
    }

    @Override
    public void registerComment(Comment comment) {
        model.registerComment(this, comment);
    }
}
