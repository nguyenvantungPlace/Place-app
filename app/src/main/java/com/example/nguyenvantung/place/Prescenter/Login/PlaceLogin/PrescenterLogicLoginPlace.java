package com.example.nguyenvantung.place.Prescenter.Login.PlaceLogin;


import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.UserPlaceModel;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.Retrofit.DataClient;
import com.example.nguyenvantung.place.View.Login.Fragment.PlaceLogin.ViewPlaceLoginFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescenterLogicLoginPlace implements PerscenterIMPLoginPlace{
    private ViewPlaceLoginFragment viewPlaceLoginFragment;
    private DataClient dataClient;

    public PrescenterLogicLoginPlace(ViewPlaceLoginFragment viewPlaceLoginFragment){
        this.viewPlaceLoginFragment = viewPlaceLoginFragment;
        dataClient = APIUtils.getData();
    }

    @Override
    public void Login(String user_name, String password) {
        Call<UserPlaceModel> callback = dataClient.loginPlace(Common.CONTROLLER_USER,
                Common.ACTION_LOGIN_PLACE, user_name, password);
        callback.enqueue(new Callback<UserPlaceModel>() {
            @Override
            public void onResponse(Call<UserPlaceModel> call, Response<UserPlaceModel> response) {
                if (!response.body().getIdNguoiDung().equals("")) {
                    if (Integer.parseInt(response.body().getIdNguoiDung()) != 0)
                        viewPlaceLoginFragment.loginSuccess(response.body());
                }else viewPlaceLoginFragment.loginFail();
            }

            @Override
            public void onFailure(Call<UserPlaceModel> call, Throwable t) {

            }
        });
    }
}
