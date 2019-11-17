package com.example.freeforfun.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.freeforfun.R;
import com.example.freeforfun.ui.utils.Paths;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.freeforfun.ui.utils.Paths.BASE_URL;

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
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

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

                String URL = BASE_URL + Paths.REGISTER;
                JsonObjectRequest requestObject = new JsonObjectRequest(
                        Request.Method.POST, URL, jsonUser, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showSnackbar(response.toString());
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                showSnackbar(error.toString());
                            }
                        }
                );
                requestQueue.add(requestObject);
            }
        });
    }
    public void showSnackbar(String messageFromServer){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, messageFromServer, Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout,"Undo registration",Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                })
                .setActionTextColor(Color.MAGENTA);
        snackbar.show();
    }
}
