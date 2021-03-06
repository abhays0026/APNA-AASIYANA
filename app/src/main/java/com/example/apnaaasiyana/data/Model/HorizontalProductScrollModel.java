package com.example.apnaaasiyana.data.Model; /**
 * type of property
 * 1. Flat
 * 2. Home
 * 3. Independent
 * 4. Rooms
 * 5. Villa
 * ................etc.
 */

public class HorizontalProductScrollModel {


    /**
     * gives the position
     * helps identify the property through the index
     */
    private long index;


    private long typeOfProperty;

    /**
     * types for now are of two types
     * 1. rented
     * 2. vacant
     * <p>
     * Can add other types afterwards
     */
    private long type;
    //This will have 1 house image link, to be placed in app using glide
    private String houseImageLink;
    private String houseName;
    private String address;

    //Contains image of type, ie. vacant or rented , a small mipmap image
    private int typeImage;

    /**
     * since , if vacant
     * else if rented , then rental date of the month to be given
     */
    private String date;


    public HorizontalProductScrollModel(long index, long typeOfProperty, long type,
                                        String houseImageLink, String houseName, String address, int typeImage, String date) {
        this.index = index;
        this.typeOfProperty = typeOfProperty;
        this.type = type;
        this.houseImageLink = houseImageLink;
        this.houseName = houseName;
        this.address = address;
        this.typeImage = typeImage;
        this.date = date;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getTypeOfProperty() {
        return typeOfProperty;
    }

    public void setTypeOfProperty(long typeOfProperty) {
        this.typeOfProperty = typeOfProperty;
    }


    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getHouseImageLink() {
        return houseImageLink;
    }

    public void setHouseImageLink(String houseImageLink) {
        this.houseImageLink = houseImageLink;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(int typeImage) {
        this.typeImage = typeImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
