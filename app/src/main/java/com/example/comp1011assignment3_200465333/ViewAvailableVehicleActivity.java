package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import com.example.comp1011assignment3_200465333.adapter.ItemAdapter;
import com.example.comp1011assignment3_200465333.model.Vehicle;

public class ViewAvailableVehicleActivity extends BaseActivity {

    ListView listView;
    TextView textView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_available_vehicle);

        listView = findViewById(R.id.listView_AvailableVehicle);
        textView = findViewById(R.id.availablePageTitle);

        readData();
        getDataString();
        convertStringToBitmapToBitmapImageList();

        ItemAdapter itemAdapter = new ItemAdapter(this, vehicleList, bitmapImageList);
        listView.setAdapter(itemAdapter);

        button = findViewById(R.id.btn_GoAllVehicleView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAvailableVehicleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    void getDataString(){
        vehicleList.clear();
        String dataString  = "";
        for(Vehicle v: vehicles){
            if(!v.getDateSold().isEmpty()){
                continue;
            }
            dataString = " Make : " + v.getMake()
                    + ", Model : " + v.getModel() + ",\n"
                    + " Condition : " + v.getCondition()
                    + ", Engine : " + v.getEngineCylinders() + ",\n"
                    + " Doors : " + v.getNumberOfDoors()
                    + ", year : " + v.getYear() + ",\n"
                    + " Price : " + v.getPrice()
                    + ", Date Sold : " + v.getDateSold();
            dataString += "\n";
            vehicleList.add(dataString);
        }
    }

    @Override
    void convertStringToBitmapToBitmapImageList() {
        for(Vehicle v : vehicles){
            if(!v.getDateSold().isEmpty()){
                continue;
            }
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(v.getImagePath());
                //Bitmap bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getApplicationContext().getContentResolver(), Uri.parse(v.getImagePath())));
                bitmapImageList.add(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}