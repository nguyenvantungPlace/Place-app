package com.example.nguyenvantung.place.View.Place;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.Prescenter.Place.PrescenterLogicPlace;
import com.example.nguyenvantung.place.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlaceActivity extends AppCompatActivity implements ViewPlaceActivity,View.OnClickListener {
    private CircleImageView place_img_avatar;
    private ImageView place_img_setting;
    private TextView place_txt_name, place_txt_address;
    private Toolbar place_toolbar;
    private RecyclerView place_recyclerview_user_liked, place_recyclerview_post;

    private PlaceModel placeModel;
    private PrescenterLogicPlace prescenterLogicPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        placeModel = (PlaceModel) getIntent().getSerializableExtra(Common.INTENT_PLACE_MODEL);
        prescenterLogicPlace = new PrescenterLogicPlace(this);

        addControls();
        addData();
        addEvents();
    }

    private void addControls() {
        place_img_avatar = findViewById(R.id.place_img_avatar);
        place_img_setting= findViewById(R.id.place_img_setting);
        place_txt_name   = findViewById(R.id.place_txt_name);
        place_toolbar    = findViewById(R.id.place_toolbar);
        place_toolbar.setTitle(placeModel.getTenDiaDiem());
        place_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        place_txt_address= findViewById(R.id.place_txt_address);
        place_recyclerview_user_liked = findViewById(R.id.place_recyclerview_user_liked);
        place_recyclerview_post = findViewById(R.id.place_recyclerview_post);

        //fix hien tuong scroll cham
        ViewCompat.setNestedScrollingEnabled(place_recyclerview_user_liked, false);
        ViewCompat.setNestedScrollingEnabled(place_recyclerview_post, false);
    }

    private void addData() {
        Picasso.get().load(Common.BASE_URL_USER_AVATAR_PLACE + placeModel.getAvatar()).into(place_img_avatar);
        place_txt_name.setText(placeModel.getTenDiaDiem());

        //get address from location
        place_txt_address.setText(prescenterLogicPlace.getAddressFromLocation(Double.parseDouble(String.valueOf(placeModel.getLatitude()))
                ,Double.parseDouble(String.valueOf(placeModel.getLongitude()))));
    }

    private void addEvents() {
        place_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        place_img_setting.setOnClickListener(this);
    }

    private void editPlace() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.place_img_setting :
                editPlace();
                break;
        }
    }
}
