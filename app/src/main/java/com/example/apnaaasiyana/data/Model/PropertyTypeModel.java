package com.example.apnaaasiyana.data.Model;

import java.util.List;

public class PropertyTypeModel {

    private String typeOfPropertyName;
    private long index;
    private String imageUrl;
    private String houseAddress;
    private String houseName;

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public PropertyTypeModel() {
    }

    public PropertyTypeModel(String typeOfPropertyName, long index, String imageUrl, String houseAddress,
                             String houseName) {
        this.typeOfPropertyName = typeOfPropertyName;
        this.index = index;
        this.imageUrl = imageUrl;
        this.houseAddress = houseAddress;
        this.houseName = houseName;
    }

    public String getTypeOfPropertyName() {
        return typeOfPropertyName;
    }

    public void setTypeOfPropertyName(String typeOfPropertyName) {
        this.typeOfPropertyName = typeOfPropertyName;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }
}
