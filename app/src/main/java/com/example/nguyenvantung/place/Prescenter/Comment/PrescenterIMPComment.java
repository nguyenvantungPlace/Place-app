package com.example.nguyenvantung.place.Prescenter.Comment;

public interface PrescenterIMPComment {
    void getComment(int id_post);
    void getInforUserFromID(int id_user);
    void checkLikePost(int id_user, int id_post);
    void insertComment(int id_user, int id_post, String noi_dung, String dateTime);
}
