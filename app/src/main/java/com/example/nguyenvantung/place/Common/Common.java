package com.example.nguyenvantung.place.Common;

import android.content.SharedPreferences;

import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;

public class Common {
    //server
    public static final String HOST = "http://192.168.1.10/";
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

    //constroller
    public static final String CONTROLLER = "c";
    public static final String CONTROLLER_USER = "User";

    //action
    public static final String ACTION = "a";
    public static final String ACTION_CHECKUSERNAME = "checkUserName";
    public static final String ACTION_REGISTER_PLACE = "registerPlace";
    public static final String ACTION_LOGIN_PLACE = "loginPlace";

    //request method Post
    public static final String REQUEST_SERVER_USER_NAME = "user_name";
    public static final String REQUEST_SERVER_PASSWORD = "password";
    public static final String REQUSET_SERVER_ENCODE_IMAGE = "encoded_image";
    public static final String REQUSET_SERVER_IMAGE_NAME = "image_name";
    public static final String REQUEST_SERVER_NAME = "name";

    // info login place
    public static UserModel USER = new UserModel();

    public static SharedPreferences LOGIN_SHAREPREFERENCES;
}
