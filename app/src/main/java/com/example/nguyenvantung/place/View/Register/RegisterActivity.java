package com.example.nguyenvantung.place.View.Register;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.Convert.Converter;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;
import com.example.nguyenvantung.place.Prescenter.Register.PrescenterLogicRegister;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Main.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, ViewRegisterActivity
, View.OnFocusChangeListener{
    private Toolbar register_toolbar;
    private CircleImageView register_img_avatar;
    private TextView register_txt_change_avatar, register_action_register;
    private TextInputEditText register_edit_username, register_edit_passowrd, register_edit_repassword, register_name;
    private ProgressDialog progressDialog;

    private PrescenterLogicRegister prescenterLogicRegister;

    private String uri_image = null;
    private Bitmap bitmapImage = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        prescenterLogicRegister = new PrescenterLogicRegister(this);
        addControls();
        addEvents();
    }

    private void addControls() {
        //init toolbar
        register_toolbar = findViewById(R.id.register_toolbar);
        register_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        //init controls
        register_img_avatar        = findViewById(R.id.register_img_avatar);
        register_txt_change_avatar = findViewById(R.id.register_txt_change_avatar);
        register_action_register   = findViewById(R.id.register_action_register);
        register_edit_username     = findViewById(R.id.register_edit_username);
        register_edit_passowrd     = findViewById(R.id.register_edit_passowrd);
        register_edit_repassword   = findViewById(R.id.register_edit_repassword);
        register_name              = findViewById(R.id.register_name);
    }

    private void addEvents() {
        //
        register_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        register_img_avatar.setOnClickListener(this);
        register_txt_change_avatar.setOnClickListener(this);

        register_edit_username.setOnFocusChangeListener(this);
        register_edit_passowrd.setOnFocusChangeListener(this);
        register_action_register.setOnClickListener(this);
    }

    //hàm chuyển sang màn hình sang màn hình lưu hình ảnh
    public void getImage(){
        Intent iSelectImage = new Intent(Intent.ACTION_PICK);
        iSelectImage.setType("image/*");
        startActivityForResult(iSelectImage, Common.REQUEST_CODE_SELECT_IMAGE);
    }

    private void checkpermissionReadEX() {
        if (Build.VERSION.SDK_INT >= 23){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        Common.REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE);
            }else {
                getImage();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //set image
        if (requestCode == Common.REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null){
            try {
                register_txt_change_avatar.setTextColor(getResources().getColor(R.color.blue_secondary));
                uri_image = data.getData().toString();
                bitmapImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
                register_img_avatar.setImageBitmap(bitmapImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Common.REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            getImage();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void userNameErrorLegth() {
        register_edit_username.setError(getResources().getString(R.string.user_name_error_legth));
    }

    //user name da duoc su dung
    @Override
    public void userNameUsed() {
        register_edit_username.setError(getResources().getString(R.string.username_used));
    }//end user name da duoc su dung

    //hàm cho biết bạn đã nhập mật khẩu sai với fomat(chiều dài lớn hơn 8 kí tự và nhỏ hơn 20)
    @Override
    public void fomatPassword() {
        register_edit_passowrd.setError(getResources().getString(R.string.password_false));
    }

    // hàm yêu cầu thêm avatar mới được đăng ký
    @Override
    public void requestUpImage() {
        dimissProgressbarDialog();
        register_txt_change_avatar.setTextColor(Color.RED);
    }

    //hàm cảnh báo điền lại mật khẩu không đúng
    @Override
    public void rePasswordFalse() {
        dimissProgressbarDialog();
        register_edit_repassword.setError(getResources().getString(R.string.repassword_false));
    }

    @Override
    public void requestName() {
        register_name.setError(getResources().getString(R.string.requesting_name_error));
    }

    //các điều kiện đều thỏa mãn thì cho phép đăng ký
    @Override
    public void register() {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//
//        String encode_imae = Base64.encodeToString(bytes, Base64.DEFAULT);

        prescenterLogicRegister.registerUser(register_name.getText().toString(),
                register_edit_username.getText().toString(),
                register_edit_passowrd.getText().toString(),
                new Converter().ConverImageToBase64(bitmapImage));
    }

    //hàm đăng ký thành công
    @Override
    public void registerSuccess() {
        dimissProgressbarDialog();
        showProgressbarDialog(Common.LOGIN);
        prescenterLogicRegister.loginUser(register_edit_username.getText().toString(), register_edit_passowrd.getText().toString());
    }

    //hàm đăng ký thất bại
    @Override
    public void registerFail() {
        Toast.makeText(this, "register fail", Toast.LENGTH_SHORT).show();
        dimissProgressbarDialog();
    }

    // mở dialog progress bar khi nhấn đăng ký, để người dùng chờ
    @Override
    public void showProgressbarDialog(String title) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(title);
        progressDialog.setMessage(getResources().getString(R.string.requesting_to_server));
        progressDialog.show();
    }

    //đóng dialog progress bar
    @Override
    public void dimissProgressbarDialog() {
        progressDialog.dismiss();
    }


    //hàm đăng nhập thành công
    @Override
    public void loginSuccess(UserModel userModel) {
        Common.USER = userModel;
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Intent iHome = new Intent(this, MainActivity.class);
        startActivity(iHome);
    }

    //hàm đăng nhập thất bại
    @Override
    public void loginFail() {
        Toast.makeText(this, "dang nhap that bai", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_img_avatar:
                checkpermissionReadEX();
                break;
            case R.id.register_txt_change_avatar:
                checkpermissionReadEX();
                break;
            case R.id.register_action_register:
                showProgressbarDialog(Common.REGISTER);
                prescenterLogicRegister.checkFormRegister(register_name.getText().toString(),
                        register_edit_passowrd.getText().toString(),
                        register_edit_repassword.getText().toString(), uri_image);
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus){
            switch (v.getId()){
                case R.id.register_edit_username:
                    prescenterLogicRegister.checkUserName(register_edit_username.getText().toString());
                    break;
                case R.id.register_edit_passowrd:
                    prescenterLogicRegister.checkPassword(register_edit_passowrd.getText().toString());
                    break;
            }
        }
    }
}
