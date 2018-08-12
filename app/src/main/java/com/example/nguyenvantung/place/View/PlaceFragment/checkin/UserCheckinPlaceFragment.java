package com.example.nguyenvantung.place.View.PlaceFragment.checkin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserCheckinPlaceFragment extends Fragment {


    public UserCheckinPlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_checkin_place, container, false);
    }

}
