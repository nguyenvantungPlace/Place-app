package com.example.nguyenvantung.place.View.AddBody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class AddBodyUploadActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar addbody_toolbar;
    private ImageView addbody_image;

    private Intent intentData;
    private String uriImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_body_upload);

        intentData = getIntent();
        uriImage = intentData.getStringExtra(Common.IMAGEUPLOAD);

        addControls();
        addEvents();
    }

    private void addControls() {
        addbody_toolbar = findViewById(R.id.addbody_toolbar);
        addbody_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        addbody_image = findViewById(R.id.addbody_image);
        Picasso.get().load(new File(uriImage)).into(addbody_image);
    }

    private void addEvents() {
        addbody_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
