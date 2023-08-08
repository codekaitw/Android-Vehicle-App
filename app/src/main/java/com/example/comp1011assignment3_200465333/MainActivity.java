package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import androidx.core.content.ContextCompat;
import com.example.comp1011assignment3_200465333.adapter.ItemAdapter;
import com.example.comp1011assignment3_200465333.data.InitialData;

import java.io.File;


public class MainActivity extends BaseActivity {
    ListView listView;
    Button btn_go_add_view;
    Button btn_go_availableVehicle_view;
    Button btn_go_soldVehicle_view;
    Button btn_go_company_detail_view;
    Spinner spinner;
    static String FILENAME = "vehicle.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView_AllVehicle);

        // spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sort_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        setSpinnerListener(spinner);
        spinner.setOnItemSelectedListener(this);



        // check if file exists
        File file = new File(getFilesDir(), FILENAME);
        if(!file.exists()){
            initData();
            writeData();
        }
        readData();
        getDataString(vehicles);
        convertStringToBitmapToBitmapImageList(vehicles);


        // actually below two line is useless, because onItemSelected() is auto called when the main activity is created
        ItemAdapter itemAdapter = new ItemAdapter(this, vehicleList, bitmapImageList);
        listView.setAdapter(itemAdapter);



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, VehicleDetailActivity.class);
                if(isSorted){
                    intent.putExtra("vehicle_Id", sortedVehicles.get(position).getId());
                }else{
                    intent.putExtra("vehicle_Id", vehicles.get(position).getId());
                }
                startActivity(intent);
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                makeToast("Long click to view details");
            }
        });
        //adapter = new ArrayAdapter<>(this, R.layout.listview_item, vehicleList);
        //listView.setAdapter(adapter);


        // add button click event
        btn_go_add_view = findViewById(R.id.btn_GoAddVehicleView);
        btn_go_add_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddVehicleActivity.class);
                startActivity(intent);
            }
        });

        btn_go_availableVehicle_view = findViewById(R.id.btn_GoAvailableVehicleView);
        btn_go_availableVehicle_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAvailableVehicleActivity.class);
                startActivity(intent);
            }
        });

        btn_go_soldVehicle_view = findViewById(R.id.btn_GoSoldVehicleView);
        btn_go_soldVehicle_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewSoldVehicleActivity.class);
                startActivity(intent);
            }
        });

        btn_go_company_detail_view = findViewById(R.id.btn_AboutUs);
        btn_go_company_detail_view.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CompanyDetailActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        super.onItemSelected(parent, view, position, id);
        listView = findViewById(R.id.listView_AllVehicle);
        String spinnerItem = spinner.getSelectedItem().toString();
        ItemAdapter itemAdapter;
        if(spinnerItem.equals("Sort")) {
            itemAdapter = new ItemAdapter(this, vehicleList, bitmapImageList);
        }else {
            itemAdapter = new ItemAdapter(this, sortedVehicleList, sortedBitmapImageList);
        }
        listView.setAdapter(itemAdapter);
    }

    void initData(){
        InitialData initData = new InitialData();
        vehicles = initData.getVehicles();
        processInitialDataImageToExternalStorage();
    }

    void processInitialDataImageToExternalStorage(){
        for(int i = 0; i < vehicles.size(); i++) {
            String imagePath = vehicles.get(i).getImagePath();
            Drawable drawable = ContextCompat.getDrawable(this, Integer.parseInt(imagePath));
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            assert bitmapDrawable != null;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            String fileImagePath  = saveImageToExternalStorage(bitmap);
            vehicles.get(i).setImagePath(fileImagePath);
        }
    }

}