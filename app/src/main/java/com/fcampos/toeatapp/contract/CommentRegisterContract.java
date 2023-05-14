package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Comment;

public interface CommentRegisterContract {
    interface Model {
        interface OnRegisterCommentListener {
            void onRegisterCommentSuccess(Comment comment);
            void onRegisterCommentError(String message);
        }
        void registerComment(CommentRegisterContract.Model.OnRegisterCommentListener listener, Comment comment);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void registerComment(Comment comment);
    }
}
