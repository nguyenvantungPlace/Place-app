package com.example.nguyenvantung.place.Prescenter.Register;

public interface IPrescenterRegister {
    void checkUserName(String user_name);  //hàm kiểm tra user name
    void checkPassword(String password); //hàm kiểm tra mật khẩu dài hơn 8 kí tự và không có khoảng trắng
    void checkFormRegister(String password, String re_password, String uri_image); //hàm kiểm tra form đăng nhập đã thỏa mãn chưa
    void registerUser(String user_name, String password, String image_base64); //hàm đăng ký lên database
    void loginUser(String user_name, String passowrd);
}
