package com.example.apnaaasiyana;

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
            case "FLAT" :
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

}
