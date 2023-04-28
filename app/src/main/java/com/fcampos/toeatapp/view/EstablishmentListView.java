package com.fcampos.toeatapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.adapter.EstablishmentAdapter;
import com.fcampos.toeatapp.contract.EstablishmentListContract;
import com.fcampos.toeatapp.contract.MyFavouriteListContract;
import com.fcampos.toeatapp.db.AppDataBase;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.MyFavourite;
import com.fcampos.toeatapp.presenter.EstablishmentListPresenter;
import com.fcampos.toeatapp.presenter.MyFavouriteListPresenter;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentListView extends AppCompatActivity implements EstablishmentListContract.View, MyFavouriteListContract.View {

    private List<Establishment> establishmentList;
    private EstablishmentAdapter adapter;

    private boolean onlyFavourites;

    private EstablishmentListPresenter establishmentListPresenter;
    private MyFavouriteListPresenter myFavouriteListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_list_view);

        establishmentListPresenter = new EstablishmentListPresenter(this);
        myFavouriteListPresenter = new MyFavouriteListPresenter(this);

        establishmentList = new ArrayList<>();

        Intent intent = getIntent();
        onlyFavourites = intent.getBooleanExtra("onlyFavourites", false);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!onlyFavourites) {

            establishmentListPresenter.loadEstablishments();
            initializeRecyclerView();
        }
        else
            loadFavouriteList();

    }

    private void initializeRecyclerView() {
        System.out.println("Entre aquí");
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
            Intent intent = new Intent(this, EstablishmentListView.class);
            intent.putExtra("onlyFavourites", true);
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
        else if(item.getItemId() == R.id.view_map_list) {
            Intent intent = new Intent(this, EstablishmentMapView.class);
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
    public void loadEstablishment(Establishment establishment) {
        establishmentList.add(establishment);
        adapter.notifyDataSetChanged();
    }

    public void loadFavouriteList() {
        List<MyFavourite> myFavouriteList = new ArrayList<>();

        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "myfavourite")
                .allowMainThreadQueries().build();
        myFavouriteList = db.myFavouriteDAO().getAll();

        for (MyFavourite myFavourite : myFavouriteList
        ) {
            myFavouriteListPresenter.loadMyFavourite(myFavourite.getEstablishment_id());
        }
        initializeRecyclerView();
    }
}