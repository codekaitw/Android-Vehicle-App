package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.comp1011assignment3_200465333.adapter.ItemAdapter;
import com.example.comp1011assignment3_200465333.model.Vehicle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BaseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<String> vehicleList = new ArrayList<>();
    ArrayList<Bitmap> bitmapImageList = new ArrayList<>();

    ActivityResultLauncher<Intent> activityResultLauncher;

    Bitmap imagePath_bitmap_for_store;
    Spinner spinner;

    ArrayList<Vehicle> sortedVehicles = new ArrayList<>();
    ArrayList<Bitmap> sortedBitmapImageList = new ArrayList<>();
    ArrayList<String> sortedVehicleList = new ArrayList<>();
    public static int vehicleId = 0;


    // use to when add new imagePath
    void addImagePathToBitmapList(String imagePath) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            bitmapImageList.add(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // for initialization of vehicle imagePath, in order to convert imagePath(String) to bitmap
    void convertStringToBitmapToBitmapImageList() {
        bitmapImageList.clear();
        for(Vehicle v : vehicles){
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(v.getImagePath());
                //Bitmap bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getApplicationContext().getContentResolver(), Uri.parse(v.getImagePath())));
                bitmapImageList.add(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    void readData(){
        Gson gson = new GsonBuilder().create();
        String fileName = MainActivity.FILENAME;
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

    void writeData(){
        Gson gson = new GsonBuilder().create();
        String fileName = MainActivity.FILENAME;
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

    void getDataString(){
        vehicleList.clear();
        String dataString  = "";
        for(Vehicle v: vehicles){

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

    void loadImage(ImageView imageView, String imagePath){
        try {
            Bitmap bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getApplicationContext().getContentResolver(), Uri.parse(imagePath)));
            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void uploadImage(ImageView imageView){
        imagePath_bitmap_for_store = null;
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result != null && result.getResultCode() == RESULT_OK){
                Intent data = result.getData();
                if(data != null) {
                    Uri uri = data.getData();
                    Bitmap bitmap = null;
                    try {
                        bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getApplicationContext().getContentResolver(), uri));
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                            // save image bitmap
                            imagePath_bitmap_for_store = bitmap;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // save image to a file in external storage
    public String saveImageToExternalStorage(Bitmap bitmap){
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(filePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(dir, fileName);

        try{
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return file.getAbsolutePath();
    }

    void checkForStoragePermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if(!Environment.isExternalStorageManager()){
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(intent);

            }
        }
    }

    Toast t;

    void makeToast(String msg){
        if(t != null){
            t.cancel();
        }
        t = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        t.show();
    }

    void setSpinnerListener(Spinner passSpinner){
        this.spinner = passSpinner;
        ;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        if(!selectedItem.equals("Sort")) {
            sort(selectedItem);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    void sort(String selectedItem) {
        sortedVehicles.clear();
        switch (selectedItem){
            case "Make":
                sortedVehicles.addAll(vehicles.stream().sorted(Comparator.comparing(Vehicle::getMake)).collect(Collectors.toList()));
                break;
            case "Model":
                sortedVehicles.addAll(vehicles.stream().sorted(Comparator.comparing(Vehicle::getModel)).collect(Collectors.toList()));
                break;
            case "Condition":
                sortedVehicles.addAll(vehicles.stream().sorted(Comparator.comparing(Vehicle::getCondition)).collect(Collectors.toList()));
                break;
            case "Engine":
                sortedVehicles.addAll(vehicles.stream().sorted(Comparator.comparing(Vehicle::getEngineCylinders)).collect(Collectors.toList()));
                break;
            case "Doors":
                sortedVehicles.addAll(vehicles.stream().sorted(Comparator.comparing(Vehicle::getNumberOfDoors)).collect(Collectors.toList()));
                break;
            case "Year":
                sortedVehicles.addAll(vehicles.stream().sorted(Comparator.comparing(Vehicle::getYear)).collect(Collectors.toList()));
                break;
            case "Price":
                sortedVehicles.addAll(vehicles.stream().sorted(Comparator.comparingDouble(Vehicle::getPrice)).collect(Collectors.toList()));
                break;
            default:
                break;
        }
        setSortedVehiclesList();
        setSortedBitmapImageList();
    }

    void setSortedVehiclesList(){
        sortedVehicleList.clear();
        String dataString  = "";
        for(Vehicle v: sortedVehicles) {

            dataString = " Make : " + v.getMake()
                    + ", Model : " + v.getModel() + ",\n"
                    + " Condition : " + v.getCondition()
                    + ", Engine : " + v.getEngineCylinders() + ",\n"
                    + " Doors : " + v.getNumberOfDoors()
                    + ", year : " + v.getYear() + ",\n"
                    + " Price : " + v.getPrice()
                    + ", Date Sold : " + v.getDateSold();
            dataString += "\n";
            sortedVehicleList.add(dataString);
        }
    }

    void setSortedBitmapImageList() {
        sortedBitmapImageList.clear();
        for(Vehicle v : sortedVehicles){
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(v.getImagePath());
                //Bitmap bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getApplicationContext().getContentResolver(), Uri.parse(v.getImagePath())));
                sortedBitmapImageList.add(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}