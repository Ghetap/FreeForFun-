package com.example.freeforfun.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.restCalls.UserRestCalls;

import java.net.HttpURLConnection;

import static com.example.freeforfun.ui.login.MainActivity.loggedUser;

public class DeleteAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.2));
    }

    public void cancelDelete(View view) {
        finish();
    }

    public void deleteAccount(View view) {
        int serverResponseCode = UserRestCalls.deleteAccount(loggedUser.getUsername());
        if(serverResponseCode == HttpURLConnection.HTTP_OK) {
            loggedUser = null;
            Intent loginIntent = new Intent(DeleteAccountActivity.this, LoginActivity.class);
            this.startActivity(loginIntent);
            finish();
        }
        else{
            //todo show snackbar -> problem occurred -> daca are enentual ceva programari la localuri
        }

    }
}
