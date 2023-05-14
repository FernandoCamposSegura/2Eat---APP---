package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.CommentRegisterContract;
import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRegisterModel implements CommentRegisterContract.Model{

    @Override
    public void registerComment(OnRegisterCommentListener listener, Comment comment) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<Comment> callComment = toEatApi.addComment(comment);
        callComment.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                Comment comment = response.body();
                Log.i("comments", "llamada desde register ok -> " + response.code());
                listener.onRegisterCommentSuccess(comment);
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onRegisterCommentError(message);
            }
        });
    }
}
