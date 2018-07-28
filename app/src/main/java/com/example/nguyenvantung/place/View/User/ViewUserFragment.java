package com.example.nguyenvantung.place.View.User;

import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;

import java.util.List;

public interface ViewUserFragment {
    void addDataToRecyclerView(List<PlaceModel> list);
    void addDataLoadMore();
    void checkPlaceTrue();
    void checkPlaceFalse();

    void getDataPlaceSuccess(List<PlaceModel> data);
    void getDtaPlaceFail();
}
