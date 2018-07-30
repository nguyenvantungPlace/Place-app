package com.example.nguyenvantung.place.View.PlaceFragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePlaceFragment extends Fragment {
    private View view;
    private RecyclerView home_place_rv;


    public HomePlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_place, container, false);
        addControls();
        addEvents();
        return view;
    }

    private void addControls() {
        home_place_rv = view.findViewById(R.id.home_place_rv);
    }

    private void addEvents() {

    }

}
