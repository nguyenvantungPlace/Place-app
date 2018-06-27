package com.example.nguyenvantung.place.Adapter.Newfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.AvatarUserCommentModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.ViewHolder.Newfeed.AvatarUserCommentViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AvatarUserCommentAdapter extends RecyclerView.Adapter<AvatarUserCommentViewHolder> {
    private Context context;
    private List<AvatarUserCommentModel> listAvatar;

    public AvatarUserCommentAdapter(Context context, List<AvatarUserCommentModel> listAvatar) {
        this.context = context;
        this.listAvatar = listAvatar;
    }

    @NonNull
    @Override
    public AvatarUserCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AvatarUserCommentViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_people_coment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarUserCommentViewHolder holder, int position) {
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + listAvatar.get(position).getAvartar())
                .resize(56,56)
                .into(holder.item_people_img_avatar);
    }

    @Override
    public int getItemCount() {
        return listAvatar.size();
    }
}
