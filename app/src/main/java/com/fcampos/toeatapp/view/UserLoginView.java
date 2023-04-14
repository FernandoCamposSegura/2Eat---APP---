package com.fcampos.toeatapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.presenter.UserLoginPresenter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class UserLoginView extends AppCompatActivity {

    EditText pt_Username_UserLogin;
    EditText et_Password_UserLogin;
    UserLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new UserLoginPresenter(this);
        Button bt_Enter_UserLogin = findViewById(R.id.bt_Enter_UserLogin);
        bt_Enter_UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pt_Username_UserLogin = findViewById(R.id.pt_Username_UserLogin);
                et_Password_UserLogin = findViewById(R.id.et_Password_UserLogin);

                String username = pt_Username_UserLogin.getText().toString();
                String password = et_Password_UserLogin.getText().toString();

                presenter.loadUserByUsernameAndPassword(username, password);
            }
        });
    }

    public void showMessage(String message) {
        Snackbar.make(pt_Username_UserLogin, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }
}