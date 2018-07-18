package com.example.nguyenvantung.place.ViewHolder.User;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListPlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private View view;
    private CircleImageView item_place_avatar;
    private TextView item_place_name;
    private LinearLayout item_place_layout;

    private PlaceModel placeModel;

    public ListPlaceViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;

        addControlls();
        addEvents();
    }

    public void initDataFromAdapter(PlaceModel placeModel){
        this.placeModel = placeModel;

        addData();
    }

    private void addData() {
        item_place_name.setText(placeModel.getTenDiaDiem());
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + placeModel.getAvatar()).into(item_place_avatar);
    }

    private void addControlls() {
        item_place_avatar = view.findViewById(R.id.item_place_avatar);
        item_place_name   = view.findViewById(R.id.item_place_name);
        item_place_layout = view.findViewById(R.id.item_place_layout);
    }

    private void addEvents() {
        item_place_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_place_layout :

            break;
        }
    }
}
