package com.example.freeforfun.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.freeforfun.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void logout(View view){
        Intent loginIntent = new Intent(MainMenuActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
