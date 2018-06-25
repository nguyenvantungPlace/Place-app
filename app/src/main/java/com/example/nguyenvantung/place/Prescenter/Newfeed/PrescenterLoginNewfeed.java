package com.example.nguyenvantung.place.Prescenter.Newfeed;

import android.util.Log;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.Retrofit.DataClient;
import com.example.nguyenvantung.place.View.Home.Fragment.ViewNewfeedFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescenterLoginNewfeed implements PrescenterIMPNewfeed {
    private ViewNewfeedFragment viewNewfeedFragment;
    private DataClient dataClient;

    public PrescenterLoginNewfeed(ViewNewfeedFragment viewNewfeedFragment){
        this.viewNewfeedFragment = viewNewfeedFragment;
        dataClient = APIUtils.getData();
    }

    @Override
    public void getPost(int limit) {
        Call<List<NewfeedModel>> callback = dataClient.getPost(Common.CONTROLLER_POST,
                Common.ACTION_GET_POST, limit);

        callback.enqueue(new Callback<List<NewfeedModel>>() {
            @Override
            public void onResponse(Call<List<NewfeedModel>> call, Response<List<NewfeedModel>> response) {
                viewNewfeedFragment.addDataRecyclerview(response.body());
            }

            @Override
            public void onFailure(Call<List<NewfeedModel>> call, Throwable t) {
                Log.d("kiemtra", "lay that bai");
            }
        });
    }
}
