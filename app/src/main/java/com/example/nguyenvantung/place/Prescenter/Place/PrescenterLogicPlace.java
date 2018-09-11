package com.example.nguyenvantung.place.Prescenter.Place;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.example.nguyenvantung.place.View.Place.ViewPlaceActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PrescenterLogicPlace implements PrescenterIMPPlace {
    private ViewPlaceActivity viewPlaceActivity;
    private Context context;

    public PrescenterLogicPlace(ViewPlaceActivity viewPlaceActivity, Context context) {
        this.viewPlaceActivity = viewPlaceActivity;
    }

    public PrescenterLogicPlace(Context context){
        this.context = context;
    }

    @Override
    public String getAddressFromLocation(Double latitude, Double longitude) {
        String addressName = "";
        List<Address> address;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            address = geocoder.getFromLocation(latitude,longitude, 1);

            addressName = address.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressName;
    }
}
