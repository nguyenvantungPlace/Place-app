package com.example.nguyenvantung.place.Prescenter.EditPost;


import android.content.Intent;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.Retrofit.DataClient;
import com.example.nguyenvantung.place.View.EditPost.ViewEditPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescenterLogicEditPost implements PrescenterIMPEditPost {
    private ViewEditPost viewEditPost;
    private DataClient dataClient;

    public PrescenterLogicEditPost(ViewEditPost viewEditPost){
        this.viewEditPost = viewEditPost;
        dataClient = APIUtils.getData();
    }

    @Override
    public void uploadImage(String uriImage) {
        Intent iImage = new Intent(Intent.ACTION_PICK);
        iImage.setType("image/*");

    }

    @Override
    public void uploadPost(int id_post, int id_dia_diem, String noi_dung, String image_name) {
        Call<CheckTrueFalse> callback = dataClient.editPostEdited(Common.CONTROLLER_POST,
                Common.ACTION_EDIT_POST, id_post, id_dia_diem, noi_dung, image_name);

        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) viewEditPost.nextPageToMain();
                else viewEditPost.EditPostFail();
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

            }
        });
    }
}
