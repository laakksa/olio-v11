package com.olio.navigationdrawer;

import android.widget.LinearLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class HomeFragmentViewModel extends ViewModel {
    private SavedStateHandle state;

    private MutableLiveData<String> mText;

    public HomeFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public void setText(String s){
        mText.setValue(s);
    }
}
