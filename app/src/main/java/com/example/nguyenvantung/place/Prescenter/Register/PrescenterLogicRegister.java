package com.example.nguyenvantung.place.Prescenter.Register;

import android.util.Log;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.Retrofit.DataClient;
import com.example.nguyenvantung.place.View.Register.ViewRegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescenterLogicRegister implements IPrescenterRegister {
    private ViewRegisterActivity viewRegisterActivity;
    private DataClient dataClient;

    public PrescenterLogicRegister(ViewRegisterActivity viewRegisterActivity){
        this.viewRegisterActivity = viewRegisterActivity;
        dataClient = APIUtils.getData();
    }

    @Override
    public void checkUserName(String user_name) {
        if (user_name.length() < 8 || user_name.contains(" ")){ //contains : Kiểm tra xem trong user_name có dấu cách không
            viewRegisterActivity.userNameErrorLegth();
        }else {
            Call<CheckTrueFalse> callback = dataClient.checkUserName(Common.CONTROLLER_USER, Common.ACTION_CHECKUSERNAME, user_name);
            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    Log.d("kiemtra", response.body().getStatus());
                    if (response.body().getStatus().equals("false"))
                        viewRegisterActivity.userNameUsed();
                }

                @Override
                public void onFailure(Call<CheckTrueFalse> call, Throwable t) {
                    Log.d("kiemtra", t.getMessage());
                }
            });
        }
    }

    @Override
    public void checkPassword(String password) {
        if (password.length() < 8 || password.length() > 22)
            viewRegisterActivity.fomatPassword();
    }

    @Override
    public void checkFormRegister(String name,String password, String re_password, String uri_image) {
        if (uri_image != null && re_password.equals(password) && !name.equals("")){
            viewRegisterActivity.register();
        }else {
            if (!(re_password.equals(password)))
                viewRegisterActivity.rePasswordFalse();
            if (uri_image == null)
                viewRegisterActivity.requestUpImage();
            if (name == null)
                viewRegisterActivity.requestName();
        }
    }

    @Override
    public void registerUser(String name, String user_name, String password, String image_base64) {
        String image_name = "IMG_PLACE_" + System.currentTimeMillis() + ".jpg";
        Call<CheckTrueFalse> callback = dataClient.register(Common.CONTROLLER_USER,
                Common.ACTION_REGISTER_PLACE, name, user_name,password,image_name,image_base64);

        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) viewRegisterActivity.registerSuccess();
                else viewRegisterActivity.registerFail();
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {
                Log.d("kiemtra", t.getMessage());
            }
        });
    }

    @Override
    public void loginUser(String user_name, String passowrd) {
        Call<UserModel> callback = dataClient.loginPlace(Common.CONTROLLER_USER,
                Common.ACTION_LOGIN_PLACE, user_name, passowrd);
        callback.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (Integer.parseInt(response.body().getIdNguoiDung()) > 0) viewRegisterActivity.loginSuccess(response.body());
                else viewRegisterActivity.loginFail();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("kiemtra", t.getMessage());
            }
        });
    }
}
