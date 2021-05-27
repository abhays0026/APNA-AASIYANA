package com.example.apnaaasiyana.FireBaseQueries.FirestorePostQueries;

import android.content.Context;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.apnaaasiyana.data.Model.CategoryModel;
import com.example.apnaaasiyana.data.Model.HouseDetails;
import com.example.apnaaasiyana.data.Model.PropertyTypeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogRecord;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.firestore;

public class DbPostQueries {


    public static int count;

    //private final Handler handler = new Handler();

    public static void uploadNewAddedPropertyDataToFirestore(final HouseDetails houseDetails,
                                                             final List<Uri> houseImagesLinksList,
                                                             final int index, final String typeOfPropertyName,
                                                             int typeOFPropertyInt, final String date) {

        final String houseName = getHouseName(typeOfPropertyName);

        Map<String, String> dummyMap = new HashMap<>();
        dummyMap.put("HI", "HI");

        firestore.collection("CATEGORIES").document(typeOfPropertyName)
                .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                .set(houseDetails.getTenantDetails());


        CountDownTimer mCountDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("carParking", houseDetails.isCarParkAvailable());


                //System.out.println("DATE : " + houseDetails.getDatePosted().toString());
                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("date", date);


                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("houseAddress", houseDetails.getHouseAddress());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("houseImage", houseImagesLinksList.get(0).toString());


//                Map<String, Object> map = new HashMap<>();
//                map.put("houseImages", Arrays.asList(houseImagesLinksList.toArray()));
//                firestore.collection("CATEGORIES").document(typeOfPropertyName)
//                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
//                        .update(map);


                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("houseName", houseDetails.getHouseName());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("housePrice", houseDetails.getHousePrice());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("houseSize", houseDetails.getHouseSize());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("houseType", houseDetails.getHouseType());

                int index1 = index;
                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("index", index);

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("isRented", houseDetails.isRented());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("isVacant", !houseDetails.isRented());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("noOfBathrooms", houseDetails.getNoOfBathrooms());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("noOfBedrooms", houseDetails.getNoOfBedrooms());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("ratingAverage", houseDetails.getAverageRatings());
//
//                firestore.collection("CATEGORIES").document(typeOfPropertyName)
//                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
//                        .update("ratings", houseDetails.getRatings());
//
                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("ratingsTotal", houseDetails.getTotalRating());
//
//
                String rentAgreement = houseDetails.getRentAgreement();

                if (rentAgreement == null || rentAgreement.equals("")) {
                    rentAgreement = "";
                }
//
//
//
                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("rentAgreement", houseDetails.getRentAgreement());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("houseCarpetArea", houseDetails.getHouseCarpetArea());

//
//                //houseType and typeOfproperty are the same
                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("typeOfProperty", houseDetails.getHouseType());
//
                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("userIdOfHouseOwner", FirebaseAuth.getInstance().getUid());

                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("userIdOfTenant", "");

//                firestore.collection("CATEGORIES").document(typeOfPropertyName)
//                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
//                        .update("inte", "");

                int s = houseImagesLinksList.size();
                String[] imageLinks = new String[s];

                for (int i = 0; i < s; ++i) {
                    imageLinks[i] = houseImagesLinksList.get(i).toString();
                }


                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("houseImages", FieldValue.arrayUnion(imageLinks));


                firestore.collection("CATEGORIES").document(typeOfPropertyName)
                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
                        .update("tenantDetails", houseDetails.getTenantDetails());


                //adding to owners id
                uploadPropertyToFirebaseIDOfOwner(houseName, index);

                //for ratings
//                int[] ratings = new int[5];
//                for(int i=0;i<5;++i) ratings[i] = 0;
//                firestore.collection("CATEGORIES").document(typeOfPropertyName)
//                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
//                        .update("ratings", FieldValue.arrayUnion(houseDetails.getRatings()));


//                int s1 = 5;
//                //int s1 = houseImagesLinksList.size();
//                int[] imageLinks1 = new int[s1];
//
//                for(int i=0;i<s1;++i){
//                    imageLinks1[i] = 0;
//                }
//
//
//
//                firestore.collection("CATEGORIES").document(typeOfPropertyName)
//                        .collection(typeOfPropertyName.toUpperCase()).document(houseName + "_" + index)
//                        .update("ratings", FieldValue.arrayUnion(imageLinks1));


            }
        }.start();


    }

    private static void uploadPropertyToFirebaseIDOfOwner(final String propertyName
            , final int index) {

//        firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
//                .update()

    }

    private static String getHouseName(final String typeOfProperty) {

        String propertyName = typeOfProperty;
        if (typeOfProperty.toLowerCase().equals("flats")) {
            propertyName = "Flat";
        } else if (typeOfProperty.toLowerCase().equals("villa")) {
            propertyName = "Villa";
        } else if (typeOfProperty.toLowerCase().equals("rooms")) {
            propertyName = "Room";
        } else if (typeOfProperty.toLowerCase().equals("independent")) {
            propertyName = "Independent";
        }

        return propertyName;


    }


    public static void getTotalPropertiesOfRequiredType(final String typeOfProperty) {

        final int count2 = count;
        // if(typeOfProperty.toLowerCase().equals("flats")) {


        firestore.collection("CATEGORIES").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                System.out.println("Here : " + documentSnapshot.get("categoryName").
                                        toString().toUpperCase());

                                if (typeOfProperty.toUpperCase().equals(
                                        documentSnapshot.get("categoryName").toString().toUpperCase())) {

                                    System.out.println("Here2 : " + documentSnapshot.get("categoryName").
                                            toString().toUpperCase() + " count : " + documentSnapshot.get("count").toString());

                                    count = Integer.parseInt(documentSnapshot.get("count").toString());
                                }
                            }

                        }
                    }
                });

        // }
//
//
//                    Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                {
//
//                    }
//
//         //       return count;
//            }
//        }, 4000);


        // return count;

    }

    public static void increasePropertyCount(final String typeOfProperty, final int count) {

        firestore.collection("CATEGORIES").document(typeOfProperty).update("count", count);

//        firestore.collection("CATEGORIES").document(typeOfProperty).update("count2", count);


    }

}
