package com.example.nguyenvantung.place.Prescenter.EditPost;

public interface PrescenterIMPEditPost {
    void uploadImage(String uriImage);
    void uploadPost(int id_post, int id_dia_diem, String noi_dung, String image_name);
}
