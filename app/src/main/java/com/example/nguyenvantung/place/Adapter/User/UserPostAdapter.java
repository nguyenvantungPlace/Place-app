package com.example.nguyenvantung.place.Adapter.User;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.ViewHolder.User.UserPostViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostViewHolder> {
    private List<NewfeedModel> newfeedList;

    public UserPostAdapter(List<NewfeedModel> newfeedList) {
        this.newfeedList = newfeedList;
    }

    @NonNull
    @Override
    public UserPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserPostViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserPostViewHolder holder, int position) {
        Picasso.get().load(Common.BASE_URL_USER_IMAGE_POST + newfeedList.get(position).getAnh())
                .into(holder.item_user_post_img);
    }

    @Override
    public int getItemCount() {
        return newfeedList.size();
    }
}
