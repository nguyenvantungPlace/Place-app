package com.example.nguyenvantung.place.Prescenter.Login.PlaceLogin;


import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;
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
        Call<UserModel> callback = dataClient.loginPlace(Common.CONTROLLER_USER,
                Common.ACTION_LOGIN_PLACE, user_name, password);
        callback.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.body().getIdNguoiDung().equals("")) {
                    if (Integer.parseInt(response.body().getIdNguoiDung()) != 0)
                        viewPlaceLoginFragment.loginSuccess(response.body());
                }else viewPlaceLoginFragment.loginFail();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }
}
