package com.example.nguyenvantung.place.ViewHolder.Newfeed;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenvantung.place.Adapter.Newfeed.AvatarUserCommentAdapter;
import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.AvatarUserCommentModel;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.CountModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.Retrofit.DataClient;
import com.example.nguyenvantung.place.View.Home.Fragment.ViewNewfeedFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewfeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private View view;
    private ViewNewfeedFragment viewNewfeedFragment;
    public CircleImageView item_newfeed_img_avatar_user;
    public TextView item_newfeed_txt_username, item_newfeed_txt_description, item_newfeed_count_like;
    public ImageView item_newfeed_img_post, item_newfeed_favorite, item_newfeed_comment, item_newfeed_share;
    public RecyclerView item_newfeed_recyclerview_avatar_user_comment;

    public DataClient dataClient;
    public int id_post;
    public int id_user;

    public NewfeedViewHolder(View itemView, ViewNewfeedFragment viewNewfeedFragment) {
        super(itemView);
        this.view = itemView;
        dataClient = APIUtils.getData();

        addControls();
        addEvents();
    }

    public void addDataID(int id_user, int id_post){
        this.id_user = id_user;
        this.id_post = id_post;

        //check liked
        Call<CheckTrueFalse> callback = dataClient.checkLike(Common.CONTROLLER_LIKE,
                Common.ACTION_CHECK_LIKED, id_user, id_post);
        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) setFavorite();
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

            }
        });

        //get avatar user comment
        Call<List<AvatarUserCommentModel>> callbackAvatarUser = dataClient.getAvatarUserComment(
                Common.CONTROLLER_USER,
                Common.ACTION_GET_AVATAR_USER_COMMENT, id_post);
        callbackAvatarUser.enqueue(new Callback<List<AvatarUserCommentModel>>() {
            @Override
            public void onResponse(Call<List<AvatarUserCommentModel>> call, Response<List<AvatarUserCommentModel>> response) {
//                Log.d("kiemtra", response.body().get(0).getAvartar());
                if (response.body().size() > 0) addDataUserComment(response.body());
            }

            @Override
            public void onFailure(Call<List<AvatarUserCommentModel>> call, Throwable t) {
                Log.d("kiemtra", "loi: " + t.getLocalizedMessage());
            }
        });

        //get count like
        Call<CountModel> callbackCountLike = dataClient.getCountLike(Common.CONTROLLER_LIKE,
                Common.ACTION_COUNT_LIKE, id_post);
        callbackCountLike.enqueue(new Callback<CountModel>() {
            @Override
            public void onResponse(Call<CountModel> call, Response<CountModel> response) {
                if (response.body().getCount() != 0){
                    item_newfeed_count_like.setVisibility(View.VISIBLE);
                    item_newfeed_count_like.setText(response.body().getCount() + " " + view.getResources().getString(R.string.count_like));
                }
            }

            @Override
            public void onFailure(Call<CountModel> call, Throwable t) {

            }
        });
    }


    private void addControls() {
        item_newfeed_img_avatar_user = view.findViewById(R.id.item_newfeed_img_avatar_user);
        item_newfeed_txt_username    = view.findViewById(R.id.item_newfeed_txt_username);
        item_newfeed_txt_description = view.findViewById(R.id.item_newfeed_txt_description);
        item_newfeed_img_post        = view.findViewById(R.id.item_newfeed_img_post);
        item_newfeed_recyclerview_avatar_user_comment = view.findViewById(R.id.item_newfeed_recyclerview_avatar_user_comment);
        item_newfeed_favorite        = view.findViewById(R.id.item_newfeed_favorite);
        item_newfeed_comment         = view.findViewById(R.id.item_newfeed_comment);
        item_newfeed_share           = view.findViewById(R.id.item_newfeed_share);
        item_newfeed_count_like      = view.findViewById(R.id.item_newfeed_count_like);
        item_newfeed_count_like.setVisibility(View.GONE);
    }

    private void addEvents() {
        item_newfeed_favorite.setOnClickListener(this);
        item_newfeed_comment.setOnClickListener(this);
        item_newfeed_share.setOnClickListener(this);
    }

    public void addDataUserComment(List<AvatarUserCommentModel> avatarList){
        item_newfeed_recyclerview_avatar_user_comment
                .setHasFixedSize(true);
        item_newfeed_recyclerview_avatar_user_comment.setLayoutManager(
                new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL, false));
        AvatarUserCommentAdapter avatarUserCommentAdapter = new AvatarUserCommentAdapter(view.getContext(), avatarList);
        item_newfeed_recyclerview_avatar_user_comment.setAdapter(avatarUserCommentAdapter);
    }

    public void setFavorite(){
        item_newfeed_favorite.setImageLevel(1);
    }

    private void likePost() {
        boolean status;
        if (item_newfeed_favorite.getDrawable().getLevel() == 0){
            //nếu icon bằng 0 tức là chưa like bài đăng
            Call<CheckTrueFalse> callback = dataClient.likePost(Common.CONTROLLER_LIKE,
                    Common.ACTION_INSERT_LIKE, id_user, id_post);
            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    if (response.body().getStatus().equals("true"))item_newfeed_favorite.setImageLevel(1);
                    else item_newfeed_favorite.setImageLevel(0);
                    Log.d("kiemtra", id_user + "--" + id_post);
                    Log.d("kiemtra", response.body().getStatus());
                }

                @Override
                public void onFailure(Call<CheckTrueFalse> call, Throwable t) {
                    Log.d("kiemtra", t.getLocalizedMessage());
                }
            });
        }else {
            Call<CheckTrueFalse> callback = dataClient.unLikePost(Common.CONTROLLER_LIKE,
                    Common.ACTION_UN_LIKE, id_user, id_post);
            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    if (response.body().getStatus().equals("true")) item_newfeed_favorite.setImageLevel(0);
                    else item_newfeed_favorite.setImageLevel(1);
                }

                @Override
                public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_newfeed_favorite:
                likePost();
                break;
            case R.id.item_newfeed_comment:

                break;
            case R.id.item_newfeed_share:

                break;
        }
    }
}
