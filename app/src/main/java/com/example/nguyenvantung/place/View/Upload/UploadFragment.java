package com.example.nguyenvantung.place.View.Upload;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nguyenvantung.place.Adapter.Upload.UploadRecyclerviewAdapter;
import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectClass.LoadMore;
import com.example.nguyenvantung.place.R;
import com.example.nguyenvantung.place.View.AddBody.AddBodyUploadActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment implements ViewUploadFragment, View.OnClickListener {
    private View view;
    private RecyclerView upload_recyclerview;
    private ImageView upload_opencamera;

    private List<String> listImage;
    private int COUNT_ITEM = 30;
    private int ITEM = 0;

    private UploadRecyclerviewAdapter uploadRecyclerviewAdapter;
    private GridLayoutManager gridLayoutManager;

    private Uri URI_IMAGE = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private ContentResolver contentResolver;
    private Cursor cursor;


    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_upload, container, false);


        contentResolver = getActivity().getContentResolver();
        cursor = contentResolver.query(URI_IMAGE, null, null, null, null);
        cursor.moveToFirst();

        addControls();
        permissionRequest();
        addEvents();

        return view;
    }

    private void addControls() {
        upload_recyclerview = view.findViewById(R.id.upload_recyclerview);
        initRecyclerview();

        upload_opencamera   = view.findViewById(R.id.upload_opencamera);
    }

    private void initRecyclerview() {
        listImage = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        upload_recyclerview.setHasFixedSize(true);
        upload_recyclerview.setLayoutManager(gridLayoutManager);
        uploadRecyclerviewAdapter = new UploadRecyclerviewAdapter(getContext(), listImage, this);
        upload_recyclerview.setAdapter(uploadRecyclerviewAdapter);
    }

    private void permissionRequest() {
        if (Build.VERSION.SDK_INT >= 23){
            if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        Common.REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE);
            }else {
                addData();
            }
        }else {
            addData();
        }
    }

    private void addData() {
        //&& ITEM < COUNT_ITEM
        while (!cursor.isAfterLast() && ITEM < COUNT_ITEM){
                String duongDanHinhAnh = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                listImage.add(duongDanHinhAnh);
                cursor.moveToNext();
                ITEM++;
        }
    }


    private void addEvents() {
        upload_recyclerview.addOnScrollListener(new LoadMore(gridLayoutManager, this));
        upload_opencamera.setOnClickListener(this);
    }

    @Override
    public void addDataLoadMore() {
        ITEM = 0;
        addData();
    }

    @Override
    public void imageClick(int possition) {
//        Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
        Intent iAddBody = new Intent(getActivity(), AddBodyUploadActivity.class);
        iAddBody.putExtra(Common.IMAGE_UPLOAD_URI, listImage.get(possition));
        startActivity(iAddBody);
    }

    private void openCamera() {
        if (getContext().getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            if (Build.VERSION.SDK_INT >= 23){
                if (getContext().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(iCamera, Common.REQUEST_CODE_TAKE_IMAGE);
                }else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}
                    , Common.REQUEST_CODE_PERMISSION_CAMERA);
                }
            }
        }else {
            Toast.makeText(getContext(), "Camera không được hỗ trợ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Common.REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            addData();
        if (requestCode == Common.REQUEST_CODE_PERMISSION_CAMERA && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            openCamera();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.REQUEST_CODE_TAKE_IMAGE && data != null){
            Intent intent = new Intent(getActivity(), AddBodyUploadActivity.class);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            intent.putExtra(Common.IMAGE_UPLOAD_BITMAP, bitmap);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.upload_opencamera:
                openCamera();
                break;
        }
    }
}
