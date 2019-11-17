package com.example.freeforfun.ui.login.ui.changeLanguage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.freeforfun.R;


public class ChangeLanguageFragment extends Fragment {

    private ChangeLanfguageViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ChangeLanfguageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_change_language, container, false);
        final TextView textView = root.findViewById(R.id.changeLanguageFragment);
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}