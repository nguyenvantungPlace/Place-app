package com.example.nguyenvantung.place.View.Home.Fragment;


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
public class NewsFeedFragment extends Fragment {
    private View view;
    private RecyclerView newsfeed_recyclerview;


    public NewsFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news_feed, container, false);

        addControls();
        addEvents();
        return view;
    }

    private void addControls() {

    }

    private void addEvents() {

    }

}
