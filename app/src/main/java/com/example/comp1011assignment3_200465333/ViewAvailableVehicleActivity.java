package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import com.example.comp1011assignment3_200465333.adapter.ItemAdapter;

public class ViewAvailableVehicleActivity extends BaseActivity {

    ListView listView;
    TextView textView;
    Button button;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_available_vehicle);

        listView = findViewById(R.id.listView_AvailableVehicle);
        textView = findViewById(R.id.availablePageTitle);

        // spinner
        spinner = findViewById(R.id.spinner_availableView);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sort_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        setSpinnerListener(spinner);
        spinner.setOnItemSelectedListener(this);

        readData();
        setAvailableVehiclesList();
        getDataString(availableVehicles);
        convertStringToBitmapToBitmapImageList(availableVehicles);

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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewAvailableVehicleActivity.this, VehicleDetailActivity.class);
                if(isSorted){
                    intent.putExtra("vehicle_Id", sortedVehicles.get(position).getId());
                }else{
                    intent.putExtra("vehicle_Id", availableVehicles.get(position).getId());
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
        viewPageCode = 1;
        listView = findViewById(R.id.listView_AvailableVehicle);
        String spinnerItem = spinner.getSelectedItem().toString();
        ItemAdapter itemAdapter;
        if(spinnerItem.equals("Sort")) {
            itemAdapter = new ItemAdapter(this, vehicleList, bitmapImageList);
        }else {
            itemAdapter = new ItemAdapter(this, sortedVehicleList, sortedBitmapImageList);
        }
        listView.setAdapter(itemAdapter);
    }
}