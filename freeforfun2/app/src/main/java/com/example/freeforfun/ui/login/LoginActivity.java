package com.example.freeforfun.ui.login;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.inputValidations.UserValidations;
import com.example.freeforfun.ui.model.User;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    private EditText username;
    private EditText password;
    private TextView logResponse;
    private Button login;
    private TextView register;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        login = findViewById(R.id.button_login);
        register = findViewById(R.id.textView_register);
        forgotPassword = findViewById(R.id.textView_forgotPassword);
        logResponse = findViewById(R.id.textView_log);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPasswordIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPasswordIntent);
            }
        });
        username.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = username.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            if(!validateUsername(usernameInput) && ! validatePassword(passwordInput))
                login.setEnabled(false);
            else
                login.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void login(View w){
        String usernameInput = username.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();
        if(!validateUsername(usernameInput) | !validatePassword(passwordInput)){
            return;
        }
        User loggedUser = UserRestCalls.login(usernameInput, passwordInput);
        if(loggedUser!=null){
            Intent mainMenuIntent = new Intent(LoginActivity.this, MainActivity.class);
            mainMenuIntent.putExtra("loggedUser", loggedUser);
            startActivity(mainMenuIntent);
        }
        else{
            showSnackbar("Authentication failed! Incorrect username or password.");

        }
    }

    public void showHidePassword(View view){
        if(view.getId()==R.id.show_password_btn){
            if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hidepassword);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.showpassword);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    public boolean validateUsername(String usernameInput){
        if(!UserValidations.isNotEmpty(usernameInput)) {
            username.setError("Username is required!");
        }
        else {
            username.setError(null);
        }
        if(!UserValidations.containsOnlyLettersAndDigits(usernameInput)){
            username.setError("Username can contain only letters,digits, '_' and '.'!");
            return false;
        }
        else {
            username.setError(null);
        }
        if(!UserValidations.doesNotContainSpace(usernameInput)){
            username.setError("Username cannot contain space!");
            return false;
        }
        else {
            username.setError(null);
        }
        return true;
    }

    public boolean validatePassword(String passwordInput){
        if(!UserValidations.isNotEmpty(passwordInput)) {
            password.setError("Password is required!");
            return false;
        }
        else {
            password.setError(null);
        }
        if(!UserValidations.hasAtLeast3Charachters(passwordInput)){
            password.setError("Password must have at least 4 characters!");
            return false;
        }
        else {
            password.setError(null);
        }
        return true;
    }
    public void showSnackbar(String messageFromServer){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, messageFromServer, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}