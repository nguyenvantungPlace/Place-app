package com.example.nguyenvantung.place.View.Register;

import com.example.nguyenvantung.place.Model.ObjectModel.UserPlaceModel;

public interface ViewRegisterActivity {
    void userNameErrorLegth(); //chiều dài của user name phải lớn hơn 8 kí tự và không có khoảng trắng
    void userNameUsed(); //user name da duoc su dung
    void fomatPassword(); //mật khẩu có độ dại nhỏ hơn 8 và lớn hơn 20
    void requestUpImage(); //yêu cầu đặt ảnh đại diện
    void rePasswordFalse(); //yêu cầu nhập lại mật khẩu, sai với mật khẩu đã nhập ở trên
    void register(); // đăng ký
    void registerSuccess(); //đăng ký thành công
    void registerFail(); //đăng ký thất bại
    void showProgressbarDialog(String title);
    void dimissProgressbarDialog();
    void loginSuccess(UserPlaceModel userModel);
    void loginFail();
}
