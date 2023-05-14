package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.CommentUpdateContract;
import com.fcampos.toeatapp.domain.Comment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentUpdateModel implements CommentUpdateContract.Model {
    @Override
    public void updateComment(OnUpdateCommentListener listener, long id, Comment comment) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<Comment> callComment = toEatApi.updateComment(id, comment);
        callComment.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                Comment comment = response.body();
                listener.onUpdateCommentSuccess(comment);
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onUpdateCommentError(message);
            }
        });
    }
}
