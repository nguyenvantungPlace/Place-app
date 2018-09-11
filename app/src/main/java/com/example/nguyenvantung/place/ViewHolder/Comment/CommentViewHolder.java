package com.example.nguyenvantung.place.ViewHolder.Comment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.CommentModel;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Comment.ViewCommentActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnFocusChangeListener {
    private View view;
    private CircleImageView item_comment_img_avatar;
    private TextView item_comment_username, item_comment_comment, item_comment_time, item_comment_txt_send_comment_edited;
    private LinearLayout item_comment_layout;
    private EditText item_comment_ed_edit;
    private ImageView item_comment_like_comment, item_comment_reply_comment;

    private CommentModel commentModel;
    private ViewCommentActivity viewCommentActivity;

    private String stauts = "";

    public CommentViewHolder(View itemView, ViewCommentActivity viewCommentActivity) {
        super(itemView);
        this.viewCommentActivity = viewCommentActivity;
        this.view = itemView;

        addControlls();
        addEvents();
    }

    private void addControlls() {
        item_comment_img_avatar = view.findViewById(R.id.item_comment_img_avatar);
        item_comment_username   = view.findViewById(R.id.item_comment_username);
        item_comment_comment    = view.findViewById(R.id.item_comment_comment);
        item_comment_time       = view.findViewById(R.id.item_comment_time);
        item_comment_layout     = view.findViewById(R.id.item_comment_layout);
        item_comment_ed_edit    = view.findViewById(R.id.item_comment_ed_edit);
        item_comment_txt_send_comment_edited = view.findViewById(R.id.item_comment_txt_send_comment_edited);
        item_comment_like_comment = view.findViewById(R.id.item_comment_like_comment);
        item_comment_reply_comment= view.findViewById(R.id.item_comment_reply_comment);

    }

    private void addEvents() {
        item_comment_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showMenuComment();
                return false;
            }
        });
        item_comment_ed_edit.setOnFocusChangeListener(this);
        item_comment_txt_send_comment_edited.setOnClickListener(this);
        item_comment_like_comment.setOnClickListener(this);
        item_comment_reply_comment.setOnClickListener(this);
    }

    public void addDataFromAdapter(CommentModel commentModel){
        this.commentModel = commentModel;
        item_comment_comment.setText(commentModel.getNoiDung());

        //get infor user from id
        getInfoUserFromId(Integer.parseInt(commentModel.getIdNguoiDung()));

        //set ic like
        checkLikeComment();
    }

    //check xem người dùng đã like comment hay chưa
    private void checkLikeComment() {
        Call<CheckTrueFalse> callback = Common.DATA_CLIENT.checkLikeComment(Common.CONTROLLER_LIKE_COMMENT,
                Common.ACTION_CHECKLINE_COMMENT, Integer.parseInt(commentModel.getIdBinhLuan()), Common.USER.getIdNguoiDung());
        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) item_comment_like_comment.setImageLevel(1);
                else item_comment_like_comment.setImageLevel(0);
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

            }
        });
    }

    //show menu comment
    private void showMenuComment(){
        final PopupMenu popupMenu = new PopupMenu(view.getContext(), item_comment_layout);
        popupMenu.getMenuInflater().inflate(R.menu.menu_comment, popupMenu.getMenu());
        if (Common.USER.getIdNguoiDung() != Integer.parseInt(commentModel.getIdNguoiDung())) {
            popupMenu.getMenu().getItem(1).setEnabled(false);
            popupMenu.getMenu().getItem(2).setEnabled(false);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_comment_coppy :
                        coppyComment();
                        break;
                    case R.id.menu_comment_edit :
                        editComment();
                        break;
                    case R.id.menu_comment_delete :
                        deleteComment();
                        break;
                    case R.id.menu_comment_cancel :
                        popupMenu.dismiss();
                        break;
                }

                return false;
            }
        });

        popupMenu.show();

    }

    private void deleteComment() {
        Call<CheckTrueFalse> callback = Common.DATA_CLIENT.deleteComment(Common.CONTROLLER_COMMENT,
                Common.ACTION_DELETE_COMMENT, Integer.parseInt(commentModel.getIdBinhLuan()));

        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) viewCommentActivity.deleteComment(getAdapterPosition());
                else Toast.makeText(view.getContext(), "Error Internet", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

            }
        });

    }

    //sửa comment
    private void editComment() {
        item_comment_ed_edit.setText(item_comment_comment.getText().toString());
        item_comment_comment.setVisibility(View.GONE);
        item_comment_ed_edit.setVisibility(View.VISIBLE);
    }

    //sao chép văn bản trong comment
    private void coppyComment() {
        ClipboardManager clipboardManager = (ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(view.getResources().getString(R.string.label), item_comment_comment.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(view.getContext(), "coppy success", Toast.LENGTH_SHORT).show();
    }

    private void sendEditComment() {
        Call<CheckTrueFalse> callback = Common.DATA_CLIENT.editComment(Common.CONTROLLER_COMMENT,
                Common.ACTION_EDIT_COMMENT, Integer.parseInt(commentModel.getIdBinhLuan())
                , item_comment_ed_edit.getText().toString());

        callback.enqueue(new Callback<CheckTrueFalse>() {
            @Override
            public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                if (response.body().getStatus().equals("true")) editCommentSuccess();
                else editCommentFail();
            }

            @Override
            public void onFailure(Call<CheckTrueFalse> call, Throwable t) {
                Log.d("kiemtra", "loi: " + t.getLocalizedMessage());
            }
        });
    }

    //edit comment khi gui len server thanh cong
    private void editCommentSuccess(){
//        item_comment_comment.setText(item_comment_ed_edit.getText().toString());
        commentModel.setNoiDung(item_comment_ed_edit.getText().toString());
        item_comment_ed_edit.setVisibility(View.GONE);
        item_comment_comment.setVisibility(View.VISIBLE);
        item_comment_txt_send_comment_edited.setVisibility(View.GONE);
        viewCommentActivity.editCommentSuccess(getAdapterPosition(), commentModel);
        Toast.makeText(view.getContext(), "Edit comment Success", Toast.LENGTH_SHORT).show();
    }

    //edit comment khi gui len server bi loi
    private void editCommentFail(){
        Toast.makeText(view.getContext(), "Edit comment Fail, Check Internet", Toast.LENGTH_SHORT).show();
    }


    //lấy thông tin người dùng từ id
    private void getInfoUserFromId(int id_user) {
        Call<UserModel> callback = Common.DATA_CLIENT.getUserFromIDUser(Common.CONTROLLER_USER,
                Common.ACTION_GET_INFO_USER_FROM_ID, id_user);

        callback.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.body().getAvatar().equals("")){
                    Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + response.body().getAvatar())
                            .into(item_comment_img_avatar);
                    item_comment_username.setText(response.body().getTenNguoiDung());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    private void likeComment() {
        if (item_comment_like_comment.getDrawable().getLevel() == 0){
            //like comment
            Call<CheckTrueFalse> callback = Common.DATA_CLIENT.likeComment(Common.CONTROLLER_LIKE_COMMENT,
                    Common.ACTION_LIKE_COMMENT, Integer.parseInt(commentModel.getIdBinhLuan()), Common.USER.getIdNguoiDung());
            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    if (response.body().getStatus().equals("true")) item_comment_like_comment.setImageLevel(1);
                    else item_comment_like_comment.setImageLevel(0);
                }

                @Override
                public void onFailure(Call<CheckTrueFalse> call, Throwable t) {

                }
            });
        } else if (item_comment_like_comment.getDrawable().getLevel() == 1) {
            //unline comment
            Call<CheckTrueFalse> callback = Common.DATA_CLIENT.unLikeComment(Common.CONTROLLER_LIKE_COMMENT,
                    Common.ACTION_UNLIKE_COMMENT, Integer.parseInt(commentModel.getIdBinhLuan()), Common.USER.getIdNguoiDung());
            callback.enqueue(new Callback<CheckTrueFalse>() {
                @Override
                public void onResponse(Call<CheckTrueFalse> call, Response<CheckTrueFalse> response) {
                    if (response.body().getStatus().equals("true")) item_comment_like_comment.setImageLevel(0);
                    else item_comment_like_comment.setImageLevel(1);
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
            case R.id.item_comment_txt_send_comment_edited :
                sendEditComment();
                break;
            case R.id.item_comment_like_comment :
                likeComment();
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.item_comment_ed_edit && b == true)
            item_comment_txt_send_comment_edited.setVisibility(View.VISIBLE);
        else if (view.getId() == R.id.item_comment_ed_edit && b == false)
            item_comment_txt_send_comment_edited.setVisibility(View.GONE);
    }
}
