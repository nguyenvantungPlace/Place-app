package com.example.nguyenvantung.place.Retrofit;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.AvatarUserCommentModel;
import com.example.nguyenvantung.place.Model.ObjectModel.CheckTrueFalse;
import com.example.nguyenvantung.place.Model.ObjectModel.CommentModel;
import com.example.nguyenvantung.place.Model.ObjectModel.CountModel;
import com.example.nguyenvantung.place.Model.ObjectModel.ImagePlaceModel;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.Model.ObjectModel.UploadObject;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;

import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient {
    //lop gui va nhan cac phuong thuc gui len server

    //Upload image
    @Multipart
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> upImage(@Part(Common.CONTROLLER) RequestBody controller,
                               @Part(Common.ACTION) RequestBody action,
                               @Part MultipartBody.Part photo);

    //kiểm tra xem trên database đã có username này đăng ký chưa
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> checkUserName(@Field(Common.CONTROLLER) String controller,
                                       @Field(Common.ACTION) String action,
                                       @Field(Common.REQUEST_SERVER_USER_NAME) String user_name);

    //đăng ký
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> register(@Field(Common.CONTROLLER) String controller,
                                  @Field(Common.ACTION) String action,
                                  @Field(Common.REQUEST_SERVER_NAME) String name,
                                  @Field(Common.REQUEST_SERVER_USER_NAME) String user_name,
                                  @Field(Common.REQUEST_SERVER_PASSWORD) String password,
                                  @Field(Common.REQUEST_SERVER_IMAGE_NAME) String image_name,
                                  @Field(Common.REQUEST_SERVER_ENCODE_IMAGE) String encode_image);

    //Đăng nhập bằng tài khoảng và mật khẩu
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<UserModel> loginPlace(@Field(Common.CONTROLLER) String controller,
                               @Field(Common.ACTION) String action,
                               @Field(Common.REQUEST_SERVER_USER_NAME) String user_name,
                               @Field(Common.REQUEST_SERVER_PASSWORD) String password);


    //------------------------------------ bài đăng ------------------------------------------------
    //post bài đăng
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> UploadPost(@Field(Common.CONTROLLER) String controller,
                                    @Field(Common.ACTION) String action,
                                    @Field(Common.REQUEST_SERVER_ID_USER) int id_nguoi_dung,
                                    @Field(Common.REQUEST_SERVER_ID_PLACE) String id_dia_diem,
                                    @Field(Common.REQUEST_SERVER_DESCRIPTION) String noi_dung,
                                    @Field(Common.REQUEST_SERVER_IMAGE_NAME) String imageName,
                                    @Field(Common.REQUEST_SERVER_DATE) String ngay_gio);

    //lấy bài đăng
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<List<NewfeedModel>> getPost(@Field(Common.CONTROLLER) String controller,
                                    @Field(Common.ACTION) String action,
                                    @Field(Common.REQUEST_SERVER_LIMIT) int limit);

    //lấy avatar người dùng comment
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<List<AvatarUserCommentModel>> getAvatarUserComment(@Field(Common.CONTROLLER) String controller,
                                                      @Field(Common.ACTION) String action,
                                                      @Field(Common.REQUEST_SERVER_ID_POST) int id_post);

    //kiểm tra xem người dùng đã like bài đăng hay chưa
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> checkLike(@Field(Common.CONTROLLER) String controller,
                                   @Field(Common.ACTION) String action,
                                   @Field(Common.REQUEST_SERVER_ID_USER) int id_user,
                                   @Field(Common.REQUEST_SERVER_ID_POST) int id_post);

    //like bài đăng
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> likePost(@Field(Common.CONTROLLER) String controller,
                                  @Field(Common.ACTION) String action,
                                  @Field(Common.REQUEST_SERVER_ID_USER) int id_user,
                                  @Field(Common.REQUEST_SERVER_ID_POST) int id_post);

    //bỏ thích bài đăng
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> unLikePost(@Field(Common.CONTROLLER) String controller,
                                    @Field(Common.ACTION) String action,
                                    @Field(Common.REQUEST_SERVER_ID_USER) int id_user,
                                    @Field(Common.REQUEST_SERVER_ID_POST) int id_post);

    //get count like
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CountModel> getCountLike(@Field(Common.CONTROLLER) String controller,
                                  @Field(Common.ACTION) String action,
                                  @Field(Common.REQUEST_SERVER_ID_POST) int id_post);

    //get all post from
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<List<NewfeedModel>> getPostFromUDUser(@Field(Common.CONTROLLER) String controller,
                                         @Field(Common.ACTION) String action,
                                         @Field(Common.REQUEST_SERVER_ID_USER) int id_user,
                                         @Field(Common.REQUEST_SERVER_LIMIT) int limit);

    //edit bài đăng
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> editPostEdited (@Field(Common.CONTROLLER) String controller,
                                         @Field(Common.ACTION) String action,
                                         @Field(Common.REQUEST_SERVER_ID_POST) int id_post,
                                         @Field(Common.REQUEST_SERVER_ID_PLACE) int id_dia_diem,
                                         @Field(Common.REQUEST_SERVER_DESCRIPTION) String noi_dung,
                                         @Field(Common.REQUEST_SERVER_IMAGE_NAME) String imageName);

    // lấy id người dùng, tên người dùng, avata người dùng trên bài post
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<UserModel> getUserFromIDUser(@Field(Common.CONTROLLER) String controller,
                                      @Field(Common.ACTION) String action,
                                      @Field(Common.REQUEST_SERVER_ID_USER) int id_user);

    //lấy bài đăng từ id_nguoi_dung và id_dia_diem để show danh sách ảnh của người dùng đã checkin
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<List<NewfeedModel>> getPostFromIDUserIDPlace(@Field(Common.CONTROLLER) String controller,
                                                @Field(Common.ACTION) String action,
                                                @Field(Common.REQUEST_SERVER_ID_USER) int id_user,
                                                @Field(Common.REQUEST_SERVER_ID_PLACE) int id_place);

    //--------------------------------- bình luận --------------------------------------------------
    //lấy tất cả comment từ id bài dăng
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<List<CommentModel>> getAllCommentFromIDPost(@Field(Common.CONTROLLER) String controller,
                                               @Field(Common.ACTION) String action,
                                               @Field(Common.REQUEST_SERVER_ID_POST) int id_post);

    //binh luan bai dang
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> insertComment(@Field(Common.CONTROLLER) String controller,
                                       @Field(Common.ACTION) String action,
                                       @Field(Common.REQUEST_SERVER_ID_USER) int id_user,
                                       @Field(Common.REQUEST_SERVER_ID_POST) int id_post,
                                       @Field(Common.REQUEST_SERVER_DESCRIPTION) String noi_dung,
                                       @Field(Common.REQUEST_SERVER_TIME_COMMENT) String time);

    //sửa bình luận
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> editComment (@Field(Common.CONTROLLER) String controller,
                                      @Field(Common.ACTION) String action,
                                      @Field(Common.REQUEST_SERVER_ID_COMMENT) int id_comment,
                                      @Field(Common.REQUEST_SERVER_DESCRIPTION) String noi_dung);

    //xóa bình luận
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> deleteComment(@Field(Common.CONTROLLER) String controller,
                                       @Field(Common.ACTION) String action,
                                       @Field(Common.REQUEST_SERVER_ID_COMMENT) int id_comment);

    //-----------------------thích bình luận--------------------------------------------------------
    // Like bình luận
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> likeComment(@Field(Common.CONTROLLER) String controller,
                                     @Field(Common.ACTION) String action,
                                     @Field(Common.REQUEST_SERVER_ID_COMMENT) int id_comment,
                                     @Field(Common.REQUEST_SERVER_ID_USER) int id_user);

    //check người dùng đã thích bình luận hay chưa
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> checkLikeComment(@Field(Common.CONTROLLER) String controller,
                                          @Field(Common.ACTION) String action,
                                          @Field(Common.REQUEST_SERVER_ID_COMMENT) int id_comment,
                                          @Field(Common.REQUEST_SERVER_ID_USER) int id_user);

    //người dùng bỏ thích bình luận
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> unLikeComment(@Field(Common.CONTROLLER) String controller,
                                          @Field(Common.ACTION) String action,
                                          @Field(Common.REQUEST_SERVER_ID_COMMENT) int id_comment,
                                          @Field(Common.REQUEST_SERVER_ID_USER) int id_user);


    //------------------------------ lấy địa điểm --------------------------------------------------
    //lấy bài đăng và gán vào
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<List<PlaceModel>> getPlace(@Field(Common.CONTROLLER) String controller,
                                    @Field(Common.ACTION) String action,
                                    @Field(Common.REQUEST_SERVER_ID_USER) int id_user,
                                    @Field(Common.REQUEST_SERVER_LIMIT) int limit);

    //check xem người dùng có quản lí 1 địa điểm nào hay không
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> checkPlace(@Field(Common.CONTROLLER) String controller,
                                    @Field(Common.ACTION) String action,
                                    @Field(Common.REQUEST_SERVER_ID_USER) int id_user);

    //lấy danh sách các địa điểm ma fnguowif dùng đã checkin
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<List<PlaceModel>> getListPlaceFromIDUSerCheckin(@Field(Common.CONTROLLER) String controller,
                                                         @Field(Common.ACTION) String action,
                                                         @Field(Common.REQUEST_SERVER_ID_USER) int id_user);


    //lấy thông tin địa điểm bằng iddiadiem
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<PlaceModel> getPlaceFromID(@Field(Common.CONTROLLER) String controller,
                                    @Field(Common.ACTION) String action,
                                    @Field(Common.REQUEST_SERVER_ID_PLACE) int id_place);



    //--------------------------Image Place----------------------------------
    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<List<ImagePlaceModel>> getAllImagePlace(@Field(Common.CONTROLLER) String controller,
                                                 @Field(Common.ACTION) String action,
                                                 @Field(Common.REQUEST_SERVER_ID_PLACE) int id_place);

    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<ImagePlaceModel> getImagePlaceFromID(@Field(Common.CONTROLLER) String Controller,
                                              @Field(Common.ACTION) String action,
                                              @Field(Common.REQUEST_SERVER_ID_IMAGE_PLACE) int id_image_place);

    @FormUrlEncoded
    @POST(Common.BASE_API_PHP)
    Call<CheckTrueFalse> uploadImagePlace(@Field(Common.CONTROLLER) String controller,
                                          @Field(Common.ACTION) String action,
                                          @Field(Common.REQUEST_SERVER_ID_PLACE) int id_place,
                                          @Field(Common.REQUEST_SERVER_DESCRIPTION) String des,
                                          @Field(Common.REQUEST_SERVER_IMAGE_NAME) String imageName,
                                          @Field(Common.REQUEST_SERVER_DATE) String date_upload);
}
