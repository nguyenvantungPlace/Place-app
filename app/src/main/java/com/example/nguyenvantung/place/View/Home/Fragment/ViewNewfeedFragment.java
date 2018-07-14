package com.example.nguyenvantung.place.View.Home.Fragment;

import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;

import java.util.List;

public interface ViewNewfeedFragment {
    void addDataRecyclerview(List<NewfeedModel> newfeedModelList);
    void getData();
    void nextPageEditPost(NewfeedModel newfeedModel , int possition);
    void hidePost(int possition);
    void nextPageComment(NewfeedModel possion);
}
