package com.example.nguyenvantung.place.ViewHolder.User;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Place.PlaceActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ListPlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private View view;
    private ImageView item_place_avatar;
    private TextView item_place_name;
    private CardView item_place_layout;
    private ImageView item_place_background, item_place_setting;

    private PlaceModel placeModel;
    private Activity activity;

    public ListPlaceViewHolder(View itemView, Activity activity) {
        super(itemView);
        this.view = itemView;
        this.activity = activity;

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
//        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + placeModel.getAvatar())
//                .into(item_place_background);
//        Blurry.with(view.getContext()).capture(view).into(item_place_background);
    }

    private void addControlls() {
        item_place_avatar = view.findViewById(R.id.item_place_avatar);
        item_place_name   = view.findViewById(R.id.item_place_name);
        item_place_layout = view.findViewById(R.id.item_place_layout);
//        item_place_background = view.findViewById(R.id.item_place_background);
        item_place_setting   = view.findViewById(R.id.item_place_setting);
    }

    private void addEvents() {
        item_place_layout.setOnClickListener(this);
        item_place_setting.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_place_layout :
                Intent intent = new Intent(view.getContext(), PlaceActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String> (item_place_avatar, "shared_image_place");
                ActivityOptions activityOptions = ActivityOptions.
                        makeSceneTransitionAnimation(activity, pairs);
                intent.putExtra(Common.INTENT_PLACE_MODEL, placeModel);
                view.getContext().startActivity(intent, activityOptions.toBundle());
                break;
            case R.id.item_place_setting :
//                Toast.makeText(view.getContext(), "sakjdkajs", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
