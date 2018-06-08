package com.example.nguyenvantung.place.View.Login;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nguyenvantung.place.Adapter.Login.LoginViewpagerAdapter;
import com.example.nguyenvantung.place.R;

public class LoginActivity extends AppCompatActivity {
    private TabLayout login_tablayout;
    private ViewPager login_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControls();
        initViewpager();
        addEvents();
    }

    private void addControls() {
        login_tablayout = findViewById(R.id.login_tablayout);
        login_viewpager = findViewById(R.id.login_viewpager);
    }

    private void initViewpager(){
        LoginViewpagerAdapter loginViewpagerAdapter = new LoginViewpagerAdapter(getSupportFragmentManager());
        login_viewpager.setAdapter(loginViewpagerAdapter);
        login_tablayout.setupWithViewPager(login_viewpager);
    }

    private void addEvents() {

    }
}
