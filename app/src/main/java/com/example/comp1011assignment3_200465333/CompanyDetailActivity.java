package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import com.example.comp1011assignment3_200465333.model.Company;
import com.example.comp1011assignment3_200465333.model.Vehicle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class CompanyDetailActivity extends BaseActivity {

    private ImageView imageView;
    private ListView listView;
    private Button btnModify;
    private Button btnHome;

    private ArrayList<String> companyProperties = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);

        imageView = findViewById(R.id.imageView_company_detail);
        listView = findViewById(R.id.listView_company_detail);
        btnHome = findViewById(R.id.btn_Home_company_detailPage);
        btnModify = findViewById(R.id.btn_Modify_company_detailPage);

        String fileName = "company.json";
        File file = new File(getFilesDir(), fileName);
        if(!file.exists()){
            initCompanyData();
            writeCompanyData();
        }
        readData();
        readCompanyData();
        int numberOfSold = (int) vehicles.stream().filter(vehicle -> vehicle.getDateSold() != null).count();
        double totalProfit = vehicles.stream().filter(vehicle -> vehicle.getDateSold() != null).mapToDouble(Vehicle::getPrice).sum();
        if(company.getNumberOfSold() != numberOfSold){
            company.setNumberOfSold(numberOfSold);
            company.setTotalProfit(totalProfit);
            writeCompanyData();
        }
        readCompanyData();

        companyProperties.add("Name: " + company.getName());
        companyProperties.add("Address: " + company.getAddress());
        companyProperties.add("Street: " + company.getStreet());
        companyProperties.add("City: " + company.getCity());
        companyProperties.add("Province: " + company.getProvince());
        companyProperties.add("Postal Code: " + company.getPostalCode());
        companyProperties.add("Number of Sold: " + company.getNumberOfSold());
        companyProperties.add("Total Profit: " + company.getTotalProfit());

        String imagePath;
        imagePath = company.getImagePath();

        try{
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, companyProperties);
        listView.setAdapter(adapter);

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(CompanyDetailActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btnModify.setOnClickListener(v -> {
            Intent intent = new Intent(CompanyDetailActivity.this, ModifyCompanyActivity.class);
            startActivity(intent);
        });
    }


    void initCompanyData(){
        String name = "Dream U Car";
        String street = "792 Dream Drive";
        String city = "Barrie";
        String province = "ON";
        String postalCode = "32F72Z";
        int numberOfSold = 0;
        double totalProfit = 0;
        String imagePath = String.valueOf(R.drawable.used_car_logo);
        String convertedImagePath = processInitImagePathToExternalStorage(imagePath);
        String address = street + ", " + city + ", " + province;
        this.company = new Company(name, address, street, city, province, postalCode, numberOfSold, totalProfit, convertedImagePath);
    }

    String processInitImagePathToExternalStorage(String imagePath){
        Drawable drawable = ContextCompat.getDrawable(this, Integer.parseInt(imagePath));
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        assert(bitmapDrawable != null);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        return saveImageToExternalStorage(bitmap);

    }

    void writeCompanyData(){
        Gson gson = new GsonBuilder().create();
        String fileName = "company.json";
        String filePath = getFilesDir().getAbsolutePath() + "/" + fileName;
        try {
            String json = gson.toJson(company);
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(json.getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readCompanyData(){
        Gson gson = new GsonBuilder().create();
        String fileName = "company.json";
        String filePath = getFilesDir().getAbsolutePath() + "/" + fileName;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String rjson = br.readLine();
            company = gson.fromJson(rjson, Company.class);
            fis.close();
            isr.close();
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}