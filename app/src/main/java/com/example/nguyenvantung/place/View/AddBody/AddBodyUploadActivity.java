package com.example.nguyenvantung.place.View.AddBody;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.Convert.Converter;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.UploadObject;
import com.example.nguyenvantung.place.Prescenter.AddBody.PrescenterIMPAddBodyUpload;
import com.example.nguyenvantung.place.Prescenter.AddBody.PrescenterLogicAddBodyUpload;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.Retrofit.DataClient;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddBodyUploadActivity extends AppCompatActivity implements View.OnClickListener, ViewAddBodyUpload {
    private Toolbar addbody_toolbar;
    private ImageView addbody_image;
    private LinearLayout upload_post;
    private ProgressDialog mProgressDialog;
    private TextInputEditText addbody_txt_desciption;
    private AutoCompleteTextView addbody_txt_place;

    private Intent intentData;
    private String uriImage;
    private Bitmap bitmapImage;
    private PrescenterLogicAddBodyUpload prescenterLogicAddBodyUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_body_upload);


        getDataIntent();
        addControls();
        addData();
        addProgressbarDialog();
        addEvents();
    }

    private void getDataIntent() {
        prescenterLogicAddBodyUpload = new PrescenterLogicAddBodyUpload(this);
        intentData = getIntent();
        uriImage = intentData.getStringExtra(Common.IMAGE_UPLOAD_URI);
        Log.d("kiemtra", uriImage);
        bitmapImage = (Bitmap) intentData.getExtras().get(Common.IMAGE_UPLOAD_BITMAP);
    }

    private void addControls() {
        addbody_toolbar        = findViewById(R.id.addbody_toolbar);
        addbody_image          = findViewById(R.id.addbody_image);
        upload_post            = findViewById(R.id.upload_post);
        addbody_txt_desciption = findViewById(R.id.addbody_txt_desciption);
        addbody_txt_place      = findViewById(R.id.addbody_txt_place);
    }

    private void addProgressbarDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getResources().getString(R.string.posting));
        mProgressDialog.setMessage(getResources().getString(R.string.requesting_to_server));
    }

    private void addData() {
        //add icon arrow for appbar
        addbody_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        //add data for image
        Log.d("kiemtra", uriImage);
        if (uriImage != null) {
            bitmapImage = BitmapFactory.decodeFile(uriImage);
        }

        addbody_image.setImageBitmap(bitmapImage);
    }

    private void addEvents() {
        addbody_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        upload_post.setOnClickListener(this);
    }

    private void uploadImagePost() {
        prescenterLogicAddBodyUpload.uploadImage(uriImage);
    }

    @Override
    public void uploadSuccess() {
        mProgressDialog.dismiss();
        Toast.makeText(this, "Upload success", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void uploadFail() {
        mProgressDialog.dismiss();
        Toast.makeText(this, "upload fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorTxtPlace() {
        addbody_txt_place.setError(getResources().getString(R.string.where_are_you));
        mProgressDialog.dismiss();
    }

    @Override
    public void uploadImageSucces(String imageName) {
        prescenterLogicAddBodyUpload.uploadPost(addbody_txt_place.getText().toString(),
                addbody_txt_desciption.getText().toString(),
                String.valueOf(System.currentTimeMillis()),
                imageName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.upload_post:
                mProgressDialog.show();
                uploadImagePost();
                break;
        }
    }
}
