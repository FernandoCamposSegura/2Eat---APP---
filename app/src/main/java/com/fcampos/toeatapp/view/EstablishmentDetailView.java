package com.fcampos.toeatapp.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.adapter.CommentAdapter;
import com.fcampos.toeatapp.contract.CommentRegisterContract;
import com.fcampos.toeatapp.contract.EstablishmentDetailContract;
import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.presenter.CommentRegisterPresenter;
import com.fcampos.toeatapp.presenter.EstablishmentDetailPresenter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstablishmentDetailView extends AppCompatActivity implements EstablishmentDetailContract.View, CommentRegisterContract.View {

    private RatingBar ratingBar;
    private Button bt_Publish_EstablishmentDetail;
    private EditText et_Message_EstablishmentDetail;

    private List<Comment> commentList;
    private CommentAdapter adapter;
    private CommentRegisterPresenter commentRegisterPresenter;
    private EstablishmentDetailPresenter establishmentDetailPresenter;
    private int starsCount = 0;
    long establishmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_detail_view);

        establishmentDetailPresenter = new EstablishmentDetailPresenter(this);
        commentRegisterPresenter = new CommentRegisterPresenter(this);

        Intent intent = getIntent();
        establishmentId = intent.getLongExtra("establishmentId", 0);
        if (establishmentId == 0)
            return;

        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Almacena la cantidad de estrellas seleccionadas en una variable
                starsCount = (int) rating;
            }
        });

        bt_Publish_EstablishmentDetail = findViewById(R.id.bt_Publish_EstablishmentDetail);
        bt_Publish_EstablishmentDetail.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                et_Message_EstablishmentDetail = findViewById(R.id.et_Message_EstablishmeentDetail);
                String message = et_Message_EstablishmentDetail.getText().toString();
                String datePost = String.valueOf(LocalDate.now());

                System.out.println(starsCount);
                System.out.println(message);
                System.out.println(datePost);
                System.out.println(UserLoginView.USER_ID);
                System.out.println(establishmentId);

                Comment comment = new Comment(starsCount, message, datePost, UserLoginView.USER_ID, establishmentId);

                publishComment(comment);
            }
        });

        initializeRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        establishmentDetailPresenter.loadComments(establishmentId);
    }

    private void initializeRecyclerView() {
        commentList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rv_CommentList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CommentAdapter(this, commentList);
        recyclerView.setAdapter(adapter);
    }

    public void publishComment(Comment comment) {
        commentRegisterPresenter.registerComment(comment);
    }

    @Override
    public void showComments(List<Comment> comments) {
        commentList.clear();
        commentList.addAll(comments);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}