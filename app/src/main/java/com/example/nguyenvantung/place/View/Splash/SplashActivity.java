package com.example.nguyenvantung.place.View.Splash;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.Retrofit.APIUtils;
import com.example.nguyenvantung.place.View.Login.LoginActivity;
import com.example.nguyenvantung.place.View.Main.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SplashActivity extends AppCompatActivity {
    //control
    private ImageView splash_img_logo;

    //animation
    private Animation animation;

    private Location location;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Common.DATA_CLIENT = APIUtils.getData();
        addControls();
        checkLogin();
        checkPermissionLocation();
        addAnimation();
    }

    private void checkPermissionLocation() {
        if (Build.VERSION.SDK_INT >= 23){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        Common.REQUEST_CODE_PERMISSION_LOCATION);
            else getLocation();
        }else getLocation();
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
//            nextActivity(LoginActivity.class);
//        }else if (!Common.USER_NAME_PLACE.equals("")){
            //login place
//        }else if (!Common.TOKEN_FACEBOOK.equals("")){
            //login token facebook
        }
    }


    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task != null){
                    location = task.getResult();
                    Common.LOCATION_DEVICE = location;
                    Log.d("LOCATION: ", "LOCATION: " + location.getLatitude() + "--" + location.getLongitude());
                    nextActivity(LoginActivity.class);
                }
            }
        });
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Common.REQUEST_CODE_PERMISSION_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults.length > 0) getLocation();
    }
}
