package com.example.nguyenvantung.place.Model.ObjectModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImagePlaceModel {
    @SerializedName("id_anh_dia_diem")
    @Expose
    private String idAnhDiaDiem;
    @SerializedName("id_dia_diem")
    @Expose
    private String idDiaDiem;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("anh")
    @Expose
    private String anh;
    @SerializedName("ngay_dang")
    @Expose
    private String ngayDang;

    public String getIdAnhDiaDiem() {
        return idAnhDiaDiem;
    }

    public void setIdAnhDiaDiem(String idAnhDiaDiem) {
        this.idAnhDiaDiem = idAnhDiaDiem;
    }

    public String getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(String idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }
}
