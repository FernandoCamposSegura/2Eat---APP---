package com.fcampos.toeatapp.presenter;

import com.fcampos.toeatapp.contract.CommentUpdateContract;
import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.model.CommentUpdateModel;
import com.fcampos.toeatapp.view.CommentUpdateView;

public class CommentUpdatePresenter implements CommentUpdateContract.Presenter, CommentUpdateContract.Model.OnUpdateCommentListener {

    private CommentUpdateModel model;
    private CommentUpdateView view;

    public CommentUpdatePresenter(CommentUpdateView view) {
        this.model = new CommentUpdateModel();
        this.view = view;
    }

    @Override
    public void onUpdateCommentSuccess(Comment comment) {
        view.showMessage("Comment updated successfully!");
    }

    @Override
    public void onUpdateCommentError(String message) {
        view.showMessage(message);
    }

    @Override
    public void updateComment(long id, Comment comment) {
        model.updateComment(this, id, comment);
    }
}
