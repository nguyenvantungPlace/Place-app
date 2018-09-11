package com.example.nguyenvantung.place.View.Comment;

import com.example.nguyenvantung.place.Model.ObjectModel.CommentModel;
import com.example.nguyenvantung.place.Model.ObjectModel.UserModel;

import java.util.List;

public interface ViewCommentActivity {
    void getCommentSuccess(List<CommentModel> listComment);
    void getInforUserFromIDSuccess(UserModel user);
    void userLikedPost();
    void userNotlied();
    void commentSuccess();
    void commentFail();
    void editCommentSuccess(int possition, CommentModel commentModel);
    void deleteComment(int possition);
}
