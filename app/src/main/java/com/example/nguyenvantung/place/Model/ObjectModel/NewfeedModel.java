package com.example.nguyenvantung.place.Model.ObjectModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewfeedModel {
    @SerializedName("id_bai_dang")
    @Expose
    private String idBaiDang;
    @SerializedName("id_nguoi_dung")
    @Expose
    private String idNguoiDung;
    @SerializedName("id_dia_diem")
    @Expose
    private String idDiaDiem;
    @SerializedName("anh")
    @Expose
    private String anh;
    @SerializedName("anh_nguoi_dung")
    @Expose
    private String anh_nguoi_dung;
    @SerializedName("noi_dung")
    @Expose
    private String noiDung;
    @SerializedName("ngay_gio_dang")
    @Expose
    private String ngayGioDang;

    public String getIdBaiDang() {
        return idBaiDang;
    }

    public void setIdBaiDang(String idBaiDang) {
        this.idBaiDang = idBaiDang;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(String idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayGioDang() {
        return ngayGioDang;
    }

    public void setNgayGioDang(String ngayGioDang) {
        this.ngayGioDang = ngayGioDang;
    }

    public String getAnh_nguoi_dung() {
        return anh_nguoi_dung;
    }

    public void setAnh_nguoi_dung(String anh_nguoi_dung) {
        this.anh_nguoi_dung = anh_nguoi_dung;
    }
}
