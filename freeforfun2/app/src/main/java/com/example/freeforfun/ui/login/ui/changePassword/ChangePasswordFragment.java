package com.example.freeforfun.ui.login.ui.changePassword;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.freeforfun.R;
import com.example.freeforfun.ui.inputValidations.UserValidations;
import com.example.freeforfun.ui.restCalls.UserRestCalls;
import com.google.android.material.snackbar.Snackbar;

import static com.example.freeforfun.ui.login.MainActivity.loggedUser;


public class ChangePasswordFragment extends Fragment implements View.OnClickListener {

    private ChangePasswordViewModel changePassworModel;
    private CoordinatorLayout coordinatorLayout;
    private EditText oldPassword;
    private EditText newPassword;
    private EditText reNewPassword;
    private Button btnChangePassword;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        changePassworModel =
                ViewModelProviders.of(this).get(ChangePasswordViewModel.class);
        View root = inflater.inflate(R.layout.fragment_change_password, container, false);
        coordinatorLayout = root.findViewById(R.id.coordinatorLayout);
        oldPassword = root.findViewById(R.id.oldPassword);
        newPassword = root.findViewById(R.id.newPassword);
        reNewPassword = root.findViewById(R.id.reNewPassword);
        btnChangePassword = root.findViewById(R.id.button_changePass);
        btnChangePassword.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        String oldPass = oldPassword.getText().toString().trim();
        String newPass = newPassword.getText().toString().trim();
        String reNewPassw = reNewPassword.getText().toString().trim();
        if(!validatePassword(oldPass) || !validatePassword(newPass)
                || !validatePassword(reNewPassw)) {
            showSnackbar("Password is required in each field and must " +
                    "have at least 4 characters!");
        }
        else {
            if (newPass.equals(reNewPassw)) {
                String serverResponse = UserRestCalls.changePassword(loggedUser.getUsername(),
                        oldPass, newPass);
                showSnackbar(serverResponse);
            } else {
                showSnackbar("The new password you entered is not equal in the two text fields!");
            }
        }
    }

    void showSnackbar(String text){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout,
                        text, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.MAGENTA);
        snackbar.show();
    }

    public boolean validatePassword(String passwordInput){
        if(!UserValidations.isNotEmpty(passwordInput)) {
            return false;
        }
        if(!UserValidations.hasAtLeast3Charachters(passwordInput)){
            return false;
        }
        return true;
    }
}