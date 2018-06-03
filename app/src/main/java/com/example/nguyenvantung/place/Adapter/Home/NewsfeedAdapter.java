package com.example.nguyenvantung.place.Adapter.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.Model.ObjectModel.NewfeedModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.ViewHolder.Home.NewfeedViewholder;

import java.util.List;

public class NewsfeedAdapter extends RecyclerView.Adapter<NewfeedViewholder> {
    private List<NewfeedModel> newfeedModels;
    private Context context;

    public NewsfeedAdapter(List<NewfeedModel> newfeedModels, Context context){
        this.newfeedModels = newfeedModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NewfeedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newfeed, parent, false);
        return new NewfeedViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewfeedViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
