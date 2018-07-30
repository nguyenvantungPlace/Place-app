package com.example.nguyenvantung.place.View.Place;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nguyenvantung.place.Adapter.Place.PlaceViewPagerAdapter;
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

public class PlaceActivity extends AppCompatActivity implements ViewPlaceActivity,View.OnClickListener, ViewPager.OnPageChangeListener {
    private CircleImageView place_img_avatar;
    private ImageView place_img_setting;
    private TextView place_txt_name, place_txt_address;
    private Toolbar place_toolbar;
    private RecyclerView place_recyclerview_user_liked, place_recyclerview_post;
    private ViewPager place_viewpager;
    private CircleImageView place_dot_first, place_dot_second, place_dot_third;

    private PlaceModel placeModel;
    private PrescenterLogicPlace prescenterLogicPlace;
    private PlaceViewPagerAdapter placeViewPagerAdapter;

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
//        place_img_setting= findViewById(R.id.place_img_setting);
        place_txt_name   = findViewById(R.id.place_txt_name);
        place_toolbar    = findViewById(R.id.place_toolbar);
        place_toolbar.setTitle(placeModel.getTenDiaDiem());
        place_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        place_txt_address= findViewById(R.id.place_txt_address);

        place_dot_first = findViewById(R.id.place_dot_first);
        place_dot_second = findViewById(R.id.place_dot_second);
        place_dot_third = findViewById(R.id.place_dot_third);
        place_viewpager = findViewById(R.id.place_viewpager);
        placeViewPagerAdapter = new PlaceViewPagerAdapter(getSupportFragmentManager());
        place_viewpager.setAdapter(placeViewPagerAdapter);

        //fix hien tuong scroll cham
//        ViewCompat.setNestedScrollingEnabled(place_recyclerview_user_liked, false);
//        ViewCompat.setNestedScrollingEnabled(place_recyclerview_post, false);
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
//        place_img_setting.setOnClickListener(this);
        place_viewpager.addOnPageChangeListener(this);
    }

    private void dotSelected(CircleImageView dot){

        if (dot == place_dot_first){
            place_dot_second.setImageResource(R.color.hint);
            place_dot_first.setImageResource(R.color.colorBlack);
        }else if (dot == place_dot_second){
            place_dot_first.setImageResource(R.color.hint);
            place_dot_third.setImageResource(R.color.hint);

            place_dot_second.setImageResource(R.color.colorBlack);
        }else {
            place_dot_second.setImageResource(R.color.hint);

            place_dot_third.setImageResource(R.color.colorBlack);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                dotSelected(place_dot_first);
                break;
            case 1:
                dotSelected(place_dot_second);
                break;
            case 2:
                dotSelected(place_dot_third);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
