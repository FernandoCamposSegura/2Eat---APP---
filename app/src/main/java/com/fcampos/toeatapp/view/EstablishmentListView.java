package com.fcampos.toeatapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.view_establishments) {
            Intent intent = new Intent(this, EstablishmentListView.class);
            startActivity(intent);
            return  true;
        } else if(item.getItemId() == R.id.view_favourites) {
            Intent intent = new Intent(this, UserProfileView.class);
            startActivity(intent);
            return  true;
        } else if(item.getItemId() == R.id.view_profile) {
            Intent intent = new Intent(this, UserProfileView.class);
            startActivity(intent);
            return  true;
        }
        else if(item.getItemId() == R.id.view_best_list) {
            Intent intent = new Intent(this, UserProfileView.class);
            startActivity(intent);
            return  true;
        }
        return false;
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