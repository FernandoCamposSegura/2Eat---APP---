package com.fcampos.toeatapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.adapter.CommentAdapter;
import com.fcampos.toeatapp.adapter.EstablishmentAdapter;
import com.fcampos.toeatapp.contract.EstablishmentDetailContract;
import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.presenter.EstablishmentDetailPresenter;
import com.fcampos.toeatapp.presenter.EstablishmentListPresenter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentDetailView extends AppCompatActivity implements EstablishmentDetailContract.View {

    private List<Comment> commentList;
    private CommentAdapter adapter;
    private EstablishmentDetailPresenter presenter;
    long establishmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_detail_view);

        presenter = new EstablishmentDetailPresenter(this);

        Intent intent = getIntent();
        establishmentId = intent.getLongExtra("establishmentId", 0);
        if (establishmentId == 0)
            return;

        initializeRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadComments(establishmentId);
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