package com.example.nguyenvantung.place.Prescenter.Comment;

import android.util.Log;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.CommentModel;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;
import com.example.nguyenvantung.place.View.Comment.ViewCommentActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescenterLogicComment implements PrescenterIMPComment {
    private ViewCommentActivity viewCommentActivity;

    public PrescenterLogicComment(ViewCommentActivity viewCommentActivity){
        this.viewCommentActivity = viewCommentActivity;
    }

    @Override
    public void getComment(int id_post) {
        Call<List<CommentModel>> callback = Common.DATA_CLIENT.getAllCommentFromIDPost(Common.CONTROLLER_COMMENT,
                Common.ACTION_GET_ALL_COMMENT_FROM_ID_POST, id_post);

        callback.enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                viewCommentActivity.getCommentSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                Log.d("kiemtra", "loi: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getInforUserFromID(int id_user) {
        Call<UserModel> callback = Common.DATA_CLIENT.getUserFromIDUser(Common.CONTROLLER_USER,
                Common.ACTION_GET_INFO_USER_FROM_ID, id_user);

        callback.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                viewCommentActivity.getInforUserFromIDSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("kiemgtra", "loi: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void checkLikePost(int id_user, int id_post) {
        Call<CheckTrueFalse> callback = Common.DATA_CLIENT.checkLike(Common.CONTROLLER_LIKE,
                Common.ACTION_CHECK_LIKED, id_user, id_post);

        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) viewCommentActivity.userLikedPost();
                else viewCommentActivity.userNotlied();
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

            }
        });
    }

    @Override
    public void insertComment(int id_user, int id_post, String noi_dung, String dateTime) {
        Call<CheckTrueFalse> callback = Common.DATA_CLIENT.insertComment(Common.CONTROLLER_COMMENT,
                Common.ACTION_INSERT_COMMENT, id_user, id_post, noi_dung, dateTime);

        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) viewCommentActivity.commentSuccess();
                else viewCommentActivity.commentFail();
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {
                Log.d("kiemtra", "loi: " + t.getLocalizedMessage());
            }
        });
    }
}
