package com.example.comp1011assignment3_200465333;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.example.comp1011assignment3_200465333.model.Vehicle;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class VehicleDetailActivity extends BaseActivity {

    private ImageView imageView;
    private ListView listView;
    private Button btnModify;
    private Button btnHome;

    private Vehicle v;
    private ArrayList<String> vehicleProperties = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        imageView = findViewById(R.id.imageView_detail);
        listView = findViewById(R.id.listView_detail);
        btnHome = findViewById(R.id.btn_Home_detailPage);
        btnModify = findViewById(R.id.btn_Modify_detailPage);

        String imagePath = null;

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int vehicle_Id = bundle.getInt("vehicle_Id");
            readData();
            Vehicle v = vehicles.stream().filter(vehicle -> vehicle.getId() == vehicle_Id).findFirst().orElse(null);
            if(v == null){
                makeToast("Vehicle not found");
                Intent intent = new Intent(VehicleDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }else {
                vehicleProperties.add("Id: " + v.getId());
                vehicleProperties.add("Make: " + v.getMake());
                vehicleProperties.add("Model: " + v.getModel());
                vehicleProperties.add("Engine Cylinders: " + v.getEngineCylinders());
                vehicleProperties.add("Number of Doors: " + v.getNumberOfDoors());
                vehicleProperties.add("Color: " + v.getColor());
                vehicleProperties.add("Condition: " + v.getCondition());
                vehicleProperties.add("Year: " + v.getYear());
                vehicleProperties.add("Price: " + v.getPrice());
                if(!v.getDateSold().isEmpty()){
                    vehicleProperties.add("Date Sold: " + v.getDateSold());
                }

                imagePath = v.getImagePath();

                // store vehicle object to this local variable
                this.v = v;
            }
        }
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehicleProperties);
        listView.setAdapter(adapter);


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VehicleDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSold()){
                    makeToast("Vehicle is sold, Can not modify");
                } else {
                    Intent intent = new Intent(VehicleDetailActivity.this, ModifyVehicleActivity.class);
                    intent.putExtra("vehicle_Id", v.getId());
                    startActivity(intent);
                }
            }
        });
    }

    boolean isSold(){
        return !v.getDateSold().isEmpty();
    }
}