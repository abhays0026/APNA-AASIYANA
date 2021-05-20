package com.example.apnaaasiyana.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private String rentAgreement = null;
    private Map<String, String> tenantDetails;
    private String userIdOfHouseOwner;
    private String userIfOfTenant;
    private boolean isRented;
    private boolean isVacant;
    private int index;
    private String houseType;
    private int ratingsTotal;


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

        houseName = houseNameEditText.getText().toString();
        housePrice = housePriceEditText.getText().toString();
        //if (noOfBathroomsEditText.getText().toString() != null)

        String bathrooms = noOfBathroomsEditText.getText().toString();
        if (bathrooms != null && !bathrooms.equals("")) {
            noOfBathrooms = Long.parseLong(bathrooms);
        }

        String bedrooms = noOfBedroomsEditText.getText().toString();
        if (bedrooms != null && !bedrooms.equals("")) {
            noOfBedrooms = Long.parseLong(bedrooms);
        }

        houseAddress = houseAddressEditText.getText().toString();
        houseCarpetArea = houseCarpetAreaEditText.getText().toString();
        houseSize = noOfBedrooms + " BHK";
        houseType = "2";
        isRented = false;
        isVacant = true;
        ratingsTotal = 0;
        tenantDetails = null;
        userIdOfHouseOwner = FirebaseAuth.getInstance().getUid();
        userIfOfTenant = null;

        final HouseDetails houseDetails = new HouseDetails();
        houseDetails.setHouseDetails(houseName, isCarParkAvailable, date, houseAddress, houseCarpetArea,
                housePrice, houseSize, houseType, noOfBedrooms, noOfBathrooms, ratingAverage, ratings,
                ratingsTotal, isRented, userIfOfTenant, userIdOfHouseOwner, rentAgreement, tenantDetails);

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
                moveToNextActivity(houseDetails);

            }
        });
    }

    private void moveToNextActivity(final HouseDetails houseDetails) {
        Intent intent = new Intent(PostPropertyActivity.this, UploadHousePicturesActivity.class);
        intent.putExtra("houseDetails", houseDetails);
        intent.putExtra("typeOfProperty", typeOfProperty);
        startActivity(intent);
    }

    private void getTodaysDate() {
        Calendar calendar;
        SimpleDateFormat dateFormat;

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        date = dateFormat.format(calendar.getTime());

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String property = parent.getItemAtPosition(position).toString().toLowerCase();

        if (property.toLowerCase().equals("flats")) {
            typeOfProperty = "Flats";
        } else if (property.toLowerCase().equals("villa")) {
            typeOfProperty = "Villa";
        } else if (property.toLowerCase().equals("rooms")) {
            typeOfProperty = "Rooms";
        } else if (property.toLowerCase().equals("independent house")) {
            typeOfProperty = "Independent";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


