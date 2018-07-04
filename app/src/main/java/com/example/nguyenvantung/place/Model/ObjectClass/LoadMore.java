package com.example.nguyenvantung.place.Model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nguyenvantung.place.View.Home.Fragment.ViewNewfeedFragment;
import com.example.nguyenvantung.place.View.Upload.ViewUploadFragment;

public class LoadMore extends RecyclerView.OnScrollListener {
    RecyclerView.LayoutManager layoutManager;

    private ViewUploadFragment viewUploadFragment;
    private ViewNewfeedFragment viewNewfeedFragment;
    private int check = 0;

    public LoadMore(RecyclerView.LayoutManager layoutManager, ViewUploadFragment viewUploadFragment){
        this.layoutManager = layoutManager;
        this.viewUploadFragment = viewUploadFragment;
    }

    public LoadMore(RecyclerView.LayoutManager layoutManager, ViewNewfeedFragment viewNewfeedFragment){
        this.layoutManager = layoutManager;
        this.viewNewfeedFragment = viewNewfeedFragment;
    }

    //su kien da duoc scroll
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (layoutManager instanceof LinearLayoutManager && viewNewfeedFragment != null){
//              Log.d("kiemtra", "position: " + ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition());
            int lastVisible = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            if (lastVisible == (layoutManager.getItemCount() - 1) && check != lastVisible){
                check = lastVisible;
                Log.d("kiemtra", check + "");
                viewNewfeedFragment.getData();
            }
        }
        if (layoutManager instanceof GridLayoutManager && viewUploadFragment != null){
            if (((GridLayoutManager) layoutManager).findLastVisibleItemPosition()
                    == (layoutManager.getItemCount() - 7)){
                viewUploadFragment.addDataLoadMore();
            }
        }


    }

    //su kien scroll thay doi
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
