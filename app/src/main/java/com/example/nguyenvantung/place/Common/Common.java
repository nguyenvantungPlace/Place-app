package com.example.nguyenvantung.place.Common;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Parcelable;

import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;
import com.example.nguyenvantung.place.Retrofit.DataClient;

import java.util.Date;

public class Common {
    //Retrofit
    public static DataClient DATA_CLIENT = null;

    //server
//    public static final String HOST = "127.0.0.1/"; //host nox
    public static final String HOST = "http://192.168.1.14/";
    public static final String BASE_URL = HOST + "place/";
    public static final String BASE_URL_USER_AVATAR_PLACE = BASE_URL + "public/image/avatar/place/";
    public static final String BASE_URL_USER_IMAGE_POST = BASE_URL + "public/image/post/";
    public static final String BASE_API_PHP = "api.php";
    public static final String API_UPLOAD_IMAGE = "api.php?c=upload&a=image";

    //
    public static final String LOGIN = "Đăng nhập";
    public static final String REGISTER = "Đăng ký";

    //name Intent
    public static final String NEWFEEDMODEL_INTENT = "POSTNEWFEED";
    public static final String INTENT_NOIDUNG_NEWFEED = "NOIDUNG";
    public static final String INTENT_ID_DIA_CHI_NEWFEED = "IDDIACHI";
    public static final String INTENT_ANH_NEWFEED = "ANHNEWFEED";
    public static final String INTENT_ID_POST = "ID_POST";
    //---------------------------place
    public static final String INTENT_PLACE_MODEL = "PLACE_MODEL";
    public static final String INTENT_DATA_PAGE_MAP = "PAGEMAP";
    public static final String INTENT_PAGE_MAP_HOME = "HOME";
    public static final String INTENT_PAGE_MAP_CHECKIN = "CHECKIN";
    public static final String INTENT_PAGE_MAP_MENU = "MENU";

    //request code
    public static final int REQUEST_CODE_SELECT_IMAGE = 0;
    public static final int REQUEST_CODE_PERMISSION_LOCATION = 1;
    public static final int REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE = 2;
    public static final int REQUEST_CODE_TAKE_IMAGE = 3;
    public static final int REQUEST_CODE_PERMISSION_CAMERA = 4;

    //controller
    public static final String CONTROLLER = "c";
    public static final String CONTROLLER_USER = "User";
    public static final String CONTROLLER_POST = "post";
    public static final String CONTROLLER_UPLOAD = "Upload";
    public static final String CONTROLLER_LIKE = "Like";
    public static final String CONTROLLER_COMMENT = "comment";
    public static final String CONTROLLER_LIKE_COMMENT = "likeComment";
    public static final String CONTROLLER_PLACE = "Place";

    //action
    public static final String ACTION = "a";
    public static final String ACTION_CHECKUSERNAME = "checkUserName";
    public static final String ACTION_REGISTER_PLACE = "registerPlace";
    public static final String ACTION_LOGIN_PLACE = "loginPlace";
    public static final String ACTION_UPLOAD = "upload";
    public static final String ACTION_GET_POST = "getpost";
    public static final String ACTION_UPLOAD_IMAGE = "image";
    public static final String ACTION_GET_AVATAR_USER_COMMENT = "getAvatarUserComment";
    public static final String ACTION_CHECK_LIKED = "checkLike";
    public static final String ACTION_INSERT_LIKE = "insertLike";
    public static final String ACTION_UN_LIKE = "unLike";
    public static final String ACTION_COUNT_LIKE = "countLike";
    public static final String ACTION_GET_POST_FROM_ID_USER = "getPostFromIDUser";
    public static final String ACTION_EDIT_POST = "editPost";
    public static final String ACTION_GET_INFO_USER_FROM_ID = "getInfoUserFromID";
    public static final String ACTION_GET_ALL_COMMENT_FROM_ID_POST = "getAllCommentFromIDPost";
    public static final String ACTION_INSERT_COMMENT = "insertComment";
    public static final String ACTION_EDIT_COMMENT = "editComment";
    public static final String ACTION_DELETE_COMMENT = "deleteComment";
    public static final String ACTION_LIKE_COMMENT = "likeComment";
    public static final String ACTION_CHECKLINE_COMMENT = "checkLike";
    public static final String ACTION_UNLIKE_COMMENT = "unLikeComment";
    public static final String ACTION_CHECK_PLACE_FROM_IDUSER = "checkPlaceInIDUser";
    public static final String ACTION_GET_PLACE_FROM_IDUSER = "getAllPlace";
    public static final String ACTION_GET_PLACE_FROM_IDUSER_CHECKIN = "getPlaceFromIDUserCheckIn";
    public static final String ACTION_GET_POST_FROM_IDPLACE = "getPostFromIDPlace";
    public static final String ACTION_GET_PLACE_FROM_ID = "getPlaceFromID";

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
    public static final String REQUEST_SERVER_LIMIT = "limit";
    public static final String REQUEST_SERVER_ID_POST = "id_post";
    public static final String REQUEST_SERVER_TIME_COMMENT = "thoi_gian_binh_luan";
    public static final String REQUEST_SERVER_ID_COMMENT = "id_comment";
    public static final String REQUEST_SERVER_ID_IMAGE_PLACE = "id_anh_dia_diem";

    //SharePrefer
    public static final String IMAGE_UPLOAD_URI = "image_upload_uri";
    public static final String IMAGE_UPLOAD_BITMAP = "image_upload_bitmap";

    // info login place
    public static UserModel USER = new UserModel();
    public static Location LOCATION_DEVICE = null;

    public static SharedPreferences LOGIN_SHAREPREFERENCES;
    public static NewfeedModel NEWFEEDEDIT;
}
