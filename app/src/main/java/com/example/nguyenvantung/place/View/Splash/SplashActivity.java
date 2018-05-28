package com.example.nguyenvantung.place.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.nguyenvantung.place.R;

public class SplashActivity extends AppCompatActivity {
    //control
    private ImageView splash_img_logo;

    //animation
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        addControls();
        addAnimation();
    }


    private void addControls() {
        splash_img_logo = findViewById(R.id.splash_img_logo);
    }

    private void addAnimation() {
        animation = AnimationUtils.loadAnimation(this, R.anim.ani_splash_img_logo);
        splash_img_logo.startAnimation(animation);
    }
}
