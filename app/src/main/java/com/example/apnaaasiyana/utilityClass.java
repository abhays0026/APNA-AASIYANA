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



}
