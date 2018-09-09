package com.example.nguyenvantung.place.Adapter.Upload;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Upload.ViewUploadFragment;
import com.example.nguyenvantung.place.ViewHolder.Upload.UploadViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.util.List;

public class UploadRecyclerviewAdapter extends RecyclerView.Adapter<UploadViewHolder> {
    private Context context;
    private List<String> listImage;
    private ViewUploadFragment viewUploadFragment;

    public UploadRecyclerviewAdapter(Context context, List<String> listImage, ViewUploadFragment viewUploadFragment){
        this.context = context;
        this.listImage = listImage;
        this.viewUploadFragment = viewUploadFragment;
    }

    @NonNull
    @Override
    public UploadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UploadViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_upload, parent,
                        false), viewUploadFragment);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final UploadViewHolder holder, int position) {
        Picasso.get().load(new File(listImage.get(position)))
                .centerCrop().fit()
                .into(holder.item_upload_image);
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }
}
