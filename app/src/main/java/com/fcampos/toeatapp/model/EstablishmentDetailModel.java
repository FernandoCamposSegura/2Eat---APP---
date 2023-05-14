package com.fcampos.toeatapp.model;

import android.util.Log;

import com.fcampos.toeatapp.api.ToEatAPI;
import com.fcampos.toeatapp.api.ToEatAPIInterface;
import com.fcampos.toeatapp.contract.EstablishmentDetailContract;
import com.fcampos.toeatapp.domain.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstablishmentDetailModel implements EstablishmentDetailContract.Model {
    @Override
    public void loadComments(OnDetailEstablishmentListener listener, long id) {
        ToEatAPIInterface toEatApi = ToEatAPI.buildInstance();
        Call<List<Comment>> callComments = toEatApi.getCommentsByEstablishmentId(id);
        callComments.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> commentList = response.body();
                for (Comment comment: commentList
                     ) {
                    System.out.println(comment.getId());
                    System.out.println(comment.getMessage());
                    System.out.println(comment.getDatePost());
                    System.out.println(comment.getRating());

                }
                Log.i("establishemnts", "llamada desde modelo ok -> " + response.code());
                listener.onDetailEstablishmentSuccess(commentList);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onDetailEstablishmentError(message);
            }
        });
    }
}
