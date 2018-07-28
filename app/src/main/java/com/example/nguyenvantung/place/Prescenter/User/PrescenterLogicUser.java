package com.example.nguyenvantung.place.Prescenter.User;

import android.util.Log;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.Retrofit.DataClient;
import com.example.nguyenvantung.place.View.User.ViewUserFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescenterLogicUser implements PrescenterIMPUser {
    private ViewUserFragment viewUserFragment;
    private DataClient dataClient;
    private List<NewfeedModel> newfeedList;

    public PrescenterLogicUser(ViewUserFragment viewUserFragment) {
        this.viewUserFragment = viewUserFragment;
        dataClient = APIUtils.getData();
    }


    //lay cac dia diem ma nguoi dung da checkin
    @Override
    public List<NewfeedModel> getData(int limit) {
        Call<List<PlaceModel>> callback = dataClient.getListPlaceFromIDUSerCheckin(Common.CONTROLLER_PLACE,
                Common.ACTION_GET_PLACE_FROM_IDUSER_CHECKIN, Common.USER.getIdNguoiDung());

        callback.enqueue(new Callback<List<PlaceModel>>() {
            @Override
            public void onResponse(Call<List<PlaceModel>> call, Response<List<PlaceModel>> response) {
                Log.d("kiemtra", response.body().size() + "");
                if (response.body() != null) viewUserFragment.addDataToRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<PlaceModel>> call, Throwable t) {
                Log.d("kiemtra", "loi lay thong tin checkin: " + t.getLocalizedMessage());
            }
        });
        return null;
    }

    //kiem tra xem nguoi dung co lam chu 1 dia diem nao khong
    @Override
    public void checkPlaceInIDUser(int id_user) {
        Call<CheckTrueFalse> callback = Common.DATA_CLIENT.checkPlace(Common.CONTROLLER_PLACE,
                Common.ACTION_CHECK_PLACE_FROM_IDUSER, id_user);

        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) viewUserFragment.checkPlaceTrue();
                else viewUserFragment.checkPlaceFalse();
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

            }
        });
    }

    //lay cac dia diem
    @Override
    public void getDataPlace(int id_user, int limit) {
        Call<List<PlaceModel>> callback = Common.DATA_CLIENT.getPlace(Common.CONTROLLER_PLACE,
                Common.ACTION_GET_PLACE_FROM_IDUSER, id_user, limit);

        callback.enqueue(new Callback<List<PlaceModel>>() {
            @Override
            public void onResponse(Call<List<PlaceModel>> call, Response<List<PlaceModel>> response) {
                if (!response.body().isEmpty()) viewUserFragment.getDataPlaceSuccess(response.body());
                else viewUserFragment.getDtaPlaceFail();
            }

            @Override
            public void onFailure(Call<List<PlaceModel>> call, Throwable t) {
                Log.d("kiemtra", "loi: " + t.getLocalizedMessage());
            }
        });
    }
}
