package com.example.nguyenvantung.place.ViewHolder.User;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenvantung.place.Model.Convert.BlurImage;
import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPostViewHolder extends RecyclerView.ViewHolder {
    public static PlaceModel placeModel1;

//    public ImageView item_user_post_img;
    private View view;
    private ImageView item_checkin_img_first, item_checkin_img_second, item_checkin_img_third;
    private CircleImageView item_checkin_img_avatar;
    private TextView item_checkin_txt_name;
    private FrameLayout item_checkin_framelayout;

    private PlaceModel placeModel;
    private List<NewfeedModel> listPost;

    public UserPostViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;

        addControls();
        addEvents();
    }

    public void getDataFromAdapter(PlaceModel placeModel){
        this.placeModel = placeModel;
        listPost = new ArrayList<>();

        getData();
    }

    private void addControls() {
//        item_user_post_img = view.findViewById(R.id.item_user_post_img);
        item_checkin_img_first  = view.findViewById(R.id.item_checkin_img_first);
//        item_checkin_framelayout= view.findViewById(R.id.item_checkin_framelayout);
        item_checkin_img_avatar = view.findViewById(R.id.item_checkin_img_avatar);
        item_checkin_txt_name   = view.findViewById(R.id.item_checkin_txt_name);
    }

    private void getData() {
        getPostFromIDUserUDPlace();
    }

    private void getPostFromIDUserUDPlace() {
        Call<List<NewfeedModel>> callback = Common.DATA_CLIENT.getPostFromIDUserIDPlace(Common.CONTROLLER_POST,
                Common.ACTION_GET_POST_FROM_IDPLACE, Common.USER.getIdNguoiDung()
                , Integer.parseInt(placeModel.getIdDiaDiem()));

        callback.enqueue(new Callback<List<NewfeedModel>>() {
            @Override
            public void onResponse(Call<List<NewfeedModel>> call, Response<List<NewfeedModel>> response) {
                if (response.body() != null) listPost = response.body();
                getInfoPlace(Integer.parseInt(response.body().get(0).getIdDiaDiem()));
            }

            @Override
            public void onFailure(Call<List<NewfeedModel>> call, Throwable t) {
                Log.d("kiemtra", "loi getPostFromIDUserIDPlace: " + t.getLocalizedMessage());
            }
        });
    }

    private void getInfoPlace(int id_place) {
        Call<PlaceModel> callback = Common.DATA_CLIENT.getPlaceFromID(Common.CONTROLLER_PLACE,
                Common.ACTION_GET_PLACE_FROM_ID, id_place);

        callback.enqueue(new Callback<PlaceModel>() {
            @Override
            public void onResponse(Call<PlaceModel> call, Response<PlaceModel> response) {
                if (response.body() != null) placeModel = response.body();
                addData();
            }

            @Override
            public void onFailure(Call<PlaceModel> call, Throwable t) {
                Log.d("kiemtra", "loi: " + t.getMessage());
            }
        });
    }

    private void addData() {
        Log.d("test", placeModel.getTenDiaDiem());
        Picasso.get().load(Common.BASE_URL_USER_IMAGE_POST + listPost.get(0).getAnh())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        item_checkin_img_first.setImageBitmap(BlurImage.blur(view.getContext(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

        item_checkin_txt_name.setText(placeModel.getTenDiaDiem());
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + placeModel.getAvatar())
                .into(item_checkin_img_avatar);
    }

    private void addEvents() {

    }
}
