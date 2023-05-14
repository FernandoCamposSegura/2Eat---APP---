package com.fcampos.toeatapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.adapter.EstablishmentAdapter;
import com.fcampos.toeatapp.contract.MyFavouriteListContract;
import com.fcampos.toeatapp.db.AppDataBase;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.MyFavourite;
import com.fcampos.toeatapp.presenter.MyFavouriteListPresenter;

import java.util.ArrayList;
import java.util.List;

public class MyFavouriteListView extends AppCompatActivity {

    List<Establishment> establishmentList;
    private EstablishmentAdapter adapter;
    List<MyFavourite> myFavouriteList;

    MyFavouriteListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite_list_view);

        establishmentList = new ArrayList<>();

        //presenter = new MyFavouriteListPresenter(this);

        loadEstablishments();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initializeRecyclerView() {
        establishmentList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rv_MyFavouriteList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EstablishmentAdapter(this, establishmentList);
        recyclerView.setAdapter(adapter);
    }

    public void loadEstablishments() {
        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "myfavourite")
                .allowMainThreadQueries().build();
        myFavouriteList = db.myFavouriteDAO().getAll();

        for (MyFavourite myFavourite : myFavouriteList
        ) {
            System.out.println(myFavourite.getEstablishment_id());
            presenter.loadMyFavourite(myFavourite.getEstablishment_id());
        }

        initializeRecyclerView();
    }


}