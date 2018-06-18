package com.example.nguyenvantung.place.Retrofit;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataClient {
    //lop gui va nhan cac phuong thuc gui len server

    //kiểm tra xem trên database đã có username này đăng ký chưa
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> checkUserName(@Field(Common.CONTROLLER) String controller,
                                       @Field(Common.ACTION) String action,
                                       @Field(Common.REQUEST_SERVER_USER_NAME) String user_name);

    //đăng ký
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> register(@Field(Common.CONTROLLER) String controller,
                                  @Field(Common.ACTION) String action,
                                  @Field(Common.REQUEST_SERVER_NAME) String name,
                                  @Field(Common.REQUEST_SERVER_USER_NAME) String user_name,
                                  @Field(Common.REQUEST_SERVER_PASSWORD) String password,
                                  @Field(Common.REQUEST_SERVER_IMAGE_NAME) String image_name,
                                  @Field(Common.REQUEST_SERVER_ENCODE_IMAGE) String encode_image);

    //Đăng nhập bằng tài khoảng và mật khẩu
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<UserModel> loginPlace(@Field(Common.CONTROLLER) String controller,
                               @Field(Common.ACTION) String action,
                               @Field(Common.REQUEST_SERVER_USER_NAME) String user_name,
                               @Field(Common.REQUEST_SERVER_PASSWORD) String password);


    //post bài đăng
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> UploadPost(@Field(Common.CONTROLLER) String controller,
                                    @Field(Common.ACTION) String action,
                                    @Field(Common.REQUEST_SERVER_ID_USER) String id_nguoi_dung,
                                    @Field(Common.REQUEST_SERVER_ID_PLACE) String id_dia_diem,
                                    @Field(Common.REQUEST_SERVER_DESCRIPTION) String noi_dung,
                                    @Field(Common.REQUEST_SERVER_ENCODE_IMAGE) String encode_image,
                                    @Field(Common.REQUEST_SERVER_IMAGE_NAME) String imageName,
                                    @Field(Common.REQUEST_SERVER_DATE) String ngay_gio);
}
