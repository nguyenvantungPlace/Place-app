package com.example.nguyenvantung.place.ViewHolder.Upload;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Upload.ViewUploadFragment;

public class UploadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private View view;
    public ImageView item_upload_image;
    private ViewUploadFragment viewUploadFragment;

    public UploadViewHolder(View itemView, ViewUploadFragment viewUploadFragment) {
        super(itemView);
        this.view = itemView;
        this.viewUploadFragment = viewUploadFragment;

        addControls();
        addEvents();
    }

    private void addControls() {
        item_upload_image  = view.findViewById(R.id.item_upload_image);
    }

    private void addEvents() {
        item_upload_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_upload_image:
                viewUploadFragment.imageClick(getAdapterPosition());
                break;
        }
    }
}
