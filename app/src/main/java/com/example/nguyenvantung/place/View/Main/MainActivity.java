package com.example.nguyenvantung.place.View.Main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Main.Fragment.FindFragment;
import com.example.nguyenvantung.place.View.Main.Fragment.HomeFragment;
import com.example.nguyenvantung.place.View.Main.Fragment.NotifiFragment;
import com.example.nguyenvantung.place.View.Main.Fragment.UploadFragment;
import com.example.nguyenvantung.place.View.Main.Fragment.UserFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Toolbar main_toolbar;
    private BottomNavigationView main_bottom_nav;

    //fragment
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    //khởi tạo control
    private void addControls() {
        //toolbar
        main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        //bottom navigation
        main_bottom_nav = findViewById(R.id.main_bottom_nav);
    }//end khởi tạo control


    //khởi tạo event cho các control
    private void addEvents() {
        main_bottom_nav.setOnNavigationItemSelectedListener(this);
    }//end khởi tạo event cho các control


    //sự kiện chuyển fragment
    private void nextFragment(Fragment fragment){
        mFragmentManager = getSupportFragmentManager();
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
                return true;
            case R.id.menu_bottom_nav_find:
                nextFragment(new FindFragment());
                return true;
            case R.id.menu_bottom_nav_upload:
                nextFragment(new UploadFragment());
                return true;
            case R.id.menu_bottom_nav_notifi:
                nextFragment(new NotifiFragment());
                return true;
            case R.id.menu_bottom_nav_user:
                nextFragment(new UserFragment());
                return true;
        }

        return false;
    }//end sự kiện khi click vào item của bottom navigation
}
