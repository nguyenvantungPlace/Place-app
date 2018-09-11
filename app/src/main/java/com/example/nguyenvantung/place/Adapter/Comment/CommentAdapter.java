package com.example.nguyenvantung.place.Adapter.Comment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.Model.ObjectModel.CommentModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Comment.ViewCommentActivity;
import com.example.nguyenvantung.place.ViewHolder.Comment.CommentViewHolder;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private List<CommentModel> listComment;
    private ViewCommentActivity viewCommentActivity;

    public CommentAdapter(List<CommentModel> listComment, ViewCommentActivity viewCommentActivity){
        this.listComment = listComment;
        this.viewCommentActivity = viewCommentActivity;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view, viewCommentActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.addDataFromAdapter(listComment.get(position));
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }
}
