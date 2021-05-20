package com.example.apnaaasiyana.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.apnaaasiyana.Adapters.HouseImagesAdapter;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.HouseDetails;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.firestore;
import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.houseImagesList;
import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.loadPropertyData;
import static com.example.apnaaasiyana.FireBaseQueries.UserAuthentication.user;
import static com.example.apnaaasiyana.utilityClass.getTypeOfPropertyName;

public class HouseDetailsActivity extends AppCompatActivity {

    private LinearLayout viewVisibleWhenNotRented;
    private LinearLayout viewVisibleWhenRented;

    private ViewPager houseImagesViewPager;
    private TabLayout viewPagerIndicator;
    private FloatingActionButton addToWishlistButton;

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
    private TextView houseAddressTextView;
    private MapView houseLocationMap;
    private TextView houseSizeDescription;
    private TextView houseBedroomsDescription;
    private TextView houseBathroomsDescription;
    private TextView houseCarParkAvailable;

    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private boolean carParkAvailable = false;

    private Map<String, String> tenantDetails = new HashMap<>();
    private String rentAgreement;

    private boolean isRented = false;

    // user id of the person who is renting the house at present ( paying guest )
    private String userIdOfPersonRented = "";

    private String userIdOfHouseOwner = "";

    private final Handler handler = new Handler();


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
        houseAddressTextView = findViewById(R.id.house_address_layout_text_view);
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
        viewVisibleWhenNotRented = findViewById(R.id.visible_details_when_house_not_rented_linear_layout);
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
//        HouseDetails houseDetails = loadPropertyData(houseImagesAdapter, index, nameOfProperty, getApplicationContext());
//
//        setupHouseDetails(houseDetails);

        houseImagesViewPager.setAdapter(houseImagesAdapter);
        houseImagesAdapter.notifyDataSetChanged();

        HouseDetails houseDetails = loadPropertyData(houseImagesAdapter, index, nameOfProperty, getApplicationContext());

        //Toast.makeText(getApplicationContext(), "houseDetaisl 2 : " + houseDetails.toString() , Toast.LENGTH_LONG).show();

        doTheAutoRefresh(houseDetails);
        //setupHouseDetails(houseDetails);

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
                //Toast.makeText(getApplicationContext(), "houseDetaisl 2 : " , Toast.LENGTH_LONG).show();

            }
        });


    }

    public static int homeDetailsPageCount = 0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        homeDetailsPageCount = 0;
        finish();

    }

    private void doTheAutoRefresh(final HouseDetails houseDetails) {

        //homeDetailsPageCount = 0;


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Write code for your refresh logic
                setupHouseDetails(houseDetails);
                doTheAutoRefresh(houseDetails);
            }
        };

        handler.postDelayed(runnable, 3000);

    }

    private void setupHouseDetails(final HouseDetails houseDetails) {

        if (homeDetailsPageCount >= 1) {
            handler.removeCallbacks(null);
            return;
        } else {
            homeDetailsPageCount++;
        }

        if (houseDetails.isCarParkAvailable()) {
            houseCarParkAvailable.setText("Yes");
        } else {
            houseCarParkAvailable.setText("No");
        }

        houseSizeDescription.setText(houseDetails.getHouseCarpetArea());
        houseName.setText(houseDetails.getHouseName());


        houseAddressTextView.setText(houseDetails.getHouseAddress());
        //houseAddressTextView = findViewById(R.id.house_address_layout_text_view);
        //houseLocationMap = findViewById(R.id.house_location_map_view);
        //TODO : work needs to be done on rented view as well


        if (houseDetails.isRented()) {


            tenantDetails = houseDetails.getTenantDetails();
            rentAgreement = houseDetails.getRentAgreement();

            final String userIDOfTenant = houseDetails.getUserIdOfTenant();
            //IF need the owner of the house, can get it through the userIdOfHouseOwner in houseDetails
            buyerName.setText(tenantDetails.get("tenantName"));
            //Glide.with();

            //final String imageUrl;
            firestore.collection("USERS").document(userIDOfTenant)
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    final DocumentSnapshot documentSnapshot = task.getResult();

                    Map<String, String> tenant = (Map<String, String>) documentSnapshot.get(userIDOfTenant);
                    String userEmail = tenant.get("userEmail");

                    StorageReference profilePicStorageReference = FirebaseStorage.getInstance().getReference()
                            .child("USERS").child("profile_pics")
                            .child(userEmail.toLowerCase() + "_profile_pic");


                    Toast.makeText(getApplicationContext(), "Tenant detail : " + userEmail, Toast.LENGTH_SHORT).show();

                    if (userEmail != null) {

                        profilePicStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Picasso.get().load(uri).into(buyerImage);

                            }
                        });

                    } else {

                        Toast.makeText(getApplicationContext(), "No Tenant ", Toast.LENGTH_SHORT).show();

                    }


                    /**
                     * getting pdfLink from firebase
                     */
                    //TODO : do the task of uploading the rent agreemnt and when doing the post functionality
                    //TODO : also get the url of uploaded file and save it in the "rentAgreement" variable in the firestore
                    //doing manually in firebase for now


                    viewAgreement.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_VIEW);

                            intent.setDataAndType(Uri.parse(houseDetails.getRentAgreement()), "text/html");

                            startActivity(intent);

                        }
                    });


                }
            });

            durationPassed.setText(tenantDetails.get("durationTenant"));
            monthlyRentRate.setText(tenantDetails.get("tenantRent"));
            lastRentPaidRate.setText(tenantDetails.get("lastRentPaidOnDate"));

            //TODO : complete the VIEW AGREEMENT functionality afterwards


        } else {


            /**
             * these are to be filled when house not rented
             */
            houseSize.setText(houseDetails.getHouseSize());
            housePrice.setText(houseDetails.getHousePrice());
            datePosted.setText(houseDetails.getDatePosted());
        }


        //TODO : add location to location view afterwards and mapView as well
        //houseLocation = findViewById(R.id.house_location_text_view);

    }
}
