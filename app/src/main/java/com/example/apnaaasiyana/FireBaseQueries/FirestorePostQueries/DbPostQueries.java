package com.example.apnaaasiyana.FireBaseQueries.FirestorePostQueries;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.apnaaasiyana.data.Model.CategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.os.Handler;
import java.util.logging.LogRecord;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.firestore;

public class DbPostQueries {


    public static int count;

    //private final Handler handler = new Handler();

    public static void uploadNewAddedPropertyDataToFirestore(){



    }


    public static void getTotalPropertiesOfRequiredType(final String typeOfProperty) {

        final int count2 = count;
        if(typeOfProperty.toLowerCase().equals("flats")) {


            firestore.collection("CATEGORIES").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                    System.out.println("Here : "  +  documentSnapshot.get("categoryName").
                                            toString().toUpperCase());

                                    if (typeOfProperty.toUpperCase().equals(
                                            documentSnapshot.get("categoryName").toString().toUpperCase())) {

                                        System.out.println("Here2 : "  +  documentSnapshot.get("categoryName").
                                                toString().toUpperCase() + " count : " + documentSnapshot.get("count").toString());

                                        count = Integer.parseInt(documentSnapshot.get("count").toString());
                                    }
                                }

                            }
                        }
                    });

        }
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

    public static void increasePropertyCount(final String typeOfProperty, final int count){

        firestore.collection("CATEGORIES").document(typeOfProperty).update("count", count);

    }

}
