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

    public Company(String name, String address, String street, String city, String province, String postalCode, int numberOfSold, double totalProfit) {
        this.name = name;
        this.address = address;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.numberOfSold = numberOfSold;
        this.totalProfit = totalProfit;
    }
}
