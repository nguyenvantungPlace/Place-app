package com.example.nguyenvantung.place.Adapter.Login;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nguyenvantung.place.View.Login.Fragment.PlaceLoginFragment;
import com.example.nguyenvantung.place.View.Login.Fragment.UserLoginFragment;
import com.example.nguyenvantung.place.View.User.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class LoginViewpagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;
    private List<String> listTitle;

    public LoginViewpagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment = new ArrayList<>();
        listFragment.add(new UserLoginFragment());
        listFragment.add(new PlaceLoginFragment());

        listTitle    = new ArrayList<>();
        listTitle.add("Đăng nhập với người dùng");
        listTitle.add("Đăng nhập với địa điểm");
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
}
