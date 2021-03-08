package com.example.apnaaasiyana.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apnaaasiyana.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UploadProfilePic extends AppCompatActivity {

    private TextView skipForNowTextView;
    private Button uploadProfilePictureBtn;
    private CircleImageView profilePicCircleImageView;
    private ImageButton selectPicImageButton;

    private StorageReference storageReference;

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        String displayName = getIntent().getStringExtra("userName");
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                .setDisplayName(displayName).build();
//
//        user.updateProfile(profileUpdates)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(UploadProfilePic.this, "Display name updated", Toast.LENGTH_SHORT).show();
//                            //Log.d(TAG, "User profile updated.");
//                        }
//                    }
//                });
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_pic);

        skipForNowTextView = findViewById(R.id.skip_for_now_text_view);
        uploadProfilePictureBtn = findViewById(R.id.upload_profile_picture_btn);
        profilePicCircleImageView = findViewById(R.id.upload_profile_pic_image_view);
        selectPicImageButton = findViewById(R.id.select_image_image_btn);

        storageReference = FirebaseStorage.getInstance().getReference();

        skipForNowTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UploadProfilePic.this, MainActivity.class));

            }
        });

        selectPicImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfilePic();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {

            if (resultCode == Activity.RESULT_OK) {

                Uri imageUri = data.getData();
                profilePicCircleImageView.setImageURI(imageUri);

                UploadImageToFirebase(imageUri);

            }

        }

    }

    private void UploadImageToFirebase(Uri imageUri) {

        //upload image to firebase
        final StorageReference fileReference = storageReference.child("USERS").child("profile_pics")
                .child(FirebaseAuth.getInstance().getCurrentUser().getEmail() + "_profile_pic");

        fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(UploadProfilePic.this, "File Uploaded Successfully !", Toast.LENGTH_SHORT).show();

                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Intent intent = new Intent(UploadProfilePic.this, MainActivity.class);
                        intent.putExtra("imageURI", uri);
                        startActivity(intent);
                        //Picasso.get().load(uri)


                    }
                });

//                startActivity(new Intent(UploadProfilePic.this, MainActivity.class));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadProfilePic.this, "Failed to upload File !", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void uploadProfilePic() {


        //open gallery
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(openGalleryIntent, 1000);


    }


}
