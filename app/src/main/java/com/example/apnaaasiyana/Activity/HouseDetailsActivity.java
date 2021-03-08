package com.example.apnaaasiyana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apnaaasiyana.Adapters.HouseImagesAdapter;
import com.example.apnaaasiyana.R;
import com.google.android.gms.maps.MapView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.api.Distribution;

import java.util.ArrayList;
import java.util.List;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.houseImagesList;
import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.loadPropertyData;
import static com.example.apnaaasiyana.utilityClass.getTypeOfPropertyName;

public class HouseDetailsActivity extends AppCompatActivity {

    private ViewPager houseImagesViewPager;
    private TabLayout viewPagerIndicator;
    private FloatingActionButton addToWishlistButton;
    private LinearLayout viewVisibleNotRented;
    private LinearLayout viewVisibleWhenRented;
    private TextView houseSize;
    private TextView housePrice;
    private TextView datePosted;
    private TextView houseLocation;
    private ImageButton buyerImage;
    private TextView houseType;
    private TextView buyerName;
    private TextView durationPassed;
    private TextView monthlyRentRate;
    private TextView lastRentPaidRate;
    private LinearLayout viewAgreement;
    private TextView houseName;
    private TextView houseLocationLocationView;
    private MapView houseLocationMap;
    private TextView houseSizeDescription;
    private TextView houseBedroomsDescription;
    private TextView houseBathroomsDescription;
    private TextView houseCarParkAvailable;

    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private boolean carParkAvailable = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        houseCarParkAvailable = findViewById(R.id.house_car_park_available_description_text_view);
        houseBedroomsDescription = findViewById(R.id.house_bedrooms_description_text_view);
        houseBathroomsDescription = findViewById(R.id.house_bathrooms_description_text_view);
        houseSizeDescription = findViewById(R.id.house_size_description_text_view);
        houseName = findViewById(R.id.house_name_text_view);
        houseLocationLocationView = findViewById(R.id.house_location_location_layout_text_view);
        houseLocationMap = findViewById(R.id.house_location_map_view);
        viewAgreement = findViewById(R.id.view_agreement_linear_layout);
        monthlyRentRate = findViewById(R.id.monthly_rent_rate_text_view);
        lastRentPaidRate = findViewById(R.id.last_rent_paid_date_text_view);
        durationPassed = findViewById(R.id.duration_passed_text_view);
        buyerName = findViewById(R.id.buyer_name_text_view);
        houseType = findViewById(R.id.buyer_type_text_view);
        buyerImage = findViewById(R.id.buyer_pic_image_btn);
        houseImagesViewPager = findViewById(R.id.house_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishlistButton = findViewById(R.id.add_to_wishlist_btn);
        viewVisibleNotRented = findViewById(R.id.visible_details_when_house_not_rented_linear_layout);
        viewVisibleWhenRented = findViewById(R.id.view_visible_when_house_rented_linear_layout);
        houseSize = findViewById(R.id.house_size_text_view_when_not_rented);
        housePrice = findViewById(R.id.house_price_text_view);
        datePosted = findViewById(R.id.date_posted_text_view);
        houseLocation = findViewById(R.id.house_location_text_view);

        long index = (long) Integer.parseInt(getIntent().getStringExtra("index"));
        long typeOfProperty = (long) Integer.parseInt(getIntent().getStringExtra("typeOfProperty"));

        //final List<String> houseImages = new ArrayList<>();

        String nameOfProperty = getTypeOfPropertyName(typeOfProperty);

        houseImagesList = new ArrayList<>();
        HouseImagesAdapter houseImagesAdapter = new HouseImagesAdapter(houseImagesList);
        loadPropertyData(houseImagesAdapter, index, nameOfProperty, getApplicationContext());

        houseImagesViewPager.setAdapter(houseImagesAdapter);
        houseImagesAdapter.notifyDataSetChanged();

        viewPagerIndicator.setupWithViewPager(houseImagesViewPager, true);

        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                } else {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishlistButton.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));

                }
            }
        });


    }
}
