package com.example.nguyenvantung.place.View.Notifi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotifiFragment extends Fragment {


    public NotifiFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(){
        return new NotifiFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifi, container, false);
    }

}
