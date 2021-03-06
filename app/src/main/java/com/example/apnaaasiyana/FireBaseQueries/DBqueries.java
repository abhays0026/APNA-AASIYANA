package com.example.apnaaasiyana.FireBaseQueries;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.apnaaasiyana.Activity.CategoryActivity;
import com.example.apnaaasiyana.Adapters.CategoryActivityAdapter;
import com.example.apnaaasiyana.Adapters.CategoryAdapter;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.CategoryModel;
import com.example.apnaaasiyana.data.Model.HomePageModel;
import com.example.apnaaasiyana.data.Model.HorizontalProductScrollModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.apnaaasiyana.HomeScreenFragment.RENTED_HOUSE;
import static com.example.apnaaasiyana.HomeScreenFragment.VACANT_HOUSE;
import static com.example.apnaaasiyana.utilityClass.getTypeOfProperty;

public class DBqueries {

    public static FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public static List<CategoryModel> categoryModelList = new ArrayList<>();

    public static List<HorizontalProductScrollModel> categoryDataList = new ArrayList<>();

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

                                        categoryDataList.add(new HorizontalProductScrollModel(
                                                (long) documentSnapshot.get("index"),
                                                (long) typeOfProperty,
                                                houseType, documentSnapshot.get("houseImage").toString(),
                                                documentSnapshot.get("houseName").toString(),
                                                documentSnapshot.get("houseAddress").toString(),
                                                typeImage, documentSnapshot.get("date").toString()
                                        ));


                                    }

                                    categoriesMap.put(categoryName, categoryDataList);
                                    categoryAdapter.notifyDataSetChanged();

                                    Toast.makeText(context, "Successfully loaded",
                                            Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(context, "Hello " + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                );

    }

}
