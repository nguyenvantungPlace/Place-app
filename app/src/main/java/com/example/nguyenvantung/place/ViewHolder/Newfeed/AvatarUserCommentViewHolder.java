package com.example.nguyenvantung.place.ViewHolder.Newfeed;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nguyenvantung.place.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AvatarUserCommentViewHolder extends RecyclerView.ViewHolder {
    private View view;
    public CircleImageView item_people_img_avatar;

    public AvatarUserCommentViewHolder(View itemView) {
        super(itemView);
        view = itemView;

        addControls();
        addEvents();
    }

    private void addControls() {
        item_people_img_avatar = view.findViewById(R.id.item_people_img_avatar);
    }

    private void addEvents() {

    }
}
