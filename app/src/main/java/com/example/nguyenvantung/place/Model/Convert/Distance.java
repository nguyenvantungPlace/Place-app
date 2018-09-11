package com.example.nguyenvantung.place.Model.Convert;

import android.location.Location;

import com.example.nguyenvantung.place.Common.Common;

public class Distance {

    public static String getDistance(Location locationPlace){
        String distance = "";
        double x = Common.LOCATION_DEVICE.distanceTo(locationPlace);
        int dis = (int) x;

        if (dis > 1000){
            distance = (dis / 1000) + " km";
        }else {
            distance = "0," + (dis/100) + " km";
        }

        return distance;
    }
}
