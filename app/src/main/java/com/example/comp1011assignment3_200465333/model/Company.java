package com.example.comp1011assignment3_200465333.model;

public class Company {
    private String name;
    private String address;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private int numberOfSold;
    private double totalProfit;
    private String imagePath;


    public Company(String name, String address, String street, String city, String province, String postalCode, int numberOfSold, double totalProfit, String imagePath) {
        this.name = name;
        this.address = address;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.numberOfSold = numberOfSold;
        this.totalProfit = totalProfit;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", numberOfSold=" + numberOfSold +
                ", totalProfit=" + totalProfit +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getNumberOfSold() {
        return numberOfSold;
    }

    public void setNumberOfSold(int numberOfSold) {
        this.numberOfSold = numberOfSold;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }
}
