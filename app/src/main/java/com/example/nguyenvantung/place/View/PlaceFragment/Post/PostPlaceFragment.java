package com.example.nguyenvantung.place.View.PlaceFragment.Post;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.nguyenvantung.place.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostPlaceFragment extends Fragment {
    private View view;
    private ProgressBar post_place_progressbar;
    private RecyclerView post_place_recyclerview;

    public PostPlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_post_place, container, false);
        addControls();
        addEvents();
        return view;
    }

    private void addControls() {
        post_place_progressbar = view.findViewById(R.id.post_place_progressbar);
        post_place_recyclerview= view.findViewById(R.id.post_place_recyclerview);
    }

    private void addEvents() {

    }

}
