package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.os.Bundle;
import com.example.comp1011assignment3_200465333.adapter.ItemAdapter;
import com.example.comp1011assignment3_200465333.model.Vehicle;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ModifyVehicleActivity extends BaseActivity {
    private Vehicle v;
    private Button btn_home;
    private Button btn_modify;
    private ListView listView;
    private TextInputLayout textInputLayout_price;
    private TextInputLayout textInputLayout_soldDate;
    private int vehicle_Id;
    private int vehicles_index;
    private ArrayList<String> singleDataVehicle = new ArrayList<>();
    private ArrayList<Bitmap> singleBitmapImagePath = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_vehicle);

        listView = findViewById(R.id.listView_modify_vehicle);
        btn_modify = findViewById(R.id.btn_submitChange_modify_vehicle);
        btn_home = findViewById(R.id.btn_GoHomePage_modify_vehicle);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            vehicle_Id = bundle.getInt("vehicle_Id");
            readData();
            Vehicle v = vehicles.stream().filter(vehicle -> vehicle.getId() == vehicle_Id).findFirst().orElse(null);
            if(v == null) {
                makeToast("Vehicle not found");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            this.v = v;

            getSingleDataString();
            Bitmap bitmap = BitmapFactory.decodeFile(v.getImagePath());
            singleBitmapImagePath.add(bitmap);

            ItemAdapter itemAdapter = new ItemAdapter(this, singleDataVehicle, singleBitmapImagePath);
            listView.setAdapter(itemAdapter);
        }else{
            makeToast("Vehicle not found");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


        btn_home.setOnClickListener( (View v) -> {
                Intent intent = new Intent(ModifyVehicleActivity.this, MainActivity.class);
                startActivity(intent);
        });

        btn_modify.setOnClickListener( (View v) -> {
            if(checkProcessInput()) {
                vehicles_index = vehicles.indexOf(vehicles.stream().filter(vehicle -> vehicle.getId() == vehicle_Id).findFirst().orElse(null));

                vehicles.get(vehicles_index).setPrice(Integer.parseInt(textInputLayout_price.getEditText().getText().toString()));
                vehicles.get(vehicles_index).setDateSold(textInputLayout_soldDate.getEditText().getText().toString());

                System.out.println(vehicles_index);
                writeData();
                makeToast("Vehicle data modified");
                Intent intent = new Intent(ModifyVehicleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean checkProcessInput(){
        textInputLayout_price = findViewById(R.id.textInputLayout_modify_price);
        textInputLayout_soldDate = findViewById(R.id.textInputLayout_modify_soldDate);
        String price = textInputLayout_price.getEditText().getText().toString();
        String soldDate = textInputLayout_soldDate.getEditText().getText().toString();
        if(price.isEmpty()){
            textInputLayout_price.setError("Please enter price");
            return false;
        }
        try{
            Double.parseDouble(price);
        }catch (NumberFormatException e){
            textInputLayout_price.setError("Price must be a number");
            return false;
        }
        textInputLayout_price.setError(null);
        if(soldDate.isEmpty()){
            textInputLayout_soldDate.setError("Please enter sold date");
            return false;
        }
        textInputLayout_soldDate.setError(null);
        return true;
    }

    void getSingleDataString(){
        singleDataVehicle.clear();

        String dataString  = "";

        dataString = " Make : " + v.getMake()
                + ", Model : " + v.getModel() + ",\n"
                + " Condition : " + v.getCondition()
                + ", Engine : " + v.getEngineCylinders() + ",\n"
                + " Doors : " + v.getNumberOfDoors()
                + ", year : " + v.getYear() + ",\n"
                + " Price : " + v.getPrice()
                + ", Date Sold : " + v.getDateSold();
        dataString += "\n";

        singleDataVehicle.add(dataString);
    }
}