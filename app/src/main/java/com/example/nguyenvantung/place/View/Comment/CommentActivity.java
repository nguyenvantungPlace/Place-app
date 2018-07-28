package com.example.nguyenvantung.place.View.Comment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenvantung.place.Adapter.Comment.CommentAdapter;
import com.example.nguyenvantung.place.Model.Convert.BlurImage;
import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.CommentModel;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;
import com.example.nguyenvantung.place.Prescenter.Comment.PrescenterLogicComment;
import com.example.nguyenvantung.place.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity implements ViewCommentActivity, View.OnClickListener,
        View.OnFocusChangeListener{
    private PrescenterLogicComment prescenterLogicComment;
    private ImageView comment_img_post;
    private TextView comment_txt_description, comment_txt_username, item_comment_txt_send_comment;
    private RecyclerView comment_recyclervew;
    private Toolbar comment_toolbar;
    private CircleImageView comment_img_avatar, item_comment_img_avatar_user_comment;
    private ImageView comment_img_like;
    private EditText item_comment_ed_comment;
    private ProgressDialog progressDialog;
    private ProgressBar comment_progressbar;

    private Intent intent;
    private NewfeedModel post;
    private CommentAdapter commentAdapter;
    private List<CommentModel> listComment;
    private String dateTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        listComment = new ArrayList<>();
        prescenterLogicComment = new PrescenterLogicComment(this);
        intent = getIntent();

        addControlls();
        addData();
        addEvents();
    }

    private void addControlls() {
        comment_img_post        = findViewById(R.id.comment_img_post);
        comment_txt_description = findViewById(R.id.comment_txt_description);
        comment_recyclervew     = findViewById(R.id.comment_recyclervew);
//        comment_blur_layout     = findViewById(R.id.comment_blur_layout);

        //init toolbar
        comment_toolbar         = findViewById(R.id.comment_toolbar);
        comment_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        comment_img_avatar      = findViewById(R.id.comment_img_avatar);
        comment_txt_username    = findViewById(R.id.comment_txt_username);
        comment_img_like        = findViewById(R.id.comment_img_like);
        item_comment_img_avatar_user_comment = findViewById(R.id.item_comment_img_avatar_user_comment);
        item_comment_ed_comment = findViewById(R.id.item_comment_ed_comment);
        item_comment_txt_send_comment = findViewById(R.id.item_comment_txt_send_comment);
        comment_progressbar     = findViewById(R.id.comment_progressbar);
    }

    private void addData() {
        post = (NewfeedModel) intent.getSerializableExtra(Common.INTENT_ID_POST);
        Picasso.get().load(Common.BASE_URL_USER_IMAGE_POST + post.getAnh())
                .into(comment_img_post, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        comment_img_post.buildDrawingCache();
                        comment_img_post.setImageBitmap(BlurImage.blur(getWindow().getContext()
                                , ((BitmapDrawable)comment_img_post.getDrawable()).getBitmap()));
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


        comment_txt_description.setText(post.getNoiDung());

        //add avatar ở ô comment
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + Common.USER.getAvatar())
                .into(item_comment_img_avatar_user_comment);

        //get data from internet
        prescenterLogicComment.getComment(Integer.parseInt(post.getIdBaiDang()));

        //getInforUserFromID
        prescenterLogicComment.getInforUserFromID(Integer.parseInt(post.getIdNguoiDung()));
    }

    private void addEvents() {
        comment_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        comment_img_like.setOnClickListener(this);
        item_comment_ed_comment.setOnFocusChangeListener(this);
        item_comment_txt_send_comment.setOnClickListener(this);
    }

    //lấy thành công comment trên internet
    @Override
    public void getCommentSuccess(List<CommentModel> list) {
        listComment.addAll(list);
        commentAdapter = new CommentAdapter(listComment, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        linearLayoutManager.setReverseLayout(true);
        comment_recyclervew.setLayoutManager(linearLayoutManager);
        comment_recyclervew.setHasFixedSize(true);
        comment_recyclervew.setAdapter(commentAdapter);
        comment_progressbar.setVisibility(View.GONE);
    }

    //lấy thông tin người dùng qua id
    @Override
    public void getInforUserFromIDSuccess(UserModel user) {
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + user.getAvatar()).into(comment_img_avatar);
        comment_txt_username.setText(user.getTenNguoiDung());
        prescenterLogicComment.checkLikePost(Integer.parseInt(post.getIdNguoiDung()), Integer.parseInt(post.getIdBaiDang()));
    }

    //nguoi dung da like bai dang
    @Override
    public void userLikedPost() {
        comment_img_like.setImageLevel(1);
    }

    //nguoi dung chua like bai dang
    @Override
    public void userNotlied() {
        comment_img_like.setImageLevel(0);
    }

    @Override
    public void commentSuccess() {
        item_comment_ed_comment.clearFocus();
        if (listComment.isEmpty()){
            prescenterLogicComment.getComment(Integer.parseInt(post.getIdBaiDang()));
        }else {
            listComment.add(new
                    CommentModel(
                    (Integer.parseInt(listComment.get(listComment.size() - 1).getIdBaiDang()) + 1) + "",
                    Common.USER.getIdNguoiDung() + "", post.getIdBaiDang(), item_comment_ed_comment.getText().toString(),
                    dateTime));
        }
        commentAdapter.notifyDataSetChanged();
        item_comment_ed_comment.setText("");
        comment_recyclervew.scrollToPosition(listComment.size());
        progressDialog.dismiss();
    }

    @Override
    public void commentFail() {
        Toast.makeText(this, "Comment Fail", Toast.LENGTH_SHORT).show();
    }

    //khi edit comment thành công thì ta thay phần tử thứ possion trong list danh sách comment
    @Override
    public void editCommentSuccess(int possition, CommentModel commentModel) {
        listComment.set(possition, commentModel);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteComment(int possition) {
        listComment.remove(possition);
        commentAdapter.notifyDataSetChanged();
    }


    //like bai dang tren thanh toolbar
    private void likePost() {
        if (comment_img_like.getDrawable().getLevel() == 0){
            //like
            Call<CheckTrueFalse> callback = Common.DATA_CLIENT.likePost(Common.CONTROLLER_LIKE,
                    Common.ACTION_INSERT_LIKE, Common.USER.getIdNguoiDung(), Integer.parseInt(post.getIdBaiDang()));

            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    if (response.body().getStatus().equals("true")) comment_img_like.setImageLevel(1);
                    else comment_img_like.setImageLevel(0);
                }

                @Override
                public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

                }
            });
        }else {
            //bor like
            Call<CheckTrueFalse> callback = Common.DATA_CLIENT.unLikePost(Common.CONTROLLER_LIKE,
                    Common.ACTION_UN_LIKE, Common.USER.getIdNguoiDung(), Integer.parseInt(post.getIdBaiDang()));

            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    if (response.body().getStatus().equals("true")) comment_img_like.setImageLevel(0);
                    else comment_img_like.setImageLevel(1);
                }

                @Override
                public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

                }
            });
        }
    }

    //insert comment l3n server
    private void insertComment() {
        dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        prescenterLogicComment.insertComment(Common.USER.getIdNguoiDung(), Integer.parseInt(post.getIdBaiDang())
                , item_comment_ed_comment.getText().toString(), dateTime);
    }

    private void showDialog(String title){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.requesting_to_server));
        progressDialog.setTitle(title);
        progressDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.comment_img_like :
                likePost();
                break;
            case R.id.item_comment_txt_send_comment :
                showDialog(getResources().getString(R.string.comment));
                insertComment();
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.item_comment_ed_comment && b == true){
            item_comment_txt_send_comment.setVisibility(View.VISIBLE);
        }else if (view.getId() == R.id.item_comment_ed_comment && b == false){
            item_comment_txt_send_comment.setVisibility(View.GONE);
        }
    }
}
