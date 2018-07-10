package com.example.nguyenvantung.place.Prescenter.User;

import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;

import java.util.List;

public interface PrescenterIMPUser {
    List<NewfeedModel> getData(int limit);
}
