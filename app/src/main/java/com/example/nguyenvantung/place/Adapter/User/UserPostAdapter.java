package com.example.nguyenvantung.place.Adapter.User;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.ViewHolder.User.UserPostViewHolder;

import java.util.List;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostViewHolder> {
    private List<PlaceModel> listPlace;

    public UserPostAdapter(List<PlaceModel> listPlace) {
        this.listPlace = listPlace;
    }

    @NonNull
    @Override
    public UserPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserPostViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_checkin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserPostViewHolder holder, int position) {
//        Picasso.get().load(Common.BASE_URL_USER_IMAGE_POST + newfeedList.get(position).getAnh())
//                .into(holder.item_user_post_img);
        holder.getDataFromAdapter(listPlace.get(position));
    }

    @Override
    public int getItemCount() {
        return listPlace.size();
    }
}
