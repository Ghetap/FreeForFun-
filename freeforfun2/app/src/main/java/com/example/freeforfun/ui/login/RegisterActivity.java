package com.example.freeforfun.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.freeforfun.R;
import com.example.freeforfun.ui.inputValidations.UserValidations;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONObject;

import org.json.JSONException;


public class RegisterActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    private Button registerButton;
    private EditText username;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private EditText mobilePhone;
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        firstName = findViewById(R.id.editText_firstName);
        lastName = findViewById(R.id.editText_lastName);
        mobilePhone = findViewById(R.id.editText_mobileNumber);
        email = findViewById(R.id.editText_email);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject jsonUser = new JSONObject();
                try {
                    jsonUser.put("firstName",firstName.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("lastName",lastName.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("password",password.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("email",email.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("mobileNumber",mobilePhone.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("username",username.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonUser.put("role",0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    if( validatePassword(jsonUser.getString("password")) && validateUsername(jsonUser.getString("username"))
                    && UserValidations.validateEmail(jsonUser.getString("email")) &&
                    UserValidations.validateName(jsonUser.getString("firstName")) &&
                    UserValidations.validateName(jsonUser.getString("lastName")) &&
                    UserValidations.validateROPhoneNumber(jsonUser.getString("mobileNumber")) &&
                    UserValidations.validateRole(jsonUser.getString("role"))){

                        String message = UserRestCalls.register(jsonUser);
                        if(message != null)
                            showSnackbar(message);
                    }else
                        showSnackbar("Registration didn't go well. Try again!");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void showSnackbar(String messageFromServer){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, messageFromServer, Snackbar.LENGTH_LONG);
        snackbar.show();
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
}
