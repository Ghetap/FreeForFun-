package com.example.freeforfun.ui.login.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.freeforfun.R;
import com.example.freeforfun.ui.login.MainActivity;

public class UserProfileFragment extends Fragment {

    private UserProfileViewModel userProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileViewModel =
                ViewModelProviders.of(this).get(UserProfileViewModel.class);
       View root = inflater.inflate(R.layout.fragment_user_profile, container, false);
       TextView name = root.findViewById(R.id.name);
       name.setText(MainActivity.loggedUser.getFirstName() +" " + MainActivity.loggedUser.getLastName());

        TextView mobileNumber = root.findViewById(R.id.phoneNumber);
        mobileNumber.setText(MainActivity.loggedUser.getMobileNumber());

        TextView role = root.findViewById(R.id.roleUser);
        role.setText("User");

        TextView email = root.findViewById(R.id.emailUser);
        email.setText(MainActivity.loggedUser.getEmail());

        TextView username = root.findViewById(R.id.username);
        username.setText(MainActivity.loggedUser.getUsername());

        return root;
    }
}