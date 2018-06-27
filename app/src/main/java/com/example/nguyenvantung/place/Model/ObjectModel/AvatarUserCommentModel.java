package com.example.nguyenvantung.place.Model.ObjectModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvatarUserCommentModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("avartar")
    @Expose
    private String avartar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }
}
