package com.example.nguyenvantung.place.View.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.Main.MainActivity;

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
        nextActivity();
    }


    private void addControls() {
        splash_img_logo = findViewById(R.id.splash_img_logo);
    }

    private void addAnimation() {
        animation = AnimationUtils.loadAnimation(this, R.anim.ani_splash_img_logo);
        splash_img_logo.startAnimation(animation);
    }

    private void nextActivity(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e){}
                finally {
                    Intent iMain = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(iMain);
                }
            }
        });
        thread.start();
    }
}
