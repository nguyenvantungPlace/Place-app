package com.example.nguyenvantung.place.View.EditPost;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Prescenter.EditPost.PrescenterLogicEditPost;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Home.Fragment.NewsFeedFragment;
import com.squareup.picasso.Picasso;

public class EditPostActivity extends AppCompatActivity implements ViewEditPost,View.OnClickListener {
    private Toolbar editpost_toolbar;
    private TextInputEditText editpost_txt_desciption;
    private AutoCompleteTextView editpost_txt_place;
    private ImageView editpost_image;
    private LinearLayout editpost_upload_post, editpost_edit_image;

    private PrescenterLogicEditPost prescenterLogicEditPost;
    private NewfeedModel newfeedModel;
    private String image_name;
    private int id_post;
    private int POSSIONEDIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        prescenterLogicEditPost = new PrescenterLogicEditPost(this);

        addControls();
        addDataFromIntent();
        addEvent();
    }

    private void addControls() {
        editpost_toolbar        = findViewById(R.id.editpost_toolbar);
        editpost_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        editpost_txt_desciption = findViewById(R.id.editpost_txt_desciption);
        editpost_txt_place      = findViewById(R.id.editpost_txt_place);
        editpost_image          = findViewById(R.id.editpost_image);
        editpost_upload_post    = findViewById(R.id.editpost_upload_post);
        editpost_edit_image     = findViewById(R.id.editpost_edit_image);
    }

    private void addDataFromIntent() {
        Intent iGetData = getIntent();

        id_post = Integer.parseInt(Common.NEWFEEDEDIT.getIdBaiDang());
        editpost_txt_desciption.setText(Common.NEWFEEDEDIT.getNoiDung());
        editpost_txt_place.setText(Common.NEWFEEDEDIT.getIdDiaDiem());
        image_name = Common.NEWFEEDEDIT.getAnh();
        Picasso.get().load(Common.BASE_URL_USER_IMAGE_POST + Common.NEWFEEDEDIT.getAnh()).into(editpost_image);
    }

    private void addEvent() {
        editpost_upload_post.setOnClickListener(this);
        editpost_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void OpenIntentGetImage() {

    }

    @Override
    public void nextPageToMain() {
        Toast.makeText(this, "Edit success", Toast.LENGTH_SHORT).show();
        new NewsFeedFragment().changePostEdited(POSSIONEDIT);
        onBackPressed();
    }

    @Override
    public void EditPostFail() {
        Toast.makeText(this, "Edit Fail", Toast.LENGTH_SHORT).show();
    }

    private void pustEditPost() {
        Common.NEWFEEDEDIT.setNoiDung(editpost_txt_desciption.getText().toString());
        Common.NEWFEEDEDIT.setIdDiaDiem(editpost_txt_place.getText().toString());
        Common.NEWFEEDEDIT.setAnh(image_name);

        prescenterLogicEditPost.uploadPost(id_post,
                Integer.parseInt(editpost_txt_place.getText().toString()),
                editpost_txt_desciption.getText().toString(), image_name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.editpost_upload_post:
                pustEditPost();
                break;
            case R.id.editpost_edit_image:
                OpenIntentGetImage();
                break;
        }
    }
}
