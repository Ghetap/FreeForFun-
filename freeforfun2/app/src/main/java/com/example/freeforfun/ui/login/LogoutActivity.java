package com.example.freeforfun.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.freeforfun.R;

import static com.example.freeforfun.ui.login.MainActivity.loggedUser;

public class LogoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.2));

    }


    public void cancelLogout(View view) {
        finish();
    }

    public void logout(View view) {
        loggedUser = null;
        Intent loginIntent = new Intent(LogoutActivity.this, LoginActivity.class);
        this.startActivity(loginIntent);
        finish();
    }

}
