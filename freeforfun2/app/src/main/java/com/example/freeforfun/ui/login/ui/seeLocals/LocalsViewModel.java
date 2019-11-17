package com.example.freeforfun.ui.login.ui.seeLocals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocalsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LocalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Locals fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}