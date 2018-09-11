package com.example.nguyenvantung.place.View.Home.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nguyenvantung.place.Adapter.Newfeed.NewfeedRecyclerViewAdapter;
import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectClass.LoadMore;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Prescenter.Newfeed.PrescenterLoginNewfeed;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Comment.CommentActivity;
import com.example.nguyenvantung.place.View.EditPost.EditPostActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends Fragment implements ViewNewfeedFragment, SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private RecyclerView newsfeed_recyclerview;
    private ProgressBar newsfeed_progressbar;
    private PrescenterLoginNewfeed prescenterLoginNewfeed;
    private SwipeRefreshLayout swipe;

    private static List<NewfeedModel> newfeedModelList;
    private static NewfeedRecyclerViewAdapter newfeedRecyclerViewAdapter;
    private int limit = 0;


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
        prescenterLoginNewfeed.getPost(limit);
        addControls();
        addData();
        addEvents();
        return view;
    }

    private void addControls() {
        //init recyclerview
        newsfeed_recyclerview = view.findViewById(R.id.newsfeed_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        newsfeed_recyclerview.setHasFixedSize(true);
        newsfeed_recyclerview.setLayoutManager(linearLayoutManager);
        newfeedRecyclerViewAdapter = new NewfeedRecyclerViewAdapter(newfeedModelList, this);
        newsfeed_recyclerview.setAdapter(newfeedRecyclerViewAdapter);
        newsfeed_progressbar = view.findViewById(R.id.newsfeed_progressbar);
        swipe = view.findViewById(R.id.swipe);
    }

    private void addData() {
    }

    private void addEvents() {
        newsfeed_recyclerview.addOnScrollListener(new LoadMore(newsfeed_recyclerview.getLayoutManager(), this));
        swipe.setOnRefreshListener(this);
    }

    @Override
    public void addDataRecyclerview(List<NewfeedModel> list) {
        newfeedModelList.addAll(list);
        newfeedRecyclerViewAdapter.notifyDataSetChanged();
        newsfeed_progressbar.setVisibility(View.GONE);
        swipe.setRefreshing(false);
    }

    @Override
    public void getData() {
//        Log.d("kiemtra", "limit: " + limit);
        limit += 5;
        Log.d("kiemtra", "limit: " + limit);
        prescenterLoginNewfeed.getPost(limit);
    }


    @Override
    public void nextPageEditPost(NewfeedModel newfeedModel, int possition) {
        Intent iEditPost = new Intent(getContext(), EditPostActivity.class);
        iEditPost.putExtra("POSSITIONEDIT", newfeedModel.getNoiDung());
        Common.NEWFEEDEDIT = newfeedModel;
        startActivity(iEditPost);
    }

    @Override
    public void hidePost(int possition) {
        newfeedModelList.remove(possition);
        newfeedRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void nextPageComment(NewfeedModel post) {
        Intent iComment = new Intent(getContext(), CommentActivity.class);
        iComment.putExtra(Common.INTENT_ID_POST, post);
        startActivity(iComment);
    }


    public void changePostEdited(int postion){
        newfeedModelList.set(postion, Common.NEWFEEDEDIT);
        newfeedRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        if (swipe.getScrollY() == 0){
            newfeedModelList.clear();
            limit = 0;
            prescenterLoginNewfeed.getPost(limit);
            Log.d("NewFeedFragment", "Swiped");
            newsfeed_recyclerview.addOnScrollListener(new LoadMore(newsfeed_recyclerview.getLayoutManager(), this));
        }
    }
}
