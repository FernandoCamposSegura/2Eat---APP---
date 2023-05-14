package com.fcampos.toeatapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.contract.UserRegisterContract;
import com.fcampos.toeatapp.domain.User;
import com.fcampos.toeatapp.presenter.UserRegisterPresenter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class UserRegisterView extends AppCompatActivity implements UserRegisterContract.View {

    EditText pt_Username_UserRegister;
    EditText pt_Email_UserRegister;
    EditText et_Password_UserRegister;
    UserRegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register_view);

        presenter = new UserRegisterPresenter(this);
        Button bt_Enter_UserLogin = findViewById(R.id.bt_Register_UserRegister);
        bt_Enter_UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pt_Username_UserRegister = findViewById(R.id.pt_Username_UserRegister);
                pt_Email_UserRegister = findViewById(R.id.pt_Email_UserRegister);
                et_Password_UserRegister = findViewById(R.id.et_Password_UserRegister);

                String username = pt_Username_UserRegister.getText().toString();
                String email = pt_Email_UserRegister.getText().toString();
                String password = et_Password_UserRegister.getText().toString();

                User user = new User(username, email, password);

                presenter.registerUser(user);
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(pt_Username_UserRegister, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }


}