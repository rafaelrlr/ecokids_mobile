package com.example.ecokids.ui.metas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MetasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MetasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is metas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}