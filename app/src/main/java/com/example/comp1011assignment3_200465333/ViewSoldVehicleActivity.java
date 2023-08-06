package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.comp1011assignment3_200465333.model.Vehicle;

public class ViewSoldVehicleActivity extends BaseActivity {

    ListView listView;
    TextView textView;
    Button button;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sold_vehicle);

        listView = findViewById(R.id.listView_SoldVehicle);
        textView = findViewById(R.id.soldPageTitle);

        readData();
        getDataString();

        adapter = new ArrayAdapter<>(this, R.layout.list_item, vehicleList);
        listView.setAdapter(adapter);

        button = findViewById(R.id.btn_GoAllVehicleView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSoldVehicleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    void getDataString(){
        vehicleList.clear();
        String dataString  = "";
        for(Vehicle v: vehicles){
            if(v.getDateSold().isEmpty()){
                continue;
            }
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
}