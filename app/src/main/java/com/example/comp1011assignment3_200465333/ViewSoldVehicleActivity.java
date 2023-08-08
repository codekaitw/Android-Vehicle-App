package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import com.example.comp1011assignment3_200465333.adapter.ItemAdapter;

public class ViewSoldVehicleActivity extends BaseActivity {

    ListView listView;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sold_vehicle);

        listView = findViewById(R.id.listView_SoldVehicle);
        textView = findViewById(R.id.soldPageTitle);

        // spinner
        spinner = findViewById(R.id.spinner_soldView);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sort_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        setSpinnerListener(spinner);
        spinner.setOnItemSelectedListener(this);

        readData();
        setSoldVehiclesList();
        getDataString(soldVehicles);
        convertStringToBitmapToBitmapImageList(soldVehicles);

        ItemAdapter itemAdapter = new ItemAdapter(this, vehicleList, bitmapImageList);
        listView.setAdapter(itemAdapter);

        button = findViewById(R.id.btn_GoAllVehicleView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSoldVehicleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewSoldVehicleActivity.this, VehicleDetailActivity.class);
                if(isSorted){
                    intent.putExtra("vehicle_Id", sortedVehicles.get(position).getId());
                }else{
                    intent.putExtra("vehicle_Id", soldVehicles.get(position).getId());
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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        super.onItemSelected(parent, view, position, id);
        viewPageCode = 2;
        listView = findViewById(R.id.listView_SoldVehicle);
        String spinnerItem = spinner.getSelectedItem().toString();
        ItemAdapter itemAdapter;
        if(spinnerItem.equals("Sort")) {
            itemAdapter = new ItemAdapter(this, vehicleList, bitmapImageList);
        }else {
            itemAdapter = new ItemAdapter(this, sortedVehicleList, sortedBitmapImageList);
        }
        listView.setAdapter(itemAdapter);
    }
    /*
    @Override
    void getDataString(){
        vehicleList.clear();
        String dataString  = "";
        for(Vehicle v: vehicles){
            if(v.getDateSold().isEmpty()){
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
            if(v.getDateSold().isEmpty()){
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

     */
}