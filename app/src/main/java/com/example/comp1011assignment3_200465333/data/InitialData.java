package com.example.comp1011assignment3_200465333.data;

import com.example.comp1011assignment3_200465333.BaseActivity;
import com.example.comp1011assignment3_200465333.R;
import com.example.comp1011assignment3_200465333.model.*;

import java.util.ArrayList;

public class InitialData {

    ArrayList<Vehicle> vehicles = new ArrayList<>();
    public InitialData(){

        vehicles.add(new Vehicle(1 ,"Toyota", "Camry", "Good", "V6", 4, 1990, 18000.0, "Red", String.valueOf(R.drawable.dream_trading_car_1), "2022-01-01"));

        vehicles.add(new Vehicle(2, "Honda", "Civic", "Fair", "V4", 4, 1991, 12500.0, "White",  String.valueOf(R.drawable.dream_trading_car_2), ""));

        vehicles.add(new Vehicle(3, "Tesla", "Model 3", "Great", "Electric", 4, 1925, 40000.0, "Black", String.valueOf(R.drawable.dream_trading_car_3), "04/05/2022"));

        vehicles.add(new Vehicle(4, "Jeep", "Wrangler", "Good", "V6", 2, 2020, 29000.0, "Orange", String.valueOf(R.drawable.dream_trading_car_4), ""));

        vehicles.add(new Vehicle(5, "BMW", "X5", "Excellent", "V8", 4, 2018, 56000.0, "Silver", String.valueOf(R.drawable.dream_trading_car_5), ""));

        vehicles.add(new Vehicle(6, "Nissan", "Altima", "Fair", "V4", 4, 2015, 8500.0, "Grey", String.valueOf(R.drawable.dream_trading_car_6), "06/24/2022"));

        vehicles.add(new Vehicle(7, "Dodge", "Challenger", "Great", "V8", 2, 2036, 40000.0, "Yellow", String.valueOf(R.drawable.dream_trading_car_7), ""));

        vehicles.add(new Vehicle(8, "Audi", "A4", "Good", "V4", 4, 2001, 22000.0, "Black", String.valueOf(R.drawable.dream_trading_car_8), "03/10/2022"));

        vehicles.add(new Vehicle(9, "Porsche", "911", "Excellent", "V8", 2, 2017, 80000.0, "Red", String.valueOf(R.drawable.dream_trading_car_9), ""));

        vehicles.add(new Vehicle(10, "Ferrari", "Fer7", "Excellent", "V8", 2, 2005,  80000.0, "Red", String.valueOf(R.drawable.dream_trading_car_10), ""));

        //after 10 add, set id to 10
        BaseActivity.vehicleId = 10;
    }



    public ArrayList<Vehicle> getVehicles(){
        return vehicles;
    }
}
