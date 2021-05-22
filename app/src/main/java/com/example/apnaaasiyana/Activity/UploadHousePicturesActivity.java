package com.example.apnaaasiyana.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.HouseDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import static com.example.apnaaasiyana.FireBaseQueries.FirestorePostQueries.DbPostQueries.count;
import static com.example.apnaaasiyana.FireBaseQueries.FirestorePostQueries.DbPostQueries.getTotalPropertiesOfRequiredType;
import static com.example.apnaaasiyana.FireBaseQueries.FirestorePostQueries.DbPostQueries.increasePropertyCount;
import static com.example.apnaaasiyana.FireBaseQueries.FirestorePostQueries.DbPostQueries.uploadNewAddedPropertyDataToFirestore;

public class UploadHousePicturesActivity extends AppCompatActivity {


    //ProgressDialog TempDialog;
    CountDownTimer mCountDownTimer, mCountDownTimer1;
    int i = 0;


    private ConstraintLayout constraintLayout;
    private ImageView selectImage1;
    private ImageView selectImage2;
    private ImageView selectImage3;
    private ImageView selectImage4;
    private ImageView selectImage5;
    private ImageView selectImage6;

    private Button uploadImagesButton;

    String typeOfProperty;
    private int typeOFPropertyInt;

    private List<Uri> imageListLinks;

    private int index;
    private static final int PICK_IMAGE = 1;
    Button chooserBtn, uploaderBtn;
    TextView alert;
    List<Uri> imageList = new ArrayList();
    private int upload_count = 0;
    private ProgressDialog progressDialog;
    List<String> urlStrings;

    private StorageReference storageReference;
    private HouseDetails houseDetails;

    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_house_pictures);

        storageReference = FirebaseStorage.getInstance().getReference();

        constraintLayout = findViewById(R.id.upload_house_images_contraint_layout);
        selectImage1 = findViewById(R.id.uplaod_image_1);
        selectImage2 = findViewById(R.id.uplaod_image_2);
        selectImage3 = findViewById(R.id.uplaod_image_3);
        selectImage4 = findViewById(R.id.uplaod_image_4);
        selectImage5 = findViewById(R.id.uplaod_image_5);
        selectImage6 = findViewById(R.id.uplaod_image_6);

        uploadImagesButton = findViewById(R.id.upload_images_button);

        houseDetails = (HouseDetails) getIntent().getSerializableExtra("houseDetails");
        typeOfProperty = getIntent().getStringExtra("typeOfProperty");
        date = getIntent().getStringExtra("date");


        selectImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfilePic(1);
            }
        });

        //if (selectImage1.getDrawable() != null) {

        selectImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfilePic(2);
            }
        });

        //}

        //if (selectImage2.getDrawable() != null) {
        selectImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfilePic(3);
            }
        });
        //}

        if (selectImage3.getDrawable() != null) {

            selectImage4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadProfilePic(4);
                }
            });
        }

        if (selectImage4.getDrawable() != null) {
            selectImage5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadProfilePic(5);
                }
            });
        }

        if (selectImage5.getDrawable() != null) {
            selectImage6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadProfilePic(6);
                }
            });
        }

        Toast.makeText(UploadHousePicturesActivity.this,
                ", Imagelist size : " + imageList.size(), Toast.LENGTH_SHORT)
                .show();
        if (imageList.size() >= 1) {

            uploadImagesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UploadHousePicturesActivity.this, "Upload Images Button clicked" +
                            ", Imagelist size : " + imageList.size(), Toast.LENGTH_SHORT)
                            .show();
                    UploadImagesToFirebase(imageList);
                }
            });

        }

    }

    private void UploadImagesToFirebase(final List<Uri> imageList) {

        final String propertyName = getTypeOfPropertyName(typeOfProperty);

        imageListLinks = new ArrayList<>();

        //int count = -1;

        if (propertyName != null) {
            //TODO : increase the count as soon as posting a new property


            getTotalPropertiesOfRequiredType(propertyName);

            constraintLayout.setAlpha((float) 0.3);
            constraintLayout.setEnabled(false);
            mCountDownTimer = new CountDownTimer(4000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                    constraintLayout.setAlpha(1);
                    constraintLayout.setEnabled(true);
                    Toast.makeText(UploadHousePicturesActivity.this, "property : " + propertyName +
                            "  count : " + count, Toast.LENGTH_SHORT).show();

                    count++;
                    //upload image to firebase

                    for (int i = 0; i < imageList.size(); ++i) {

                        Uri imageUri = imageList.get(i);

                        final int count2 = i + 1;
                        final StorageReference fileReference = storageReference.child("Categories").child(typeOfProperty.toUpperCase()).
                                child(propertyName + "_" + count)
                                .child(propertyName + "_" + count + "_" + count2);

                        final int finalCount = count;
                        fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                Toast.makeText(UploadHousePicturesActivity.this, "File " + count2 + "Uploaded Successfully !", Toast.LENGTH_SHORT).show();

                                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        imageListLinks.add(uri);
                                        Toast.makeText(UploadHousePicturesActivity.this,
                                                "Images Uploaded Successfully !", Toast.LENGTH_SHORT).show();

                                        Toast.makeText(UploadHousePicturesActivity.this,
                                                "URI : " + uri.toString(), Toast.LENGTH_SHORT).show();


                                        int count3 = finalCount;
                                        index = count3;
                                        increasePropertyCount(typeOfProperty, count3);

                                    }
                                });

                                mCountDownTimer1 = new CountDownTimer(3000, 1000){
                                    @Override
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    @Override
                                    public void onFinish() {
//                                        Toast.makeText(UploadHousePicturesActivity.this,
//                                                "date : " + houseDetails.getDatePosted().toString()
//                                        ,Toast.LENGTH_SHORT).show();

                                        uploadNewAddedPropertyDataToFirestore(houseDetails,imageListLinks,
                                                index,propertyName,
                                                typeOFPropertyInt, date);

                                        Intent intent = new Intent(UploadHousePicturesActivity.this,
                                                MainActivity.class);

                                        Toast.makeText(UploadHousePicturesActivity.this,
                                                "Successful ", Toast.LENGTH_SHORT).show();

                                        //startActivity(intent);

                                    }
                                }.start();


//                startActivity(new Intent(UploadProfilePic.this, MainActivity.class));

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UploadHousePicturesActivity.this, "Failed to upload File !", Toast.LENGTH_SHORT).show();

                            }
                        });


                    }


                }
            }.start();


        } else {
            Toast.makeText(UploadHousePicturesActivity.this, "Failed to upload File !", Toast.LENGTH_SHORT).show();

        }


    }


    private void uploadProfilePic(final int imageNumber) {
        //open gallery
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(openGalleryIntent, imageNumber);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {

                Uri imageUri = data.getData();
                selectImage1.setImageURI(imageUri);
                imageList.add(imageUri);
                Toast.makeText(UploadHousePicturesActivity.this,
                        ", Imagelist size : " + imageList.size(), Toast.LENGTH_SHORT)
                        .show();

            }

        } else if (requestCode == 2) {

            if (resultCode == Activity.RESULT_OK) {

                Uri imageUri = data.getData();
                selectImage2.setImageURI(imageUri);
                imageList.add(imageUri);
                Toast.makeText(UploadHousePicturesActivity.this,
                        ", Imagelist size : " + imageList.size(), Toast.LENGTH_SHORT)
                        .show();


            }

        } else if (requestCode == 3) {

            if (resultCode == Activity.RESULT_OK) {

                Uri imageUri = data.getData();
                selectImage3.setImageURI(imageUri);
                imageList.add(imageUri);
                Toast.makeText(UploadHousePicturesActivity.this,
                        ", Imagelist size : " + imageList.size(), Toast.LENGTH_SHORT)
                        .show();

            }

        } else if (requestCode == 4) {

            if (resultCode == Activity.RESULT_OK) {

                Uri imageUri = data.getData();
                selectImage4.setImageURI(imageUri);
                imageList.add(imageUri);
                Toast.makeText(UploadHousePicturesActivity.this,
                        ", Imagelist size : " + imageList.size(), Toast.LENGTH_SHORT)
                        .show();

            }

        } else if (requestCode == 5) {

            if (resultCode == Activity.RESULT_OK) {

                Uri imageUri = data.getData();
                selectImage5.setImageURI(imageUri);
                imageList.add(imageUri);
                Toast.makeText(UploadHousePicturesActivity.this,
                        ", Imagelist size : " + imageList.size(), Toast.LENGTH_SHORT)
                        .show();

            }

        } else if (requestCode == 6) {

            if (resultCode == Activity.RESULT_OK) {

                Uri imageUri = data.getData();
                selectImage6.setImageURI(imageUri);
                imageList.add(imageUri);
                Toast.makeText(UploadHousePicturesActivity.this,
                        ", Imagelist size : " + imageList.size(), Toast.LENGTH_SHORT)
                        .show();

            }

        }


        if (imageList.size() >= 1) {

            uploadImagesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UploadHousePicturesActivity.this, "Upload Images Button clicked" +
                            ", Imagelist size : " + imageList.size(), Toast.LENGTH_SHORT)
                            .show();
                    UploadImagesToFirebase(imageList);
                }
            });
        }


    }


    private String getTypeOfPropertyName(final String typeOfProperty) {

        String propertyName = typeOfProperty;
        if (typeOfProperty.toLowerCase().equals("flats")) {
            typeOFPropertyInt = 1;
            propertyName = "Flats";
        } else if (typeOfProperty.toLowerCase().equals("villa")) {
            propertyName = "Villa";
            typeOFPropertyInt = 2;
        } else if (typeOfProperty.toLowerCase().equals("rooms")) {
            propertyName = "Rooms";
            typeOFPropertyInt = 3;
        } else if (typeOfProperty.toLowerCase().equals("independent")) {
            propertyName = "Independent";
            typeOFPropertyInt = 4;
        }

        return propertyName;
    }


}
