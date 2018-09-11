package com.example.nguyenvantung.place.Adapter.User;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.ViewHolder.User.ListPlaceViewHolder;

import java.util.List;

public class AdapterListPlace extends RecyclerView.Adapter<ListPlaceViewHolder> {
    private List<PlaceModel> listPlace;
    private Activity activity;

    public AdapterListPlace(List<PlaceModel> listPlace, Activity activity){
        this.listPlace = listPlace;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ListPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new ListPlaceViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPlaceViewHolder holder, int position) {
        holder.initDataFromAdapter(listPlace.get(position));
    }

    @Override
    public int getItemCount() {
        return listPlace.size();
    }
}
