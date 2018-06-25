package com.example.nguyenvantung.place.View.Home.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.Adapter.Newfeed.NewfeedRecyclerViewAdapter;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Prescenter.Newfeed.PrescenterLoginNewfeed;
import com.example.nguyenvantung.place.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends Fragment implements ViewNewfeedFragment {
    private View view;
    private RecyclerView newsfeed_recyclerview;
    private PrescenterLoginNewfeed prescenterLoginNewfeed;

    private List<NewfeedModel> newfeedModelList;
    private NewfeedRecyclerViewAdapter newfeedRecyclerViewAdapter;


    public NewsFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        prescenterLoginNewfeed = new PrescenterLoginNewfeed(this);


        newfeedModelList = new ArrayList<>();
        prescenterLoginNewfeed.getPost(0);
        addControls();
        addData();
        addEvents();
        return view;
    }

    private void addControls() {
        //init recyclerview
        newsfeed_recyclerview = view.findViewById(R.id.newsfeed_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        newsfeed_recyclerview.setHasFixedSize(true);
        newsfeed_recyclerview.setLayoutManager(linearLayoutManager);
    }

    private void addData() {
    }

    private void addEvents() {

    }

    @Override
    public void addDataRecyclerview(List<NewfeedModel> newfeedModelList) {
        this.newfeedModelList.addAll(newfeedModelList);
        Log.d("kiemtra", newfeedModelList.get(0).getAnh());
//        newfeedRecyclerViewAdapter.notifyDataSetChanged();
        newfeedRecyclerViewAdapter = new NewfeedRecyclerViewAdapter(newfeedModelList, this);
        newsfeed_recyclerview.setAdapter(newfeedRecyclerViewAdapter);
    }

}
