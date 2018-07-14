package com.example.nguyenvantung.place.Model.ObjectModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentModel {
    @SerializedName("id_binh_luan")
    @Expose
    private String idBinhLuan;
    @SerializedName("id_nguoi_dung")
    @Expose
    private String idNguoiDung;
    @SerializedName("id_bai_dang")
    @Expose
    private String idBaiDang;
    @SerializedName("noi_dung")
    @Expose
    private String noiDung;
    @SerializedName("thoi_gian_binh_luan")
    @Expose
    private String thoiGianBinhLuan;

    public CommentModel(String idBinhLuan, String idNguoiDung, String idBaiDang, String noiDung, String thoiGianBinhLuan) {
        this.idBinhLuan = idBinhLuan;
        this.idNguoiDung = idNguoiDung;
        this.idBaiDang = idBaiDang;
        this.noiDung = noiDung;
        this.thoiGianBinhLuan = thoiGianBinhLuan;
    }

    public String getIdBinhLuan() {
        return idBinhLuan;
    }

    public void setIdBinhLuan(String idBinhLuan) {
        this.idBinhLuan = idBinhLuan;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getIdBaiDang() {
        return idBaiDang;
    }

    public void setIdBaiDang(String idBaiDang) {
        this.idBaiDang = idBaiDang;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getThoiGianBinhLuan() {
        return thoiGianBinhLuan;
    }

    public void setThoiGianBinhLuan(String thoiGianBinhLuan) {
        this.thoiGianBinhLuan = thoiGianBinhLuan;
    }
}
