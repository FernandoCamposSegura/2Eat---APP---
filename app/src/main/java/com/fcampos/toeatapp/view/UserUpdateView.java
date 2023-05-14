package com.fcampos.toeatapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.contract.UserUpdateContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.Role;
import com.fcampos.toeatapp.domain.User;
import com.fcampos.toeatapp.presenter.EstablishmentUpdatePresenter;
import com.fcampos.toeatapp.presenter.UserUpdatePresenter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class UserUpdateView extends AppCompatActivity implements UserUpdateContract.View {

    EditText pt_Username_UserUpdate;
    EditText pt_Email_UserUpdate;
    EditText et_Password_UserUpdate;

    UserUpdatePresenter userUpdatePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_view);

        userUpdatePresenter = new UserUpdatePresenter(this);
        Button bt_Enter_UserLogin = findViewById(R.id.bt_Update_UserUpdate);
        bt_Enter_UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pt_Username_UserUpdate = findViewById(R.id.pt_Username_UserUpdate);
                pt_Email_UserUpdate = findViewById(R.id.pt_Email_UserUpdate);
                et_Password_UserUpdate = findViewById(R.id.et_Password_UserUpdate);

                String username = pt_Username_UserUpdate.getText().toString();
                String email = pt_Email_UserUpdate.getText().toString();
                String password = et_Password_UserUpdate.getText().toString();

                User user = new User(username, email, password, Role.USER);

                userUpdatePresenter.updateUser(UserLoginView.USER_ID, user);
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(pt_Username_UserUpdate, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }
}