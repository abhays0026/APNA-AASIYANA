package com.example.apnaaasiyana.FireBaseQueries;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.apnaaasiyana.Adapters.CategoryActivityAdapter;
import com.example.apnaaasiyana.Adapters.CategoryAdapter;
import com.example.apnaaasiyana.Adapters.HouseImagesAdapter;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.CategoryModel;
import com.example.apnaaasiyana.data.Model.HorizontalProductScrollModel;
import com.example.apnaaasiyana.data.Model.HouseDetails;
import com.example.apnaaasiyana.data.Model.PropertyTypeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.apnaaasiyana.Fragments.HomeScreenFragment.RENTED_HOUSE;
import static com.example.apnaaasiyana.Fragments.HomeScreenFragment.VACANT_HOUSE;
import static com.example.apnaaasiyana.utilityClass.getConvertedPrice;
import static com.example.apnaaasiyana.utilityClass.getHouseType;
import static com.example.apnaaasiyana.utilityClass.getPropertyNameFromIndex;
import static com.example.apnaaasiyana.utilityClass.getTypeOfProperty;

public class DBqueries {

    public static FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public static List<CategoryModel> categoryModelList = new ArrayList<>();
    public static List<CategoryModel> categoryModelListRented = new ArrayList<>();

    public static List<PropertyTypeModel> myPropertiesList = new ArrayList<>();

    public static List<HorizontalProductScrollModel> categoryDataList = new ArrayList<>();

    public static List<String> houseImagesList = new ArrayList<>();

    public static HouseDetails houseDetail = new HouseDetails();

    /**
     * List for activities other than the home page
     */
    public static Map<String, List<HorizontalProductScrollModel>> categoriesMap = new HashMap<>();

    public static void loadCategories(final CategoryAdapter categoryAdapter, final Context context) {

        firestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {


                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),
                                        documentSnapshot.get("categoryName").toString()));



                            }

                            categoryAdapter.notifyDataSetChanged();

                        } else {

                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }


    //
    public static void loadCategoryData(final CategoryActivityAdapter categoryAdapter,
                                        final String categoryName,
                                        final Context context) {

        categoryDataList = new ArrayList<>();
        firestore.collection("CATEGORIES")
                .document(categoryName)
                .collection(categoryName.toUpperCase())
                .orderBy("index").get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {

                                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                        long houseType = (long) documentSnapshot.get("houseType");

                                        int typeImage = R.mipmap.home;
                                        if (houseType == VACANT_HOUSE) {
                                            typeImage = R.mipmap.vacant_house;
                                        } else if (houseType == RENTED_HOUSE) {
                                            typeImage = R.mipmap.rented_house;
                                        }

                                        int typeOfProperty = getTypeOfProperty(categoryName);
//                                        categoryDataList.add(new HorizontalProductScrollModel(


                                        //TODO : add one more condition that is owner id is not the same as the properties
                                        //being displayed
                                        if(!(boolean)documentSnapshot.get("isRented")) {
                                            categoriesMap.get(categoryName).add(new HorizontalProductScrollModel(
                                                    (long) documentSnapshot.get("index"),
                                                    (long) typeOfProperty,
                                                    houseType, documentSnapshot.get("houseImage").toString(),
                                                    documentSnapshot.get("houseName").toString(),
                                                    documentSnapshot.get("houseAddress").toString(),
                                                    typeImage, documentSnapshot.get("date").toString()
                                            ));
                                        }

                                            String ownerId = documentSnapshot.get("userIdOfHouseOwner").toString();

                                            if(ownerId.equals(FirebaseAuth.getInstance().getUid())){

                                                myPropertiesList.add(new PropertyTypeModel(
                                                        categoryName,
                                                        (long) documentSnapshot.get("index"),
                                                        documentSnapshot.get("houseImage").toString(),
                                                        documentSnapshot.get("houseAddress").toString(),
                                                        documentSnapshot.get("houseName").toString()));

                                            }




                                    }

                                    categoryAdapter.notifyDataSetChanged();


                                } else {
                                    Toast.makeText(context, "Hello " + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                );

        categoryAdapter.notifyDataSetChanged();

    }


    public static HouseDetails loadPropertyData(final HouseImagesAdapter houseImagesAdapter,
                                                final long index,
                                                final String typeOfPropertyName,
                                                final Context context) {

//        final HouseDetails houseDetails = new HouseDetails();

        houseDetail = new HouseDetails();


        //houseImagesList = new ArrayList<>();

        /**
         * Trimming sting for "FLATS -> FLAT"
         *"ROOMS -> ROOM"
         *
         * for others , it remains the same
         *
         */

        String typeOfPropertyNameTemp = typeOfPropertyName;
        if (typeOfPropertyName.toUpperCase().equals("FLATS")) {
            typeOfPropertyNameTemp = "Flat";
        } else if (typeOfPropertyName.toUpperCase().equals("ROOMS")) {
            typeOfPropertyNameTemp = "Room";
        }


//        Toast.makeText(context, "Property : " + typeOfPropertyNameTemp + "_" + index, Toast.LENGTH_SHORT).show();

        firestore.collection("CATEGORIES").document(typeOfPropertyName)
                .collection(typeOfPropertyName.toUpperCase()).document(typeOfPropertyNameTemp + "_" + index)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot documentSnapshot = task.getResult();

                List<String> houseImgList = (List<String>) documentSnapshot.get("houseImages");

                for (int i = 0; i < houseImgList.size(); ++i) {

                    houseImagesList.add(houseImgList.get(i));

                }

                houseImagesAdapter.notifyDataSetChanged();

                float housePrice = Float.parseFloat(documentSnapshot.get("housePrice").toString());
                String convertedHousePrice = getConvertedPrice(housePrice);
                String houseSize = documentSnapshot.get("houseSize").toString();
//                String houseType = getHouseType(Integer.parseInt(documentSnapshot.get("houseType").toString()));
                long houseType = Long.parseLong(documentSnapshot.get("houseType").toString());
                String datePosted = documentSnapshot.get("date").toString();
                String houseAddress = documentSnapshot.get("houseAddress").toString();
                String houseCarpetArea = documentSnapshot.get("houseCarpetArea").toString();
                long numberOfBedrooms = (long) documentSnapshot.get("noOfBedrooms");
                long numberOfBathrooms = (long) documentSnapshot.get("noOfBathrooms");
                double averageRatings = (double) documentSnapshot.get("ratingAverage");
                boolean isRented = (boolean) documentSnapshot.get("isRented");
                String userIdOfTenant = documentSnapshot.get("userIdOfTenant").toString();
                String userIdOfHouseOwner = documentSnapshot.get("userIdOfHouseOwner").toString();

                // String rentAgreement = documentSnapshot.get("rentAgreement").toString();

                /**
                 * contains ratings : 1 star, 2 star .....
                 */
                List<Long> ratings = new ArrayList<>();
                //List<Long> ratings = (List<Long>) documentSnapshot.get("ratings");

                long totalRating = (long) documentSnapshot.get("ratingsTotal");

                boolean isCarParkAvailable = (boolean) documentSnapshot.get("carParking");

                String houseName = documentSnapshot.get("houseName").toString();

                String rentAgreement = documentSnapshot.get("rentAgreement").toString();

                Map<String, String> tenantDetails = (Map<String, String>) documentSnapshot.get("tenantDetails");


                HouseDetails houseDetails = new HouseDetails();

                houseDetail.setHouseDetails(houseName, isCarParkAvailable,
                        datePosted, houseAddress, houseCarpetArea, convertedHousePrice, houseSize,
                        houseType, numberOfBedrooms, numberOfBathrooms, averageRatings, ratings, totalRating
                        , isRented, userIdOfTenant, userIdOfHouseOwner, rentAgreement, tenantDetails);

                //Toast.makeText(context, "houseDetails 1 : " + houseDetail.toString() , Toast.LENGTH_LONG).show();


                //houseDetail = houseDetails;
                //Todo : create a HouseDetails class, put all the above data into it and return it to the activity

            }
        });

        //Toast.makeText(context, "houseDetails : " + houseDetail.toString() , Toast.LENGTH_LONG).show();


        return houseDetail;
    }

}
