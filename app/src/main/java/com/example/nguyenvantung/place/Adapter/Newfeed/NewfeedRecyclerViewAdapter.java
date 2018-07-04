package com.example.nguyenvantung.place.Adapter.Newfeed;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.AvatarUserCommentModel;
import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.Retrofit.DataClient;
import com.example.nguyenvantung.place.View.Home.Fragment.ViewNewfeedFragment;
import com.example.nguyenvantung.place.ViewHolder.Newfeed.NewfeedViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewfeedRecyclerViewAdapter extends RecyclerView.Adapter<NewfeedViewHolder> {
    private List<NewfeedModel> newfeedModelList;
    private ViewNewfeedFragment viewNewfeedFragment;
    private DataClient dataClient;

    public NewfeedRecyclerViewAdapter(List<NewfeedModel> newfeedModelList, ViewNewfeedFragment viewNewfeedFragment){
        this.newfeedModelList = newfeedModelList;
        this.viewNewfeedFragment = viewNewfeedFragment;
        dataClient = APIUtils.getData();
    }

    @NonNull
    @Override
    public NewfeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewfeedViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newfeed, parent, false), viewNewfeedFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewfeedViewHolder holder, int position) {
        holder.addDataID(Integer.parseInt(Common.USER.getIdNguoiDung()),
                Integer.parseInt(newfeedModelList.get(position).getIdBaiDang()));

        holder.item_newfeed_txt_username.setText(newfeedModelList.get(position).getIdNguoiDung());
        holder.item_newfeed_txt_description.setText(newfeedModelList.get(position).getNoiDung());
        Picasso.get().load(Common.BASE_URL_USER_IMAGE_POST + newfeedModelList.get(position).getAnh())
                .into(holder.item_newfeed_img_post);
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + newfeedModelList.get(position).getAnh_nguoi_dung())
                .into(holder.item_newfeed_img_avatar_user);



    }

    @Override
    public int getItemCount() {
        return newfeedModelList.size();
    }
}
