package com.capstoneprodukbangkit.batik.model;

public class DynamicRvModel {

    String name;
    String detail;
    private int image;

    public DynamicRvModel(String name, String detail, int image) {
        this.name = name;
        this.detail = detail;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public String getDetail() {
        return detail;
    }
    public int getImage() {
        return image;
    }
}
