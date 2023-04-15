package com.fcampos.toeatapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.adapter.EstablishmentAdapter;
import com.fcampos.toeatapp.contract.EstablishmentListContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.model.EstablishmentListModel;
import com.fcampos.toeatapp.presenter.EstablishmentListPresenter;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentListView extends AppCompatActivity implements EstablishmentListContract.View {

    private List<Establishment> establishmentList;
    private EstablishmentAdapter adapter;
    private EstablishmentListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_list_view);

        presenter = new EstablishmentListPresenter(this);

        initializeRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadEstablishments();
    }

    private void initializeRecyclerView() {
        establishmentList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rv_EstablishmentList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EstablishmentAdapter(this, establishmentList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showEstablishments(List<Establishment> establishments) {
        establishmentList.clear();
        establishmentList.addAll(establishments);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {

    }
}