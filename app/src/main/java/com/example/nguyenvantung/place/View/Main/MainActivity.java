package com.example.nguyenvantung.place.View.Main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.nguyenvantung.place.Adapter.Main.MainViewPagerAdapter;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Find.FindFragment;
import com.example.nguyenvantung.place.View.Home.HomeFragment;
import com.example.nguyenvantung.place.View.Notifi.NotifiFragment;
import com.example.nguyenvantung.place.View.Upload.UploadFragment;
import com.example.nguyenvantung.place.View.User.UserFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private Toolbar main_toolbar;
    private BottomNavigationView main_bottom_nav;

    private ViewPager main_viewpager;

    //fragment
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        addControls();
        addEvents();
    }

    //khởi tạo control
    private void addControls() {
//        main_viewpager = findViewById(R.id.main_viewpager);
//        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
//        main_viewpager.setAdapter(mainViewPagerAdapter);

        //bottom navigation
        main_bottom_nav = findViewById(R.id.main_bottom_nav);
        main_bottom_nav.setSelectedItemId(R.id.menu_bottom_nav_home);
        nextFragment(new HomeFragment());
    }//end khởi tạo control


    //khởi tạo event cho các control
    private void addEvents() {
        main_bottom_nav.setOnNavigationItemSelectedListener(this);
//        main_viewpager.addOnPageChangeListener(this);
    }//end khởi tạo event cho các control


    //sự kiện chuyển fragment
    private void nextFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, fragment).commit();
        }
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.main_fragment, fragment).commit();
    }// end sự kiện chuyển fragment

    // sự kiện khi click vào item của bottom navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id_item = item.getItemId();
        switch (id_item){
            case R.id.menu_bottom_nav_home:
                nextFragment(new HomeFragment());
//                main_viewpager.setCurrentItem(0);
                return true;
            case R.id.menu_bottom_nav_find:
                nextFragment(new FindFragment());
//                main_viewpager.setCurrentItem(1);
                return true;
            case R.id.menu_bottom_nav_upload:
                nextFragment(new UploadFragment());
//                main_viewpager.setCurrentItem(2);
                return true;
            case R.id.menu_bottom_nav_notifi:
                nextFragment(new NotifiFragment());
//                main_viewpager.setCurrentItem(3);
                return true;
            case R.id.menu_bottom_nav_user:
                nextFragment(new UserFragment());
//                main_viewpager.setCurrentItem(4);
                return true;
        }
        return false;
    }//end sự kiện khi click vào item của bottom navigation

}
