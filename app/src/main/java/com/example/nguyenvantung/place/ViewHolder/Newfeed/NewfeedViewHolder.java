package com.example.nguyenvantung.place.ViewHolder.Newfeed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Home.Fragment.ViewNewfeedFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewfeedViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private ViewNewfeedFragment viewNewfeedFragment;
    public CircleImageView item_newfeed_img_avatar_user;
    public TextView item_newfeed_txt_username, item_newfeed_txt_description;
    public ImageView item_newfeed_img_post;

    public NewfeedViewHolder(View itemView, ViewNewfeedFragment viewNewfeedFragment) {
        super(itemView);
        this.view = itemView;

        addControls();
        addEvents();
    }

    private void addControls() {
        item_newfeed_img_avatar_user = view.findViewById(R.id.item_newfeed_img_avatar_user);
        item_newfeed_txt_username    = view.findViewById(R.id.item_newfeed_txt_username);
        item_newfeed_txt_description = view.findViewById(R.id.item_newfeed_txt_description);
        item_newfeed_img_post        = view.findViewById(R.id.item_newfeed_img_post);
    }

    private void addEvents() {

    }
}
