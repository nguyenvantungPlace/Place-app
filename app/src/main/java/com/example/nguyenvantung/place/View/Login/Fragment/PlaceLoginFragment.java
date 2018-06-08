package com.example.nguyenvantung.place.View.Login.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Register.RegisterActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceLoginFragment extends Fragment implements View.OnClickListener {

    private TextView placelogin_register;
    private View view;

    public PlaceLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_place_login, container, false);

        addControls();
        addEvents();
        return view;
    }

    private void addControls() {
        placelogin_register = view.findViewById(R.id.placelogin_register);
    }

    private void addEvents() {
        placelogin_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.placelogin_register:
                Intent iRegister = new Intent(getContext(), RegisterActivity.class);
                startActivity(iRegister);
                break;
        }
    }
}
