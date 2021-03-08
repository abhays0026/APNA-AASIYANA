package com.example.apnaaasiyana.FireBaseQueries;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.apnaaasiyana.Activity.MainActivity;
import com.example.apnaaasiyana.Activity.UploadProfilePic;
import com.example.apnaaasiyana.data.UserClasses.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserAuthentication {

    public static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public static FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    public static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static void createUser(final Context context, final String userEmail,
                                  final String userPassword, final String userName) {

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

//                            //Setting the display name
//                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                                    .setDisplayName(userName).build();
//
//                            user.updateProfile(profileUpdates)
//                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
//                                                Toast.makeText(context, "Profile Updated Succesfully !", Toast.LENGTH_SHORT).show();
//                                                //Log.d(TAG, "User profile updated.");
//                                            }
//                                        }
//                                    });

                            setDisplayName(context, userName);


                            Map<Object, UserDetails> userDetailsMap = new HashMap<>();
                            UserDetails userDetails = new UserDetails();
                            userDetails.setUserEmail(userEmail);
                            userDetails.setUserName(userName);
//                            userDetails.setUserEmail(user.getEmail());
//                            userDetails.setUserName(user.getDisplayName());
                            //userDetails.setUserPassword(use);

                            userDetailsMap.put("UserDetails", userDetails);

                            firestore.collection("USERS")
                                    .add(userDetailsMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                    if (task.isSuccessful()) {
//                                        Intent intent = new Intent(context, MainActivity.class);
//                                        context.startActivity(intent);
                                    } else {

                                        //signUpFloatingBtn.setEnabled(true);
                                        String error = task.getException().getMessage();
                                        Toast.makeText(context, error, Toast.LENGTH_LONG).show();

                                    }

                                }
                            });

//                            Intent intent = new Intent(context, MainActivity.class);
//                            context.startActivity(intent);

                        } else {
                            // flag[0] = 0;
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_LONG).show();

                        }

                    }
                });

        Intent intent = new Intent(context, UploadProfilePic.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("userName", userName);
        context.startActivity(intent);

    }

    public static void verifyEmail(final Context context) {


        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null) {

            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    if (task.isSuccessful()) {

                        Toast.makeText(context, "Verification Link sent to email !", Toast.LENGTH_SHORT).show();

                    } else {
                        String error = task.getException().getMessage();
                        Toast.makeText(context, "Error sending the message. " +
                                "Please try after some time .", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }


    }

    public static void checkIfVerifiedEmail(final Context context) {

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified()) {


            // user is verified, so you can finish this activity or send user to activity which you want.
            //finish();
            // Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
        } else {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();

            //restart this activity

        }

    }

    private static void setDisplayName(final Context context,final String displayName){

        //String displayName = getIntent().getStringExtra("userName");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName).build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Display name updated", Toast.LENGTH_SHORT).show();
                            //Log.d(TAG, "User profile updated.");
                        }
                    }
                });

    }


}
