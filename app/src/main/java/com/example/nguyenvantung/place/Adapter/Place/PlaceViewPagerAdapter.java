package com.example.nguyenvantung.place.Adapter.Place;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nguyenvantung.place.Model.ObjectModel.PlaceModel;
import com.example.nguyenvantung.place.View.PlaceFragment.checkin.UserCheckinPlaceFragment;
import com.example.nguyenvantung.place.View.PlaceFragment.home.HomePlaceFragment;
import com.example.nguyenvantung.place.View.PlaceFragment.menu.MenuPlaceFragment;

import java.util.ArrayList;
import java.util.List;

public class PlaceViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment = new ArrayList<>();
    private PlaceModel placeModel;

    public PlaceViewPagerAdapter(FragmentManager fm, PlaceModel placeModel) {
        super(fm);
        this.placeModel = placeModel;
        listFragment.add(new HomePlaceFragment(placeModel));
        listFragment.add(new MenuPlaceFragment());
        listFragment.add(new UserCheckinPlaceFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}
