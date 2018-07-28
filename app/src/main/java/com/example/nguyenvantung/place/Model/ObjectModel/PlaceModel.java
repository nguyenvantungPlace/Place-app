package com.example.nguyenvantung.place.Model.ObjectModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlaceModel implements Serializable {

    @SerializedName("id_dia_diem")
    @Expose
    private String idDiaDiem;
    @SerializedName("id_nguoi_dung")
    @Expose
    private String idNguoiDung;
    @SerializedName("ten_dia_diem")
    @Expose
    private String tenDiaDiem;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("thanh_pho")
    @Expose
    private String thanhPho;
    @SerializedName("phuong")
    @Expose
    private String phuong;
    @SerializedName("xa")
    @Expose
    private String xa;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("thoi_gian_mo_cua")
    @Expose
    private Object thoiGianMoCua;
    @SerializedName("thoi_gian_dong_cua")
    @Expose
    private Object thoiGianDongCua;
    @SerializedName("gia_cao_nhat")
    @Expose
    private Object giaCaoNhat;
    @SerializedName("gia_thap_nhat")
    @Expose
    private Object giaThapNhat;

    public String getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(String idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getPhuong() {
        return phuong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Object getThoiGianMoCua() {
        return thoiGianMoCua;
    }

    public void setThoiGianMoCua(Object thoiGianMoCua) {
        this.thoiGianMoCua = thoiGianMoCua;
    }

    public Object getThoiGianDongCua() {
        return thoiGianDongCua;
    }

    public void setThoiGianDongCua(Object thoiGianDongCua) {
        this.thoiGianDongCua = thoiGianDongCua;
    }

    public Object getGiaCaoNhat() {
        return giaCaoNhat;
    }

    public void setGiaCaoNhat(Object giaCaoNhat) {
        this.giaCaoNhat = giaCaoNhat;
    }

    public Object getGiaThapNhat() {
        return giaThapNhat;
    }

    public void setGiaThapNhat(Object giaThapNhat) {
        this.giaThapNhat = giaThapNhat;
    }
}
