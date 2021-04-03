package com.olio.navigationdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment {
    EditText edit;
    String text;
    TextView textView;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home, container, false);
        edit = v.findViewById(R.id.editText);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final float scale = v.getContext().getResources().getDisplayMetrics().density;
        super.onViewCreated(view, savedInstanceState);
        edit = v.findViewById(R.id.editText);
        textView = v.findViewById(R.id.textView);
        if (savedInstanceState != null){
            edit.setText(savedInstanceState.getString("asdasd"));
        }
        SharedPreferences spref = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        edit.setText(spref.getString("daatta", null));
        if(!spref.getBoolean("switch", true)){
            edit.setFocusableInTouchMode(false);
            edit.setFocusable(false);
            edit.setKeyListener(null);
        }
        textView.setTextSize(Integer.parseInt(spref.getString("fontsize", "12")));
        System.out.println(spref.getInt("width-seekbar", 12));
        textView.setTextColor(Color.parseColor(spref.getString("font_colour", "#FF000000")));
        if(spref.getBoolean("bold", false)) {
            textView.setTypeface(null, Typeface.BOLD);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences pref = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("daatta", edit.getText().toString()).commit();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("asdasd", edit.getText().toString());
    }
}


