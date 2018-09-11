package com.example.nguyenvantung.place.Retrofit;

import com.example.nguyenvantung.place.Common.Common;

public class APIUtils {
    //class dung de cung cap cac duong dan de gui den server

    public static DataClient getData(){
        return RetrofitClient.getClient(Common.BASE_URL).create(DataClient.class);
    }
}
