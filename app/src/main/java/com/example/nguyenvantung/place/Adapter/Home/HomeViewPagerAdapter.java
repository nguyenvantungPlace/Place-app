package com.example.nguyenvantung.place.Adapter.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nguyenvantung.place.View.Home.Fragment.CameraFragment;
import com.example.nguyenvantung.place.View.Home.Fragment.ChatFragment;
import com.example.nguyenvantung.place.View.Home.Fragment.NewsFeedFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(new CameraFragment());
        fragmentList.add(new NewsFeedFragment());
        fragmentList.add(new ChatFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
