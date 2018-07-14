package com.example.nguyenvantung.place.ViewHolder.Newfeed;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenvantung.place.Adapter.Newfeed.AvatarUserCommentAdapter;
import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.AvatarUserCommentModel;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.CountModel;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Home.Fragment.ViewNewfeedFragment;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewfeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnFocusChangeListener{
    private View view;
    private ViewNewfeedFragment viewNewfeedFragment;
    private CircleImageView item_newfeed_img_avatar_user, item_newfeed_img_avatar_user_comment;
    private TextView item_newfeed_txt_username, item_newfeed_txt_description, item_newfeed_count_like, item_newfeed_tv_click_comment;
    private ImageView item_newfeed_img_post, item_newfeed_favorite, item_newfeed_comment, item_newfeed_share;
    private RecyclerView item_newfeed_recyclerview_avatar_user_comment;
    private ImageView item_newfeed_more;
    private EditText item_newfeed_ed_comment;

    private NewfeedModel postModel;
    private List<AvatarUserCommentModel> listAvatarUserCommentModel;

    public NewfeedViewHolder(View itemView, ViewNewfeedFragment viewNewfeedFragment) {
        super(itemView);
        this.view = itemView;
        this.viewNewfeedFragment = viewNewfeedFragment;

        addControls();
        addEvents();
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
        item_newfeed_more            = view.findViewById(R.id.item_newfeed_more);
        item_newfeed_img_avatar_user_comment = view.findViewById(R.id.item_newfeed_img_avatar_user_comment);
        item_newfeed_ed_comment      = view.findViewById(R.id.item_newfeed_ed_comment);
        item_newfeed_tv_click_comment= view.findViewById(R.id.item_newfeed_tv_click_comment);
    }

    private void addEvents() {
        item_newfeed_favorite.setOnClickListener(this);
        item_newfeed_comment.setOnClickListener(this);
        item_newfeed_share.setOnClickListener(this);
        item_newfeed_more.setOnClickListener(this);
        item_newfeed_more.setOnClickListener(this);
        item_newfeed_ed_comment.setOnFocusChangeListener(this);
        item_newfeed_tv_click_comment.setOnClickListener(this);
        item_newfeed_tv_click_comment.setOnClickListener(this);
    }

    // đưa data từ adapter vào viewholder
    public void initData(NewfeedModel newfeedModel){
        this.postModel = newfeedModel;
        //addData
        addData();

        //check liked
        checkLike();

        //get avatar user comment
//        getAvatarUserComment();

        //get count like
        getCountLike();
    }

    // gán dữ liệu vào controll
    private void addData() {
        //add image post
        Picasso.get().load(Common.BASE_URL_USER_IMAGE_POST + postModel.getAnh()).into(item_newfeed_img_post);
        item_newfeed_txt_description.setText(postModel.getNoiDung());
        setImageAndNameUser();

        //add image avatar user comment
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + Common.USER.getAvatar())
                .into(item_newfeed_img_avatar_user_comment);
    }

    //gán avatar người đăng
    private void setImageAndNameUser() {
        Call<UserModel> callback = Common.DATA_CLIENT.getUserFromIDUser(Common.CONTROLLER_USER,
                Common.ACTION_GET_INFO_USER_FROM_ID, Integer.parseInt(postModel.getIdNguoiDung()));
        callback.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + response.body().getAvatar())
                        .into(item_newfeed_img_avatar_user);
                item_newfeed_txt_username.setText(response.body().getTenNguoiDung());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("kiemtra", "loi: " + t.getLocalizedMessage());
            }
        });
    }

    //lấy số lượng like
    private void getCountLike(){
        Call<CountModel> callbackCountLike = Common.DATA_CLIENT.getCountLike(Common.CONTROLLER_LIKE,
                Common.ACTION_COUNT_LIKE, Integer.parseInt(postModel.getIdBaiDang()));
        callbackCountLike.enqueue(new Callback<CountModel>() {
            @Override
            public void onResponse(Call<CountModel> call, Response<CountModel> response) {
                if (response.body().getCount() > 0){
                    item_newfeed_count_like.setVisibility(View.VISIBLE);
                    item_newfeed_count_like.setText(response.body().getCount() + " " + view.getResources().getString(R.string.count_like));
                }else item_newfeed_count_like.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CountModel> call, Throwable t) {

            }
        });
    }

//    private void getAvatarUserComment(){
//        listAvatarUserCommentModel = new ArrayList<>();
//        listAvatarUserCommentModel.clear();
//        Call<List<AvatarUserCommentModel>> callbackAvatarUser = Common.DATA_CLIENT.getAvatarUserComment(
//                Common.CONTROLLER_USER,
//                Common.ACTION_GET_AVATAR_USER_COMMENT, Integer.parseInt(postModel.getIdBaiDang()));
//        callbackAvatarUser.enqueue(new Callback<List<AvatarUserCommentModel>>() {
//            @Override
//            public void onResponse(Call<List<AvatarUserCommentModel>> call, Response<List<AvatarUserCommentModel>> response) {
//                if (!response.body().isEmpty()){
//                    listAvatarUserCommentModel.addAll(response.body());
//                }
//                addDataUserComment(listAvatarUserCommentModel);
//            }
//
//            @Override
//            public void onFailure(Call<List<AvatarUserCommentModel>> call, Throwable t) {
//                Log.d("kiemtra", "loi: " + t.getLocalizedMessage());
//            }
//        });
//    }


    //kiểm tra người dùng có thích bài đăng hay không
    private void checkLike(){
        Call<CheckTrueFalse> callback = Common.DATA_CLIENT.checkLike(Common.CONTROLLER_LIKE,
                Common.ACTION_CHECK_LIKED, Common.USER.getIdNguoiDung(), Integer.parseInt(postModel.getIdBaiDang()));
        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) item_newfeed_favorite.setImageLevel(1);
                else item_newfeed_favorite.setImageLevel(0);
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {
                Log.d("kiemtra", "loi like: " + t.getLocalizedMessage());
            }
        });
    }


    //gán dữ liệu người dùng comment
    public void addDataUserComment(List<AvatarUserCommentModel> avatarList){
        if (avatarList.size() > 0) {
//            item_newfeed_recyclerview_avatar_user_comment
//                    .setHasFixedSize(true);
            item_newfeed_recyclerview_avatar_user_comment.setLayoutManager(
                    new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
            AvatarUserCommentAdapter avatarUserCommentAdapter = new AvatarUserCommentAdapter(view.getContext(), avatarList);
            item_newfeed_recyclerview_avatar_user_comment.setAdapter(avatarUserCommentAdapter);
        }else item_newfeed_recyclerview_avatar_user_comment = null;
    }

    // like bài đăng
    private void likePost() {
        if (item_newfeed_favorite.getDrawable().getLevel() == 0){
            //nếu icon bằng 0 tức là chưa like bài đăng
            Call<CheckTrueFalse> callback = Common.DATA_CLIENT.likePost(Common.CONTROLLER_LIKE,
                    Common.ACTION_INSERT_LIKE, Common.USER.getIdNguoiDung(), Integer.parseInt(postModel.getIdBaiDang()));
            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    if (response.body().getStatus().equals("true"))item_newfeed_favorite.setImageLevel(1);
                    else item_newfeed_favorite.setImageLevel(0);
//                    Log.d("kiemtra", id_user + "--" + id_post);
                    Log.d("kiemtra", response.body().getStatus());

                    //like xong thì lấy lại danh sách người thích
                    getCountLike();
                }

                @Override
                public void onFailure(Call<CheckTrueFalse> call, Throwable t) {
                    Log.d("kiemtra", t.getLocalizedMessage());
                }
            });
        }else {
            Call<CheckTrueFalse> callback = Common.DATA_CLIENT.unLikePost(Common.CONTROLLER_LIKE,
                    Common.ACTION_UN_LIKE, Common.USER.getIdNguoiDung(), Integer.parseInt(postModel.getIdBaiDang()));
            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    if (response.body().getStatus().equals("true")) item_newfeed_favorite.setImageLevel(0);
                    else item_newfeed_favorite.setImageLevel(1);

                    //bỏ like xong thì lấy lại danh sách người thích
                    getCountLike();
                }

                @Override
                public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

                }
            });
        }
    }

    //chuyển trang edit bài đăng
    private void nextPageEdit(NewfeedModel postModel){
        viewNewfeedFragment.nextPageEditPost(postModel, getAdapterPosition());
    }

    //show menu khi nhấn vào icon 3 chấm
    private void showContextMenu() {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), item_newfeed_more);
        popupMenu.getMenuInflater().inflate(R.menu.contextmenu_item_rv, popupMenu.getMenu());
        if (Common.USER.getIdNguoiDung() != Integer.parseInt(postModel.getIdNguoiDung())){
            popupMenu.getMenu().getItem(1).setEnabled(false);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_rv_edit :
                        nextPageEdit(postModel);
                        break;
                    case R.id.menu_rv_hide :
                        hidePost();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    //ẩn bài đăng
    private void hidePost() {
        viewNewfeedFragment.hidePost(getAdapterPosition());
    }

    //chuyển page comment
    private void nextPageComment() {
        viewNewfeedFragment.nextPageComment(postModel);
    }

    //comment bài đăng ngay trên item bài đăng
    private void commentInPost() {
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        Call<CheckTrueFalse> callback = Common.DATA_CLIENT.insertComment(Common.CONTROLLER_COMMENT,
                Common.ACTION_INSERT_COMMENT, Common.USER.getIdNguoiDung(), Integer.parseInt(postModel.getIdBaiDang()),
                item_newfeed_ed_comment.getText().toString(), time);

        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) commentSucces();
                else commentFail();
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

            }
        });
    }

    private void commentSucces(){
        item_newfeed_ed_comment.setText("");
    }

    private void commentFail(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_newfeed_favorite:
                likePost();
                break;
            case R.id.item_newfeed_comment:
                nextPageComment();
                break;
            case R.id.item_newfeed_share:

                break;
            case R.id.item_newfeed_more:
                showContextMenu();
                break;
            case R.id.item_newfeed_tv_click_comment:
                commentInPost();
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.item_newfeed_ed_comment && b == true){
            item_newfeed_tv_click_comment.setVisibility(View.VISIBLE);
        }else if (view.getId() == R.id.item_newfeed_ed_comment && b != true){
            item_newfeed_tv_click_comment.setVisibility(View.GONE);
        }
    }
}
