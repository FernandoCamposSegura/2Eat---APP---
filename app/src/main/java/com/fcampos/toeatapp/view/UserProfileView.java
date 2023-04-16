package com.fcampos.toeatapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.contract.UserDeleteContract;
import com.fcampos.toeatapp.contract.UserProfileContract;
import com.fcampos.toeatapp.domain.User;
import com.fcampos.toeatapp.presenter.UserDeletePresenter;
import com.fcampos.toeatapp.presenter.UserProfilePresenter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class UserProfileView extends AppCompatActivity implements UserProfileContract.View, UserDeleteContract.View {

    TextView tv_Username_UserProfile;
    TextView tv_Email_UserProfile;

    Button bt_SignOut_UserProfile;
    Button bt_Delete_UserProfile;

    UserProfilePresenter userProfilePresenter;
    UserDeletePresenter userDeletePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_view);

        userProfilePresenter = new UserProfilePresenter(this);
        userDeletePresenter = new UserDeletePresenter(this);

        userProfilePresenter.findUser(UserLoginView.USER_ID);

        bt_SignOut_UserProfile = findViewById(R.id.bt_SignOut_UserProfile);
        bt_SignOut_UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLoginView.USER_ID = 0;
                goToUserLoginActivity();
            }
        });

        bt_Delete_UserProfile = findViewById(R.id.bt_Delete_UserProfile);
        bt_Delete_UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDeletePresenter.deleteUser(UserLoginView.USER_ID);
                goToUserLoginActivity();
            }
        });
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
    public void showMessage(String message) {
        Snackbar.make(tv_Username_UserProfile, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void loadUser(User user) {
        tv_Username_UserProfile = findViewById(R.id.tv_Username_UserProfile);
        tv_Email_UserProfile = findViewById(R.id.tv_Email_UserProfile);

        tv_Username_UserProfile.setText(user.getUsername());
        tv_Email_UserProfile.setText(user.getEmail());
    }

    public void goToUserLoginActivity() {
        Intent intent = new Intent(this, UserLoginView.class);
        this.startActivity(intent);
    }
}