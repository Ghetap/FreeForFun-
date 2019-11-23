package com.example.freeforfun.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.inputValidations.UserValidations;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.google.android.material.snackbar.Snackbar;

public class ForgotPasswordActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    private Button forgetPasswordButton;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email = findViewById(R.id.editText_emailForgotPassword);
        forgetPasswordButton = findViewById(R.id.button_forgotPass);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
    }

    public boolean validateEmail(String emailInput){
        if(!UserValidations.isNotEmpty(emailInput)) {
            return false;
        }
        if(!UserValidations.validateEmail(emailInput)){
            return false;
        }
        return true;
    }

    public void showSnackbar(String messageFromServer){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, messageFromServer, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.MAGENTA);
        snackbar.show();
    }

    public void forgotPassword(View view){
        String emailInput = email.getText().toString().trim();
        if(! validateEmail(emailInput))
            showSnackbar("The email address is invalid! Mail could't pe sent!");
        else{
            String response = UserRestCalls.forgotPassword(emailInput);
            showSnackbar(response);
        }
    }
}