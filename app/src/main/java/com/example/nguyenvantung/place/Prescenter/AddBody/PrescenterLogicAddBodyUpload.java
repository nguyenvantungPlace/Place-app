package com.example.nguyenvantung.place.Prescenter.AddBody;

import android.content.Intent;
import android.util.Log;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.Retrofit.DataClient;
import com.example.nguyenvantung.place.Retrofit.RetrofitClient;
import com.example.nguyenvantung.place.View.AddBody.ViewAddBodyUpload;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescenterLogicAddBodyUpload implements PrescenterIMPAddBodyUpload {
    private ViewAddBodyUpload viewAddBodyUpload;
    private DataClient dataClient;

    public PrescenterLogicAddBodyUpload(ViewAddBodyUpload viewAddBodyUpload){
        this.viewAddBodyUpload = viewAddBodyUpload;
        dataClient = APIUtils.getData();
    }

    @Override
    public void uploadPost(String id_dia_diem, String noi_dung, String ngay_gio_dang, String image_encode, String image_name) {
        if (!id_dia_diem.equals("")) {
            Call<CheckTrueFalse> callback = dataClient.UploadPost(
                    Common.CONTROLLER_POST,
                    Common.ACTION_UPLOAD,
                    Common.USER.getIdNguoiDung(),
                    id_dia_diem, noi_dung, "", image_name, ngay_gio_dang);

            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    if (response.body().getStatus().equals("true"))
                        viewAddBodyUpload.uploadSuccess();
                    else viewAddBodyUpload.uploadFail();
                }

                @Override
                public void onFailure(Call<CheckTrueFalse> call, Throwable t) {
                    Log.d("kiemtra", t.getLocalizedMessage());
                }
            });
        }else {
            viewAddBodyUpload.errorTxtPlace();
        }
    }
}
