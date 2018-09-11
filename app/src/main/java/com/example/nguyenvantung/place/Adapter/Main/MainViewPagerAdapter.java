package com.example.nguyenvantung.place.Adapter.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nguyenvantung.place.View.Find.FindFragment;
import com.example.nguyenvantung.place.View.Home.HomeFragment;
import com.example.nguyenvantung.place.View.Notifi.NotifiFragment;
import com.example.nguyenvantung.place.View.Upload.UploadFragment;
import com.example.nguyenvantung.place.View.User.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment = new ArrayList<>();
        listFragment.add(new HomeFragment());
        listFragment.add(new FindFragment());
        listFragment.add(new UploadFragment());
        listFragment.add(new NotifiFragment());
        listFragment.add(new UserFragment());
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
