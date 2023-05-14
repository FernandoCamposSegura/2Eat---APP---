package com.fcampos.toeatapp.contract;

import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.domain.Establishment;

import java.util.List;

public interface EstablishmentDetailContract {
    interface Model {
        interface OnDetailEstablishmentListener {
            void onDetailEstablishmentSuccess(List<Comment> commentList);
            void onDetailEstablishmentError(String message);
        }
        void loadComments(EstablishmentDetailContract.Model.OnDetailEstablishmentListener listener, long id);
    }

    interface View {
        void showComments(List<Comment> comments);
        void showMessage(String message);
    }

    interface Presenter {
        void loadComments(long id);
    }
}
