package com.example.freeforfun.ui.login.ui.changeLanguage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChangeLanfguageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChangeLanfguageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Change Language fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}