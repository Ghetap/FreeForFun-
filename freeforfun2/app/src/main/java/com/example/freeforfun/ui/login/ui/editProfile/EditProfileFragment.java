package com.example.freeforfun.ui.login.ui.editProfile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.freeforfun.R;
import com.example.freeforfun.ui.inputValidations.UserValidations;
import com.example.freeforfun.ui.login.MainActivity;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.example.freeforfun.ui.login.MainActivity.loggedUser;


public class EditProfileFragment extends Fragment implements View.OnClickListener {

    private EditProfileViewModel editProfileViewModel;
    private CoordinatorLayout coordinatorLayout;
    private EditText firstName;
    private EditText lastName;
    private EditText mobilePhone;
    private ImageView imageView;
    private Button updateProfileButton;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        editProfileViewModel =
                ViewModelProviders.of(this).get(EditProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        firstName = root.findViewById(R.id.editText_firstName);
        lastName = root.findViewById(R.id.editText_lastName);
        mobilePhone = root.findViewById(R.id.editText_mobileNumber);
        updateProfileButton = root.findViewById(R.id.button_update);
        coordinatorLayout = root.findViewById(R.id.coordinatorLayout);
        updateProfileButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put("id",loggedUser.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if(firstName.getText().toString().trim().length() > 0)
                jsonUser.put("firstName",firstName.getText());
            else
                jsonUser.put("firstName",loggedUser.getFirstName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if(lastName.getText().toString().trim().length() > 0)
                jsonUser.put("lastName",lastName.getText());
            else
                jsonUser.put("lastName",loggedUser.getLastName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonUser.put("password",loggedUser.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonUser.put("email",loggedUser.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if(mobilePhone.getText().toString().trim().length() > 0)
                jsonUser.put("mobileNumber",mobilePhone.getText());
            else
                jsonUser.put("mobileNumber",loggedUser.getMobileNumber());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonUser.put("username",loggedUser.getUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonUser.put("role",loggedUser.getRole());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if(firstName.getText().toString().trim().length() > 0 &&!UserValidations.validateName(jsonUser.getString("firstName")))
            {
                showSnackbar("First name must begin with capital letter and contain max 30 chracters !");
            }
            else if(mobilePhone.getText().toString().trim().length() > 0  && !UserValidations.validateROPhoneNumber(jsonUser.getString("mobileNumber")))
            {
                showSnackbar("Invalid RO- mobile phone");
            }
            else if(lastName.getText().toString().trim().length() > 0 && !UserValidations.validateName(jsonUser.getString("lastName")))
            {
                showSnackbar("Last name must begin with capital letter and contain max 30 chracters !");

            }
            else{
                String message = UserRestCalls.update(jsonUser);
                if(message != null){
                    showSnackbar(message);
                    loggedUser.setFirstName(jsonUser.getString("firstName"));
                    loggedUser.setLastName(jsonUser.getString("lastName"));
                    loggedUser.setMobileNumber(jsonUser.getString("mobileNumber"));
                }else
                    showSnackbar("Edit didn't go well. Try again!");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void showSnackbar(String text){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout,
                        text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}