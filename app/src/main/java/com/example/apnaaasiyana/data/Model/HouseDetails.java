package com.example.apnaaasiyana.data.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class HouseDetails implements Serializable {

    private String houseName;//
    private String datePosted;///
    private boolean isCarParkAvailable;//
    private String houseAddress;//
    private String houseCarpetArea;//
    private String housePrice;//
    private String houseSize;/// write the same as bedroom + BHK ( 2 BHK for 2 bedrooms)
    private String houseType;/// WRITE 1 , as vacant when posted
    private long noOfBedrooms;//
    private long noOfBathrooms;//
    private double averageRatings;///
    private List<Long> ratings;///
    private long totalRating;///
    private boolean isRented;///
    private String userIdOfTenant;///
    private String userIdOfHouseOwner;///
    private String rentAgreement;///

    private Map<String, String> tenantDetails;///

    public Map<String, String> getTenantDetails() {
        return tenantDetails;
    }

    public void setTenantDetails(Map<String, String> tenantDetails) {
        this.tenantDetails = tenantDetails;
    }

    public String getRentAgreement() {
        return rentAgreement;
    }

    public void setRentAgreement(String rentAgreement) {
        this.rentAgreement = rentAgreement;
    }

    public void setAverageRatings(double averageRatings) {
        this.averageRatings = averageRatings;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public String getUserIdOfTenant() {
        return userIdOfTenant;
    }

    public void setUserIdOfTenant(String userIdOfTenant) {
        this.userIdOfTenant = userIdOfTenant;
    }

    public String getUserIdOfHouseOwner() {
        return userIdOfHouseOwner;
    }

    public void setUserIdOfHouseOwner(String userIdOfHouseOwner) {
        this.userIdOfHouseOwner = userIdOfHouseOwner;
    }

    public HouseDetails(){

    }

    public HouseDetails(String houseName, String datePosted, boolean isCarParkAvailable,
                        String houseAddress, String houseCarpetArea, String housePrice,
                        String houseSize, String houseType, long noOfBedrooms,
                        long noOfBathrooms, double averageRatings, List<Long> ratings,
                        long totalRating, boolean isRented, String userIdOfTenant,
                        String userIdOfHouseOwner, String rentAgreement, Map<String, String> tenantDetails) {
        this.houseName = houseName;
        this.datePosted = datePosted;
        this.isCarParkAvailable = isCarParkAvailable;
        this.houseAddress = houseAddress;
        this.houseCarpetArea = houseCarpetArea;
        this.housePrice = housePrice;
        this.houseSize = houseSize;
        this.houseType = houseType;
        this.noOfBedrooms = noOfBedrooms;
        this.noOfBathrooms = noOfBathrooms;
        this.averageRatings = averageRatings;
        this.ratings = ratings;
        this.totalRating = totalRating;
        this.isRented = isRented;
        this.userIdOfTenant = userIdOfTenant;
        this.userIdOfHouseOwner = userIdOfHouseOwner;
        this.rentAgreement = rentAgreement;
        this.tenantDetails = tenantDetails;
    }

    public void setHouseDetails(String houseName, boolean isCarParkAvailable,
                                String datePosted, String houseAddress,
                                String houseCarpetArea, String housePrice,
                                String houseSize, String houseType, long noOfBedrooms,
                                long noOfBathrooms, double averageRatings,
                                List<Long> ratings, long totalRating,
                                boolean isRented, String userIdOfTenant,
                                String userIdOfHouseOwner,
                                String rentAgreement,
    Map<String, String> tenantDetails){

        this.houseName = houseName;
        this.isCarParkAvailable = isCarParkAvailable;
        this.datePosted = datePosted;
        this.houseAddress = houseAddress;
        this.houseCarpetArea = houseCarpetArea;
        this.housePrice = housePrice;
        this.houseSize = houseSize;
        this.houseType = houseType;
        this.noOfBedrooms = noOfBedrooms;
        this.noOfBathrooms = noOfBathrooms;
        this.averageRatings = averageRatings;
        this.ratings = ratings;
        this.totalRating = totalRating;
        this.isRented = isRented;
        this.userIdOfTenant = userIdOfTenant;
        this.userIdOfHouseOwner = userIdOfHouseOwner;
        this.rentAgreement = rentAgreement;
        this.tenantDetails = tenantDetails;


    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public boolean isCarParkAvailable() {
        return isCarParkAvailable;
    }

    public void setCarParkAvailable(boolean carParkAvailable) {
        isCarParkAvailable = carParkAvailable;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseCarpetArea() {
        return houseCarpetArea;
    }

    public void setHouseCarpetArea(String houseCarpetArea) {
        this.houseCarpetArea = houseCarpetArea;
    }

    public String getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice;
    }

    public String getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(String houseSize) {
        this.houseSize = houseSize;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public long getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(long noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public long getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public void setNoOfBathrooms(long noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public double getAverageRatings() {
        return averageRatings;
    }

    public void setAverageRatings(long averageRatings) {
        this.averageRatings = averageRatings;
    }

    public List<Long> getRatings() {
        return ratings;
    }

    public void setRatings(List<Long> ratings) {
        this.ratings = ratings;
    }

    public long getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(long totalRating) {
        this.totalRating = totalRating;
    }

    @Override
    public String toString() {
        return "HouseDetails{" +
                "houseName='" + houseName + '\'' +
                ", datePosted='" + datePosted + '\'' +
                ", isCarParkAvailable=" + isCarParkAvailable +
                ", houseAddress='" + houseAddress + '\'' +
                ", houseCarpetArea='" + houseCarpetArea + '\'' +
                ", housePrice='" + housePrice + '\'' +
                ", houseSize='" + houseSize + '\'' +
                ", houseType='" + houseType + '\'' +
                ", noOfBedrooms=" + noOfBedrooms +
                ", noOfBathrooms=" + noOfBathrooms +
                ", averageRatings=" + averageRatings +
                ", ratings=" + ratings +
                ", totalRating=" + totalRating +
                '}';
    }
}
