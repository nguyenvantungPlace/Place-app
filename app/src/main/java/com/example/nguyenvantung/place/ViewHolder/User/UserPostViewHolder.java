package com.example.nguyenvantung.place.ViewHolder.User;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.nguyenvantung.place.R;

public class UserPostViewHolder extends RecyclerView.ViewHolder {
    public ImageView item_user_post_img;

    private View view;

    public UserPostViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;

        addControls();
        addEvents();
    }

    private void addControls() {
        item_user_post_img = view.findViewById(R.id.item_user_post_img);
    }

    private void addEvents() {

    }
}
