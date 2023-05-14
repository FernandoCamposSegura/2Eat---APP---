package com.fcampos.toeatapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.contract.EstablishmentUpdateContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.User;
import com.fcampos.toeatapp.presenter.EstablishmentUpdatePresenter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class EstablishmentUpdateView extends AppCompatActivity implements EstablishmentUpdateContract.View {

    EditText et_Name_EstablishmentUpdate;
    EditText pt_Description_EstablishmentUpdate;
    EditText et_Address_EstablishmentUpdate;
    EditText num_Latitude_EstablishmentUpdate;
    EditText num_Longitude_EstablishmentUpdate;

    long establishmentId;
    EstablishmentUpdatePresenter establishmentUpdatePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_update_view);

        Intent intent = getIntent();
        establishmentId = intent.getLongExtra("establishmentId", 0);
        if (establishmentId == 0)
            return;

        establishmentUpdatePresenter = new EstablishmentUpdatePresenter(this);
        Button bt_Enter_UserLogin = findViewById(R.id.bt_Update_EstablishmentUpdate);
        bt_Enter_UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_Name_EstablishmentUpdate = findViewById(R.id.pt_Name_EstablishmentUpdate);
                pt_Description_EstablishmentUpdate = findViewById(R.id.pt_Description_EstablishmentUpdate);
                et_Address_EstablishmentUpdate = findViewById(R.id.et_Adress_EstablishmentUpdate);
                num_Latitude_EstablishmentUpdate = findViewById(R.id.num_Latitude_EstablishmentUpdate);
                num_Longitude_EstablishmentUpdate = findViewById(R.id.num_Longitude_EstablishmentUpdate);

                String name = et_Name_EstablishmentUpdate.getText().toString();
                String description = pt_Description_EstablishmentUpdate.getText().toString();
                String address = et_Address_EstablishmentUpdate.getText().toString();
                long latitude = Long.valueOf(num_Latitude_EstablishmentUpdate.getText().toString());
                long longitude = Long.valueOf(num_Longitude_EstablishmentUpdate.getText().toString());

                Establishment establishment = new Establishment(name, description, address, longitude, latitude);

                establishmentUpdatePresenter.updateEstablishment(establishmentId, establishment);
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(et_Name_EstablishmentUpdate, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }
}