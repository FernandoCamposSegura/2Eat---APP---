package com.fcampos.toeatapp.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.contract.CommentUpdateContract;
import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.presenter.CommentUpdatePresenter;
import com.fcampos.toeatapp.presenter.EstablishmentDetailPresenter;

import java.time.LocalDate;

public class CommentUpdateView extends AppCompatActivity implements CommentUpdateContract.View {

    private RatingBar ratingBar_CommentUpdate;
    private EditText et_Message_CommentUpdate;

    private CommentUpdatePresenter commentUpdatePresenter;
    private double starsCount = 0;
    private long commentId;
    private long establishmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_update_view);

        commentUpdatePresenter = new CommentUpdatePresenter(this);

        Intent intent = getIntent();
        commentId = intent.getLongExtra("commentId", 0);
        if (commentId == 0)
            return;

        establishmentId = intent.getLongExtra("establishmentId", 0);
        if (establishmentId == 0)
            return;

        System.out.println(establishmentId + ", " + commentId );

        ratingBar_CommentUpdate = findViewById(R.id.ratingBar_CommentUpdate);
        ratingBar_CommentUpdate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Almacena la cantidad de estrellas seleccionadas en una variable
                starsCount = rating;
            }
        });

        Button bt_Update_CommentUpdate = findViewById(R.id.bt_Update_CommentUpdate);
        bt_Update_CommentUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et_Message_CommentUpdate = findViewById(R.id.et_Message_CommentUpdate);
                String message = et_Message_CommentUpdate.getText().toString();
                String datePost = String.valueOf(LocalDate.now());

                System.out.println(message + ", " + starsCount );

                Comment comment = new Comment(starsCount, message, datePost, UserLoginView.USER_ID, establishmentId);

                commentUpdatePresenter.updateComment(commentId, comment);
            }
        });
    }

    @Override
    public void showMessage(String message) {

    }
}