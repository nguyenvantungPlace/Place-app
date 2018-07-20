package com.example.nguyenvantung.place.View.Place;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nguyenvantung.place.R;

public class PlaceActivity extends AppCompatActivity implements ViewPlaceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        addControls();
        addEvents();
    }

    private void addControls() {

    }

    private void addEvents() {

    }
}
