package com.example.nguyenvantung.place.View.Map;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    private ImageView map_img_back;
    private FloatingActionButton map_ploating_action;
    private FrameLayout map_detail;

    private GoogleMap mMap;
    private PlaceModel placeModel;
    private boolean IS_SHOW_DETAIL = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getData();
        addControlls();
        addEvents();
    }

    private void getData(){
        placeModel = (PlaceModel) getIntent().getSerializableExtra(Common.INTENT_PLACE_MODEL);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Double.parseDouble(placeModel.getLatitude()), Double.parseDouble(placeModel.getLongitude()));
        mMap.addMarker(new MarkerOptions().position(sydney).title(placeModel.getTenDiaDiem()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

    private void addControlls() {
        map_img_back = findViewById(R.id.map_img_back);
        map_ploating_action = findViewById(R.id.map_ploating_action);
        map_detail = findViewById(R.id.map_detail);
    }

    private void addEvents() {
        map_img_back.setOnClickListener(this);
        map_ploating_action.setOnClickListener(this);
    }

    private void showDetail() {
        IS_SHOW_DETAIL = true;
        map_ploating_action.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);

        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) map_detail.getLayoutParams();
        marginLayoutParams.setMargins(0, 0, 0, 0);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.ani_detail_show_in_map);
        map_detail.startAnimation(animation);
        map_detail.setLayoutParams(marginLayoutParams);
    }

    private void dismissDetail() {
        IS_SHOW_DETAIL = false;
        map_ploating_action.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) map_detail.getLayoutParams();
        marginLayoutParams.setMargins(0, 0, 0, -250);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.ani_detail_dismiss_map);
        map_detail.startAnimation(animation);
        map_detail.setLayoutParams(marginLayoutParams);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.map_img_back :
                onBackPressed();
                break;
            case R.id.map_ploating_action :
                if (!IS_SHOW_DETAIL) showDetail();
                else dismissDetail();
                break;
        }
    }
}
