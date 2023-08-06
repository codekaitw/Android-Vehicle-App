package com.example.comp1011assignment3_200465333.data;

import com.example.comp1011assignment3_200465333.model.*;

import java.util.ArrayList;

public class InitialData{
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    public InitialData(){
        vehicles.add(new Vehicle("Toyota", "Camry", "Good", "V6", 4, 1990, 18000.0, "Red", "img1.jpg", "img2.jpg", "2022-01-01"));

        vehicles.add(new Vehicle("Honda", "Civic", "Fair", "V4", 4, 1991, 12500.0, "White", "civic1.jpg", "civic2.jpg", ""));

        vehicles.add(new Vehicle("Tesla", "Model 3", "Great", "Electric", 4, 1925, 40000.0, "Black", "model31.jpg", "model32.jpg", "04/05/2022"));

        vehicles.add(new Vehicle("Jeep", "Wrangler", "Good", "V6", 2, 2020, 29000.0, "Orange", "wrangler1.jpg", "wrangler2.jpg", ""));

        vehicles.add(new Vehicle("BMW", "X5", "Excellent", "V8", 4, 2018, 56000.0, "Silver", "bmw1.jpg", "bmw2.jpg", ""));

        vehicles.add(new Vehicle("Nissan", "Altima", "Fair", "V4", 4, 2015, 8500.0, "Grey", "altima1.jpg", "altima2.jpg", "06/24/2022"));

        vehicles.add(new Vehicle("Dodge", "Challenger", "Great", "V8", 2, 2036, 40000.0, "Yellow", "challenger1.jpg", "challenger2.jpg", ""));

        vehicles.add(new Vehicle("Audi", "A4", "Good", "V4", 4, 2001, 22000.0, "Black", "audi1.jpg", "audi2.jpg", "03/10/2022"));

        vehicles.add(new Vehicle("Porsche", "911", "Excellent", "V8", 2, 2017, 80000.0, "Red", "porsche1.jpg", "porsche2.jpg", ""));

        vehicles.add(new Vehicle("Ferrari", "Fer7", "Excellent", "V8", 2, 2005,  80000.0, "Red", "ferrari1.jpg", "ferrari2.jpg", ""));
    }

    public ArrayList<Vehicle> getVehicles(){
        return vehicles;
    }
}
