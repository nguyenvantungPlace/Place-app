package com.example.nguyenvantung.place.Retrofit;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.UserPlaceModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataClient {
    //lop gui va nhan cac phuong thuc gui len server


    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> checkUserName(@Field(Common.CONTROLLER) String controller,
                                       @Field(Common.ACTION) String action,
                                       @Field(Common.REQUEST_SERVER_USER_NAME) String user_name);

    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> register(@Field(Common.CONTROLLER) String controller,
                                  @Field(Common.ACTION) String action,
                                  @Field(Common.REQUEST_SERVER_USER_NAME) String user_name,
                                  @Field(Common.REQUEST_SERVER_PASSWORD) String password,
                                  @Field(Common.REQUSET_SERVER_IMAGE_NAME) String image_name,
                                  @Field(Common.REQUSET_SERVER_ENCODE_IMAGE) String encode_iamge);

    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<UserPlaceModel> loginPlace(@Field(Common.CONTROLLER) String controller,
                                    @Field(Common.ACTION) String action,
                                    @Field(Common.REQUEST_SERVER_USER_NAME) String user_name,
                                    @Field(Common.REQUEST_SERVER_PASSWORD) String password);

}
