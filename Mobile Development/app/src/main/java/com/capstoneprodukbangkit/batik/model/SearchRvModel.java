package com.capstoneprodukbangkit.batik.model;

public class SearchRvModel {

    private int batikImage;
    private String batikName;
    private String batikLocation;

    public SearchRvModel(int batikImage, String batikName, String batikLocation){
        this.batikImage = batikImage;
        this.batikName = batikName;
        this.batikLocation = batikLocation;
    }

    public int getBatikImage() {
        return batikImage;
    }

    public String getBatikName(){
        return batikName;
    }

    public String getBatikLocation(){
        return batikLocation;
    }
}
