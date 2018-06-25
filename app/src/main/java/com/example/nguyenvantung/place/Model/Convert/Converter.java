package com.example.nguyenvantung.place.Model.Convert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Output;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Converter {

    public Converter(){

    }

    public String ConverImageToBase64(Bitmap bitmap){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageByte = baos.toByteArray();
        String endCodeImage = Base64.encodeToString(imageByte, Base64.DEFAULT);
        Log.d("kiemtra", endCodeImage);
        return endCodeImage;
    }

}
