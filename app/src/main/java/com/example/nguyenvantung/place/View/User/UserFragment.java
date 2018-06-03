package com.example.nguyenvantung.place.View.User;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nguyenvantung.place.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getActivity(), "fragment home", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

}
