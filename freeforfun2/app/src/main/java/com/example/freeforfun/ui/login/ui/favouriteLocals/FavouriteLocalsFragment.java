package com.example.freeforfun.ui.login.ui.favouriteLocals;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freeforfun.R;

public class FavouriteLocalsFragment extends Fragment {

    private FavouriteLocalsViewModel mViewModel;

    public static FavouriteLocalsFragment newInstance() {
        return new FavouriteLocalsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favourite_locals_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FavouriteLocalsViewModel.class);
        // TODO: Use the ViewModel
    }

}
