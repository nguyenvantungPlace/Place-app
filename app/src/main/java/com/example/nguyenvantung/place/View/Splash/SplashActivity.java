package com.example.nguyenvantung.place.View.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.View.Login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    //control
    private ImageView splash_img_logo;

    //animation
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Common.DATA_CLIENT = APIUtils.getData();
        addControls();
        checkLogin();
        addAnimation();
    }


    private void addControls() {
        splash_img_logo = findViewById(R.id.splash_img_logo);
    }

    private void checkLogin() {
        Common.LOGIN_SHAREPREFERENCES = getSharedPreferences("LOGIN", MODE_PRIVATE);
//        Common.USER.set = Common.LOGIN_SHAREPREFERENCES.getString("TOKENFACEBOOK", "");
        Common.USER.setTenDangNhap(Common.LOGIN_SHAREPREFERENCES.getString("USER_NAME_PLACE", ""));
        Common.USER.setMatKhau(Common.LOGIN_SHAREPREFERENCES.getString("PASSWORD_PLACE", ""));

        if (Common.USER.getTenDangNhap().equals("")){
            nextActivity(LoginActivity.class);
//        }else if (!Common.USER_NAME_PLACE.equals("")){
            //login place
//        }else if (!Common.TOKEN_FACEBOOK.equals("")){
            //login token facebook
        }
    }

    private void addAnimation() {
        animation = AnimationUtils.loadAnimation(this, R.anim.ani_splash_img_logo);
        splash_img_logo.startAnimation(animation);
    }

    private void nextActivity(final Class aClass){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e){}
                finally {
                    Intent iMain = new Intent(SplashActivity.this, aClass);
                    startActivity(iMain);
                    finish();
                }
            }
        });
        thread.start();
    }
}
