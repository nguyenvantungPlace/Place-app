package com.example.nguyenvantung.place.Model.Convert;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Converter {

    public Converter(){

    }

    public String ConverImageToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        String encode_imae = Base64.encodeToString(bytes, Base64.DEFAULT);
        return encode_imae;
    }

}
