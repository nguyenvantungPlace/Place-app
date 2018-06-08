package com.example.nguyenvantung.place.View.Login.Fragment.PlaceLogin;

import com.example.nguyenvantung.place.Model.ObjectModel.UserPlaceModel;

public interface ViewPlaceLoginFragment {
    void loginSuccess(UserPlaceModel userPlaceModel);
    void loginFail();
}
