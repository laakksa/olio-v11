package com.olio.navigationdrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {
    EditText edit;
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
        super.onViewCreated(view, savedInstanceState);
        edit = v.findViewById(R.id.editText);
        textView = v.findViewById(R.id.textView);
        //Restore data from saved state
        if (savedInstanceState != null) {
            edit.setText(savedInstanceState.getString("savedInstance"));
        }
        SharedPreferences spref = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);

        //Restore editable text views text when returning to fragment
        edit.setText(spref.getString("data", null));

        //Set editText uneditable and set readable textView's text when editable switch is false in settings
        if (!spref.getBoolean("switch", true)) {
            edit.setFocusableInTouchMode(false);
            edit.setFocusable(false);
            edit.setKeyListener(null);
            textView.setText(edit.getText());
        }

        //Set toolbar's title from display text setting
        String displaytext = spref.getString("displaytext", "Display Text");
        getActivity().setTitle(displaytext);

        //Set textView's text settings with parameters from settings
        textView.setTextSize(Integer.parseInt(spref.getString("fontsize", "12")));
        textView.setGravity(Integer.parseInt(spref.getString("gravity", "3")));
        textView.setTextColor(Color.parseColor(spref.getString("font_colour", "#FF000000")));
        if (spref.getBoolean("bold", false)) {
            textView.setTypeface(null, Typeface.BOLD);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences pref = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //Save editText's text when navigating away from fragment
        editor.putString("data", edit.getText().toString()).commit();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save editText text if for example rotating device
        outState.putString("savedInstance", edit.getText().toString());
    }
}


