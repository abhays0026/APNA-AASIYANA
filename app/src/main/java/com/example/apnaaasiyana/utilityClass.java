package com.example.apnaaasiyana;

import android.content.Context;
import android.content.Intent;

import com.example.apnaaasiyana.Activity.MainActivity;
import com.example.apnaaasiyana.Activity.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

public class utilityClass {


    /**
     * type of property
     * 1. Flat
     * 2. Home
     * 3. Independent
     * 4. Rooms
     * 5. Villa
     * ................etc.
     */
    public static int getTypeOfProperty(String categoryName){

        categoryName = categoryName.toUpperCase();
        int typeOfProperty = 1;

        switch (categoryName){
            case "FLATS" :
                typeOfProperty = 1;
                break;
            case "HOME" :
                typeOfProperty = 2;
                break;
            case "INDEPENDENT" :
                typeOfProperty = 3;
                break;
            case "ROOMS" :
                typeOfProperty = 4;
                break;
            case "VILLA" :
                typeOfProperty = 5;
                break;
        }

        return typeOfProperty;
    }


    /**
     *
     * @param type : integer to the type of the house
     * @return name of the type
     */
    public static String getHouseType(int type){

        String houseType = "RENTED";
        if(type == 1){
            houseType = "VACANT";
        }else if(type == 2){
            houseType = "RENTED";
        }

        return houseType;

    }

    public static String getPropertyNameFromIndex(long typeOfProperty){

        String name = "Flat";
        if(typeOfProperty == 1){
            name = "Flat";
        }else if(typeOfProperty == 2){
            name = "Villa";
        }else if(typeOfProperty == 3){
            name = "Room";
        }else if(typeOfProperty == 4){
            name = "Independent";
        }

        return name;

    }

    public static String getTypeOfPropertyName(long typeOfProperty){

        String nameOfProperty = "Flats";
        switch ((int)typeOfProperty){
            case 1 :
                nameOfProperty = "Flats";
                break;
            case 2 :
                nameOfProperty = "Home";
                break;
            case 3 :
                nameOfProperty = "Independent";
                break;
            case 4 :
                nameOfProperty = "Rooms";
                break;
            case 5 :
                nameOfProperty = "Villa";
                break;
        }

        return nameOfProperty;

    }

    /**
     * function to convert actual amount in numbers to another form
     * eg. 45000 --> 45k
     * or 4500000 --> 45lacs
     * or 450000000 --> 45 crores
     */
    public static String getConvertedPrice(Float housePrice){

        String price = "";

        //float housePriceInt = Float.parseFloat(housePrice);

        if(housePrice < 1000.0){
            price = housePrice + " rupees";
        }else if(housePrice < 100000){
            price = housePrice/1000 + " k";
        }else if(housePrice <10000000){
            price = housePrice/100000 + " lacs";
        }else {
            price = housePrice/10000000 + " crores";
        }

        return price;
    }



}
