package com.example.nguyenvantung.place.View.User;

import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;

import java.util.List;

public interface ViewUserFragment {
    void addDataToRecyclerView(List<NewfeedModel> newfeedList);
    void addDataLoadMore();
}
