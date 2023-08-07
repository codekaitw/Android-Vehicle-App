package com.example.comp1011assignment3_200465333;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.comp1011assignment3_200465333.model.Company;
import com.google.android.material.textfield.TextInputLayout;

public class ModifyCompanyActivity extends CompanyDetailActivity {


    private Button btn_home;
    private Button btn_upload;
    private Button btn_submitChange;
    private TextInputLayout textInputLayout_name;
    private TextInputLayout textInputLayout_street;
    private TextInputLayout textInputLayout_city;
    private TextInputLayout textInputLayout_province;
    private TextInputLayout textInputLayout_postalCode;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_company);

        btn_home = findViewById(R.id.btn_GoHomePage_modify_company);
        btn_upload = findViewById(R.id.btn_UploadImage_modifyCompanyView);
        btn_submitChange = findViewById(R.id.btn_submitChange_modify_company);
        imageView = findViewById(R.id.imageView_modifyCompanyView);

        readCompanyData();

        try{
            Bitmap bitmap = BitmapFactory.decodeFile(company.getImagePath());
            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }

        uploadImage(imageView);

        btn_submitChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkProcessInput()){
                    writeCompanyData();
                    makeToast("Company Details Updated");
                    Intent intent = new Intent(ModifyCompanyActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForStoragePermission();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

        btn_home.setOnClickListener(view -> {
            Intent intent = new Intent(ModifyCompanyActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

    boolean checkProcessInput(){
        textInputLayout_name = findViewById(R.id.textInputLayout_modify_name);
        textInputLayout_street = findViewById(R.id.textInputLayout_modify_street);
        textInputLayout_city = findViewById(R.id.textInputLayout_modify_city);
        textInputLayout_province = findViewById(R.id.textInputLayout_modify_province);
        textInputLayout_postalCode = findViewById(R.id.textInputLayout_modify_postalcode);

        String name = textInputLayout_name.getEditText().getText().toString();
        String street = textInputLayout_street.getEditText().getText().toString();
        String city = textInputLayout_city.getEditText().getText().toString();
        String province = textInputLayout_province.getEditText().getText().toString();
        String postalCode = textInputLayout_postalCode.getEditText().getText().toString();

        if(name.isEmpty()){
            textInputLayout_name.setError("Name is required");
            return false;
        }
        textInputLayout_name.setError(null);
        if(city.isEmpty()){
            textInputLayout_city.setError("City is required");
            return false;
        }
        textInputLayout_city.setError(null);
        if(province.isEmpty()){
            textInputLayout_province.setError("Province is required");
            return false;
        }
        textInputLayout_province.setError(null);
        if(postalCode.isEmpty()){
            textInputLayout_postalCode.setError("Postal Code is required");
            return false;
        }
        textInputLayout_postalCode.setError(null);
        if(street.isEmpty()){
            textInputLayout_street.setError("Street is required");
            return false;
        }
        textInputLayout_street.setError(null);
        // get bitmap imagePath
        if(imagePath_bitmap_for_store == null){
            makeToast("Please select an image and upload");
            return false;
        }

        company.setName(name);
        company.setStreet(street);
        company.setCity(city);
        company.setProvince(province);
        company.setPostalCode(postalCode);
        String address = street + ", " + city + ", " + province;
        company.setAddress(address);

        String imagePath = saveImageToExternalStorage(imagePath_bitmap_for_store);
        company.setImagePath(imagePath);

        return true;
    }
}