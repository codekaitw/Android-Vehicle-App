package com.example.comp1011assignment3_200465333;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.comp1011assignment3_200465333.model.Vehicle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<String> vehicleList = new ArrayList<>();
    void readData(){
        Gson gson = new GsonBuilder().create();
        String fileName = MainActivity.fileName;
        String filePath = getFilesDir().getAbsolutePath() + "/" + fileName;
        try {
            //FileInputStream fis = openFileInput(fileName);
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String rjson = br.readLine();
            Type listType = new TypeToken<ArrayList<Vehicle>>(){}.getType();
            vehicles = gson.fromJson(rjson, listType);
            fis.close();
            isr.close();
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void writeData(){
        Gson gson = new GsonBuilder().create();
        String fileName = MainActivity.fileName;
        String filePath = getFilesDir().getAbsolutePath() + "/" + fileName;
        try {
            //FileOutputStream fos = openFileOutput(filePath, MODE_PRIVATE);
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(gson.toJson(vehicles).getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void getDataString(){
        vehicleList.clear();
        String dataString  = "";
        for(Vehicle v: vehicles){

            dataString = "Make : " + v.getMake()
                    + ", Model : " + v.getModel() + ",\n"
                    + " Condition : " + v.getCondition()
                    + ", Engine : " + v.getEngineCylinders() + ",\n"
                    + " Doors : " + v.getNumberOfDoors()
                    + ", Price : " + v.getPrice();
            dataString += "\n";
            vehicleList.add(dataString);
        }
    }
    Toast t;

    void makeToast(String msg){
        if(t != null){
            t.cancel();
        }
        t = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        t.show();
    }
}