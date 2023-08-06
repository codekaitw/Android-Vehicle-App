package com.example.comp1011assignment3_200465333.model;

public class Vehicle {
    private String make;
    private String model;
    private String condition;
    private String engineCylinders;
    private int numberOfDoors;

    private int year;
    private double price;
    private String color;
    private String thumbnailImg;
    private String fillSizeImg;
    private String dateSold;

    public Vehicle() {
    }

    public Vehicle(String make, String model, String condition, String engineCylinders, int numberOfDoors, int year, double price, String color, String thumbnailImg, String fillSizeImg, String dateSold) {
        this.make = make;
        this.model = model;
        this.condition = condition;
        this.engineCylinders = engineCylinders;
        this.numberOfDoors = numberOfDoors;
        this.year = year;
        this.price = price;
        this.color = color;
        this.thumbnailImg = thumbnailImg;
        this.fillSizeImg = fillSizeImg;
        this.dateSold = dateSold;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getEngineCylinders() {
        return engineCylinders;
    }

    public void setEngineCylinders(String engineCylinders) {
        this.engineCylinders = engineCylinders;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getThumbnailImg() {
        return thumbnailImg;
    }

    public void setThumbnailImg(String thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }

    public String getFillSizeImg() {
        return fillSizeImg;
    }

    public void setFillSizeImg(String fillSizeImg) {
        this.fillSizeImg = fillSizeImg;
    }

    public String getDateSold() {
        return dateSold;
    }

    public void setDateSold(String dateSold) {
        this.dateSold = dateSold;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", condition='" + condition + '\'' +
                ", engineCylinders='" + engineCylinders + '\'' +
                ", numberOfDoors=" + numberOfDoors +
                ", year=" + year +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", thumbnailImg='" + thumbnailImg + '\'' +
                ", fillSizeImg='" + fillSizeImg + '\'' +
                ", dateSold='" + dateSold + '\'' +
                '}';
    }
}
