package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.comp1011assignment3_200465333.model.Vehicle;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddVehicleActivity extends AppCompatActivity {

    ArrayList<Object> validInput = new ArrayList<>();
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    Vehicle v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        final Button btn_add = findViewById(R.id.btn_addSubmit);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkProcessInput()){
                    //Toast.makeText(AddActivity.this, "Vehicle added", Toast.LENGTH_SHORT).show();
                    makeToast("Vehicle added");
                    Intent intent = new Intent(AddVehicleActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean checkProcessInput(){
        final TextInputLayout textInputLayoutMake = findViewById(R.id.textInputLayout_make);
        final TextInputLayout textInputLayoutModel = findViewById(R.id.textInputLayout_model);
        final TextInputLayout textInputLayoutEngine = findViewById(R.id.textInputLayout_engine);
        final TextInputLayout textInputLayoutDoor = findViewById(R.id.textInputLayout_door);
        final TextInputLayout textInputLayoutColor = findViewById(R.id.textInputLayout_color);
        final TextInputLayout textInputLayoutYear = findViewById(R.id.textInputLayout_year);
        final TextInputLayout textInputLayoutPrice = findViewById(R.id.textInputLayout_price);
        final TextInputLayout textInputLayoutCondition = findViewById(R.id.textInputLayout_condition);
        final TextInputLayout textInputLayoutDateSold = findViewById(R.id.textInputLayout_dateSold);

        String make = textInputLayoutMake.getEditText().getText().toString();
        String model = textInputLayoutModel.getEditText().getText().toString();
        String engine = textInputLayoutEngine.getEditText().getText().toString();
        String door = textInputLayoutDoor.getEditText().getText().toString();
        String color = textInputLayoutColor.getEditText().getText().toString();
        String year = textInputLayoutYear.getEditText().getText().toString();
        String price = textInputLayoutPrice.getEditText().getText().toString();
        String condition = textInputLayoutCondition.getEditText().getText().toString();
        String dateSold = textInputLayoutDateSold.getEditText().getText().toString();

        if(make.isEmpty()) {
            textInputLayoutMake.setError("Make is required");
            return false;
        }
        textInputLayoutMake.setError(null);
        if(model.isEmpty()) {
            textInputLayoutModel.setError("Model is required");
            return false;
        }
        textInputLayoutModel.setError(null);
        if(engine.isEmpty()) {
            textInputLayoutEngine.setError("Engine is required");
            return false;
        }
        textInputLayoutEngine.setError(null);
        if(door.isEmpty()) {
            textInputLayoutDoor.setError("Door is required");
            return false;
        }
        try{
            Integer.parseInt(door);
        }catch (NumberFormatException e){
            textInputLayoutDoor.setError("Door must be a number");
            return false;
        }
        textInputLayoutDoor.setError(null);
        if(color.isEmpty()) {
            textInputLayoutColor.setError("Color is required");
            return false;
        }
        textInputLayoutColor.setError(null);
        if(year.isEmpty()) {
            textInputLayoutYear.setError("Year is required");
            return false;
        }
        try{
            Integer.parseInt(year);
        }catch (NumberFormatException e){
            textInputLayoutYear.setError("Year must be a number");
            return false;
        }
        textInputLayoutYear.setError(null);
        if(price.isEmpty()) {
            textInputLayoutPrice.setError("Price is required");
            return false;
        }
        try{
            Double.parseDouble(price);
        }catch (NumberFormatException e){
            textInputLayoutPrice.setError("Price must be a number");
            return false;
        }
        textInputLayoutPrice.setError(null);
        if(condition.isEmpty()) {
            textInputLayoutCondition.setError("Condition is required");
            return false;
        }
        textInputLayoutCondition.setError(null);
        validInput.add(make);
        validInput.add(model);
        validInput.add(engine);
        validInput.add(Integer.parseInt(door));
        validInput.add(color);
        validInput.add(Double.parseDouble(year));
        validInput.add(Double.parseDouble(price));
        validInput.add(condition);
        validInput.add(dateSold);

        v = new Vehicle(make, model, condition, engine, Integer.parseInt(door), Integer.parseInt(year), Double.parseDouble(price), color, "s", "s", dateSold);
        processData();
        return true;
    }

    private void readData(){
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

    private void writeData(){
        Gson gson = new GsonBuilder().create();
        String fileName = MainActivity.fileName;
        String filePath = getFilesDir().getAbsolutePath() + "/" + fileName;
        try {
            //FileOutputStream fos = openFileOutput(filePath, MODE_PRIVATE);
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(gson.toJson(vehicles).getBytes());
            fos.flush();
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void processData(){
        readData();
        vehicles.add(v);
        writeData();
    }

    // test Toast
    Toast t;

    void makeToast(String msg){
        if(t != null){
            t.cancel();
        }
        t = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        t.show();
    }
}