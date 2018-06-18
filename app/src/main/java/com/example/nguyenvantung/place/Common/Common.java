package com.example.nguyenvantung.place.Common;

import android.content.SharedPreferences;

import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;

import java.util.Date;

public class Common {
    //server
    public static final String HOST = "http://192.168.1.56/";
    public static final String BASE_URL = HOST + "place/";
    public static final String BASE_URL_USER_AVATAR_PLACE = BASE_URL + "public/image/avatar/place/";
    public static final String BASE_API_PHP = "api.php";

    //
    public static final String LOGIN = "Đăng nhập";
    public static final String REGISTER = "Đăng ký";

    //request code
    public static final int REQUEST_CODE_SELECT_IMAGE = 0;
    public static final int REQUEST_CODE_PERMISSION_LOCATION = 1;
    public static final int REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE = 2;
    public static final int REQUEST_CODE_TAKE_IMAGE = 3;
    public static final int REQUEST_CODE_PERMISSION_CAMERA = 4;

    //constroller
    public static final String CONTROLLER = "c";
    public static final String CONTROLLER_USER = "User";
    public static final String CONTROLLER_POST = "post";

    //action
    public static final String ACTION = "a";
    public static final String ACTION_CHECKUSERNAME = "checkUserName";
    public static final String ACTION_REGISTER_PLACE = "registerPlace";
    public static final String ACTION_LOGIN_PLACE = "loginPlace";
    public static final String ACTION_UPLOAD = "upload";

    //request method Post
    public static final String REQUEST_SERVER_USER_NAME = "user_name";
    public static final String REQUEST_SERVER_PASSWORD = "password";
    public static final String REQUEST_SERVER_ENCODE_IMAGE = "encoded_image";
    public static final String REQUEST_SERVER_IMAGE_NAME = "image_name";
    public static final String REQUEST_SERVER_NAME = "name";

    public static final String REQUEST_SERVER_ID_USER = "id_nguoi_dung";
    public static final String REQUEST_SERVER_ID_PLACE = "id_dia_diem";
    public static final String REQUEST_SERVER_IMAGE = "anh";
    public static final String REQUEST_SERVER_DESCRIPTION = "noi_dung";
    public static final String REQUEST_SERVER_DATE = "ngay_gio_dang";

    //SharePrefer
    public static final String IMAGE_UPLOAD_URI = "image_upload_uri";
    public static final String IMAGE_UPLOAD_BITMAP = "image_upload_bitmap";

    // info login place
    public static UserModel USER = new UserModel();

    public static SharedPreferences LOGIN_SHAREPREFERENCES;
}
