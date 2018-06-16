package com.example.nguyenvantung.place.Model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nguyenvantung.place.View.Upload.ViewUploadFragment;

public class LoadMore extends RecyclerView.OnScrollListener {
    RecyclerView.LayoutManager layoutManager;

    private ViewUploadFragment viewUploadFragment;

    public LoadMore(RecyclerView.LayoutManager layoutManager, ViewUploadFragment viewUploadFragment){
        this.layoutManager = layoutManager;
        this.viewUploadFragment = viewUploadFragment;
    }

    //su kien da duoc scroll
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (layoutManager instanceof LinearLayoutManager){

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
