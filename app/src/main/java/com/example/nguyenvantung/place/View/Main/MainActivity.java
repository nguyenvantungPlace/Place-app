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
    private FragmentManager fm;
    private FragmentTransaction mFragmentTransaction;

    final Fragment homefm = HomeFragment.newInstance();
    final Fragment findfm = FindFragment.newInstance();
    final Fragment uploadfm = UploadFragment.newInstance();
    final Fragment notifm = NotifiFragment.newInstance();
    final Fragment userfm = UserFragment.newInstance();

    Fragment active = homefm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mFragmentManager = getSupportFragmentManager();

        addControls();
        addEvents();
    }

    //khởi tạo control
    private void addControls() {
        //bottom navigation
        main_bottom_nav = findViewById(R.id.main_bottom_nav);
        main_bottom_nav.setSelectedItemId(R.id.menu_bottom_nav_home);
//        nextFragment(new HomeFragment());


        setFragment();
    }//end khởi tạo control

    private void setFragment() {
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.main_fragment, findfm, "FIND").hide(findfm).commit();
        fm.beginTransaction().add(R.id.main_fragment, uploadfm, "UPLOAD").hide(uploadfm).commit();
        fm.beginTransaction().add(R.id.main_fragment, notifm, "NOTIFI").hide(notifm).commit();
        fm.beginTransaction().add(R.id.main_fragment, userfm, "USER").hide(userfm).commit();
        fm.beginTransaction().add(R.id.main_fragment, homefm, "HOME").commit();
    }


    //khởi tạo event cho các control
    private void addEvents() {
        main_bottom_nav.setOnNavigationItemSelectedListener(this);
//        main_viewpager.addOnPageChangeListener(this);
    }//end khởi tạo event cho các control


    // sự kiện khi click vào item của bottom navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id_item = item.getItemId();
        switch (id_item){
            case R.id.menu_bottom_nav_home:
                if (!(active instanceof HomeFragment)) fm.beginTransaction().hide(active).show(homefm).commit();
                active = homefm;
                return  true;
            case R.id.menu_bottom_nav_find:
                if (!(active instanceof FindFragment)) fm.beginTransaction().hide(active).show(findfm).commit();
                active = findfm;
                return true;
            case R.id.menu_bottom_nav_upload:
                if (!(active instanceof UploadFragment)) fm.beginTransaction().hide(active).show(uploadfm).commit();
                active = uploadfm;
                return true;
            case R.id.menu_bottom_nav_notifi:
                if (!(active instanceof NotifiFragment)) fm.beginTransaction().hide(active).show(notifm).commit();
                active = notifm;
                return true;
            case R.id.menu_bottom_nav_user:
                if (!(active instanceof UserFragment)) fm.beginTransaction().hide(active).show(userfm).commit();
                active = userfm;
                return true;
        }
        return false;
    }//end sự kiện khi click vào item của bottom navigation

}
