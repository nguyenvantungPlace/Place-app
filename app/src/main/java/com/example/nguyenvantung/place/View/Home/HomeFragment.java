package com.example.nguyenvantung.place.View.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nguyenvantung.place.Adapter.Home.HomeViewPagerAdapter;
import com.example.nguyenvantung.place.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private View view;
    private Toolbar mToolbar;
    private ViewPager home_viewpager;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        addControls();
        addEvents();
        return view;
    }

    public static Fragment newInstance(){
        return new HomeFragment();
    }

    private void addControls() {
        // init viewpager
        home_viewpager = view.findViewById(R.id.home_viewpager);
        home_viewpager.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
        home_viewpager.setCurrentItem(1);
    }

    private void addEvents() {

    }

}
