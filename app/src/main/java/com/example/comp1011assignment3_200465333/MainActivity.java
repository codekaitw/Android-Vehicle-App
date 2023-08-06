package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.comp1011assignment3_200465333.data.InitialData;
import com.example.comp1011assignment3_200465333.model.Vehicle;

import java.io.*;


public class MainActivity extends BaseActivity {

    public Vehicle v;
    TextView textView;
    ListView listView;
    ArrayAdapter<String> adapter;
    Button btn_go_add_view;
    Button btn_go_availableVehicle_view;
    Button btn_go_soldVehicle_view;
    static String fileName = "vehicle.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView_AllVehicle);
        textView = findViewById(R.id.textView);

        File file = new File(getFilesDir(), fileName);
        if(!file.exists()){
            initData();
            writeData();
        }
        readData();
        getDataString();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                makeToast(name);
            }
        });

        adapter = new ArrayAdapter<>(this, R.layout.list_item, vehicleList);
        listView.setAdapter(adapter);


        // add button click event
        btn_go_add_view = findViewById(R.id.btn_GoAllVehicleView);
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
    }


    void initData(){
        InitialData baseDataObj = new InitialData();
        vehicles = baseDataObj.getVehicles();
    }

    /*
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
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

     */



}