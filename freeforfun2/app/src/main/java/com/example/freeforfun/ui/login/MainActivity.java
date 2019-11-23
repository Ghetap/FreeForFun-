package com.example.freeforfun.ui.login;
import android.content.Intent;
import android.os.Bundle;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView loggedEmail;
    private TextView loggedUsername;
    private AppBarConfiguration mAppBarConfiguration;
    public static User loggedUser;

    private MenuItem logoutItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loggedUser = (User)getIntent().getSerializableExtra("loggedUser");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No notifications found!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile, R.id.nav_update, R.id.nav_see_locals,
                 R.id.nav_change_language, R.id.nav_change_password)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        logoutItem = findViewById(R.id.action_settingsLogout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        loggedEmail = findViewById(R.id.loggedEmail);
        loggedUsername = findViewById(R.id.loggedUsername);
        loggedEmail.setText(loggedUser.getEmail());
        loggedUsername.setText(loggedUser.getUsername());
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logout(MenuItem item) {
        Intent logoutIntent = new Intent(MainActivity.this, LogoutActivity.class);
        this.startActivity(logoutIntent);
    }

    public void openDeleteActivity(View view) {
        Intent deleteIntent = new Intent(MainActivity.this, DeleteAccountActivity.class);
        this.startActivity(deleteIntent);
    }
}