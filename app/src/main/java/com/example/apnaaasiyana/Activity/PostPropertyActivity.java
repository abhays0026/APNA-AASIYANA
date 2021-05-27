package com.example.apnaaasiyana.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.HouseDetails;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostPropertyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //TODO : next from this page will move towards uploading pictures

//TODO : get the date here from java and then post in the required format

    private Spinner typeOfPropertiesSpinner;//

    private RadioGroup radioCarParkGroup;
    private RadioButton radioCarParkButton;
    private EditText houseNameEditText;
    private EditText housePriceEditText;
    private EditText noOfBathroomsEditText;
    private EditText noOfBedroomsEditText;
    private EditText houseAddressEditText;
    private EditText houseCarpetAreaEditText;
    private Button nextButtonPropertyDetails;


    private boolean isCarParkAvailable = true;//
    private String typeOfProperty;//
    private String houseName;//
    private String housePrice;//
    private String houseSize;
    private String houseCarpetArea;//
    private String houseAddress;//
    private String date;//
    private List<String> houseImages;
    private String houseImage;
    private long noOfBathrooms;//
    private long noOfBedrooms;//
    private double ratingAverage = 0;
    private List<Long> ratings = Arrays.asList(new Long[5]);
    private String rentAgreement = "";
    private Map<String, String> tenantDetails;
    private String userIdOfHouseOwner;
    private String userIfOfTenant = "";
    private boolean isRented;
    private boolean isVacant;
    private int index;
    private int houseType;
    private int ratingsTotal;

    private HouseDetails houseDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property);

        houseNameEditText = findViewById(R.id.house_name_text_view_post_property);
        housePriceEditText = findViewById(R.id.house_price_post_property);
        noOfBathroomsEditText = findViewById(R.id.no_of_bathrooms_post_property);
        noOfBedroomsEditText = findViewById(R.id.no_of_bedroom_post_property);
        houseAddressEditText = findViewById(R.id.house_address_post_property);
        houseCarpetAreaEditText = findViewById(R.id.carpet_area_post_property);
        nextButtonPropertyDetails = findViewById(R.id.property_details_next_btn);

        houseNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                houseName = houseNameEditText.getText().toString();
                if(houseName == null)houseName = "";

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        housePriceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                housePrice = housePriceEditText.getText().toString();
                if(housePrice == null){
                    housePrice = "";
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //if (noOfBathroomsEditText.getText().toString() != null)

        noOfBathroomsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String bathrooms = noOfBathroomsEditText.getText().toString();
                if (bathrooms != null && !bathrooms.equals("")) {
                    noOfBathrooms = Long.parseLong(bathrooms);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        noOfBedroomsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String bedrooms = noOfBedroomsEditText.getText().toString();
                if (bedrooms != null && !bedrooms.equals("")) {
                    noOfBedrooms = Long.parseLong(bedrooms);
                    houseSize = noOfBedrooms + " BHK";

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        houseAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                houseAddress = houseAddressEditText.getText().toString();
                if(houseAddress == null) houseAddress = "";

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        houseCarpetAreaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                houseCarpetArea = houseCarpetAreaEditText.getText().toString();
                if(houseCarpetArea == null) houseCarpetArea = "";

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


       // houseType = getHouseType(typeOfProperty);
        isRented = false;
        isVacant = true;
        ratingsTotal = 0;
        //tenantDetails = null;
        userIdOfHouseOwner = FirebaseAuth.getInstance().getUid();
        userIfOfTenant = "";

         tenantDetails = new HashMap<>();
                tenantDetails.put("durationTenant", "");
                tenantDetails.put("lastRentPaidOn", "");
                tenantDetails.put("tenantName", "");
                tenantDetails.put("tenantRent", "");


        for(int i=0;i<5;++i){
            ratings.set(i, (long) 0);
        }

        houseDetails = new HouseDetails();
//        houseDetails.setHouseDetails(houseName, isCarParkAvailable, date, houseAddress, houseCarpetArea,
//                housePrice, houseSize, houseType, noOfBedrooms, noOfBathrooms, ratingAverage, ratings,
//                ratingsTotal, isRented, userIfOfTenant, userIdOfHouseOwner, rentAgreement, tenantDetails);

//        Toast.makeText(PostPropertyActivity.this, "houseDetails ; " + houseDetails.toString(), Toast.LENGTH_SHORT)
//                .show();

        ///Spinner activation
        typeOfPropertiesSpinner = findViewById(R.id.type_of_property_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_of_property_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        typeOfPropertiesSpinner.setAdapter(adapter);
        typeOfPropertiesSpinner.setOnItemSelectedListener(this);
        //spinner complete

        //radio button functionality
        radioCarParkGroup = findViewById(R.id.property_details_car_park_radio_group);
        int selectedId = radioCarParkGroup.getCheckedRadioButtonId();
        radioCarParkButton = findViewById(selectedId);

        if (radioCarParkButton.getText().toString().toLowerCase().equals("true")) {
            isCarParkAvailable = true;
        } else {
            isCarParkAvailable = false;
        }
        //radio button complete


        getTodaysDate();

        //Todo : need to pass data to the next intent as well
        nextButtonPropertyDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                houseDetails.setHouseDetails(houseName, isCarParkAvailable, date, houseAddress, houseCarpetArea,
                        housePrice, houseSize, houseType, noOfBedrooms, noOfBathrooms, ratingAverage, ratings,
                        ratingsTotal, isRented, userIfOfTenant, userIdOfHouseOwner, rentAgreement, tenantDetails);

                moveToNextActivity();

            }
        });
    }

    private void moveToNextActivity() {
        Intent intent = new Intent(PostPropertyActivity.this, UploadHousePicturesActivity.class);
        intent.putExtra("houseDetails", houseDetails);
        intent.putExtra("typeOfProperty", typeOfProperty);
        intent.putExtra("date" , date);
        startActivity(intent);
    }

    private void getTodaysDate() {
        Calendar calendar;
        SimpleDateFormat dateFormat;

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        date = dateFormat.format(calendar.getTime()).toString();

        System.out.println("Date 2: " + date.toString());

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String property = parent.getItemAtPosition(position).toString().toLowerCase();

        if (property.toLowerCase().equals("flats")) {
            typeOfProperty = "Flats";
            houseType = 1;
        } else if (property.toLowerCase().equals("villa")) {
            typeOfProperty = "Villa";
            houseType = 2;
        } else if (property.toLowerCase().equals("rooms")) {
            typeOfProperty = "Rooms";
            houseType = 3;
        } else if (property.toLowerCase().equals("independent house")) {
            typeOfProperty = "Independent";
            houseType = 4;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}


