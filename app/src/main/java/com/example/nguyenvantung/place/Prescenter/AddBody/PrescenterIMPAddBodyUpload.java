package com.example.nguyenvantung.place.Prescenter.AddBody;

import java.util.Date;

public interface PrescenterIMPAddBodyUpload {
    void uploadImage(String uriImage);
    void uploadPost(String id_dia_diem, String noi_dung, String ngay_gio_dang, String image_name);
}
