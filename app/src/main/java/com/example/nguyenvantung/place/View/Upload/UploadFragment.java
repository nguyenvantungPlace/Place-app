package com.example.nguyenvantung.place.View.Upload;


import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nguyenvantung.place.Adapter.Upload.UploadRecyclerviewAdapter;
import com.example.nguyenvantung.place.Common.Common;
import com.example.nguyenvantung.place.Model.ObjectClass.LoadMore;
import com.example.nguyenvantung.place.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment implements ViewUploadFragment {
    private View view;
    private RecyclerView upload_recyclerview;

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
    }

    @Override
    public void addDataLoadMore() {
        ITEM = 0;
        addData();
    }

    @Override
    public void imageClick(int possition) {
        Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Common.REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            addData();
    }
}
