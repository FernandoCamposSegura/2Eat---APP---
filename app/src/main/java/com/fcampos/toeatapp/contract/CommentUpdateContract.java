package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Comment;

public interface CommentUpdateContract {
    interface Model {
        interface OnUpdateCommentListener {
            void onUpdateCommentSuccess(Comment comment);
            void onUpdateCommentError(String message);
        }
        void updateComment(CommentUpdateContract.Model.OnUpdateCommentListener listener, long id, Comment comment);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void updateComment(long id, Comment comment);
    }
}
