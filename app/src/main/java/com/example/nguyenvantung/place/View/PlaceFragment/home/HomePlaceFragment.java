package com.example.nguyenvantung.place.View.PlaceFragment.home;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Map.MapsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.nguyenvantung.place.R.id.home_place_map;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class HomePlaceFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    private View view;
    private RecyclerView home_place_rv;
    private GoogleMap gm;
    private LinearLayout nextpage_map;
    private android.app.Fragment home_place_map;
    private CardView home_place_cardview;

    private PlaceModel placeModel;

    @SuppressLint("ValidFragment")
    public HomePlaceFragment(PlaceModel placeModel) {
        // Required empty public constructor
        this.placeModel = placeModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_place, container, false);
        addControls();
        addEvents();
        createMap();
        return view;
    }

    private void createMap() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.home_place_map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gm = googleMap;
        LatLng place = new LatLng(Double.parseDouble(placeModel.getLatitude()), Double.parseDouble(placeModel.getLongitude()));
        gm.addMarker(new MarkerOptions().position(place).title(placeModel.getTenDiaDiem()));
        gm.moveCamera(CameraUpdateFactory.newLatLng(place));
        gm.moveCamera(CameraUpdateFactory.zoomTo(10));
    }

    private void addControls() {
        home_place_rv = view.findViewById(R.id.home_place_rv);
        ViewCompat.setNestedScrollingEnabled(home_place_rv, false);
        nextpage_map  = view.findViewById(R.id.nextpage_map);
        home_place_cardview = view.findViewById(R.id.home_place_cardview);
    }

    private void addEvents() {
        nextpage_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nextpage_map:
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra(Common.INTENT_PLACE_MODEL, placeModel);
                startActivity(intent);
                break;
        }
    }
}
