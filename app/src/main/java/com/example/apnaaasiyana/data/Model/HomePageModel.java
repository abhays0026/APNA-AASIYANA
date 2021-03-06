package com.example.apnaaasiyana.data.Model;

import java.util.List;

public class HomePageModel {


    //Todo: add new type variable like grid, scroll, vertical etc. if new type added and update the constructors in this as well
    //Start one type of model
    //Creating a model for Horizontal sling pages
    //Todo: Add other view afterward to it

    private String title;
    private String backgroundColor;
    private List<HorizontalProductScrollModel> horizontalProductScrollModelListList;


    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public HomePageModel(String title, String color, List<HorizontalProductScrollModel> horizontalProductScrollModelListList) {
        this.title = title;
        this.backgroundColor = color;
        this.horizontalProductScrollModelListList = horizontalProductScrollModelListList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelListList() {
        return horizontalProductScrollModelListList;
    }

    public void setHorizontalProductScrollModelListList(List<HorizontalProductScrollModel> horizontalProductScrollModelListList) {
        this.horizontalProductScrollModelListList = horizontalProductScrollModelListList;
    }
//End one type of model
    //if new to be added , start adding below it

}
