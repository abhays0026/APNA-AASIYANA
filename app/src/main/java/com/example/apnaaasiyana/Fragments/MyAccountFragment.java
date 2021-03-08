package com.example.apnaaasiyana.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apnaaasiyana.Activity.MainActivity;
import com.example.apnaaasiyana.Activity.RegisterActivity;
import com.example.apnaaasiyana.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.apnaaasiyana.FireBaseQueries.UserAuthentication.verifyEmail;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    private StorageReference storageReference;
    private FirebaseUser firebaseUser;

    private Button signOutButton;
    private TextView verifyEmailTextView;
    private TextView userEmail;
    private TextView userName;
    private FloatingActionButton verifyEmailButton;
    private CircleImageView profileImage;


    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        profileImage = view.findViewById(R.id.profile_image);


        userEmail = view.findViewById(R.id.user_email);
        userName = view.findViewById(R.id.user_name);

        verifyEmailTextView = view.findViewById(R.id.verify_email_text_view);

        verifyEmailButton = view.findViewById(R.id.verify_email_btn);

        StorageReference profilePicStorageReference = storageReference.child("USERS").child("profile_pics")
                .child(FirebaseAuth.getInstance().getCurrentUser().getEmail() + "_profile_pic");

        if (!FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {

            verifyEmailButton.setEnabled(true);
            verifyEmailButton.setImageResource(R.mipmap.not_verified);
            verifyEmailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verifyEmail(view.getContext());
                }
            });


        } else {

            verifyEmailTextView.setVisibility(View.GONE);
            verifyEmailButton.setEnabled(false);
            verifyEmailButton.setImageResource(R.mipmap.verified);

        }

        signOutButton = view.findViewById(R.id.sign_out_btn);

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    FirebaseAuth.getInstance().signOut();
                }
                RegisterActivity.setSignUpFragment = false;

                startActivity(new Intent(getContext(), RegisterActivity.class));


            }
        });

        setUserDetails(firebaseUser, profilePicStorageReference);

        return view;
    }


    private void setUserDetails(final FirebaseUser user,
                                final StorageReference profilePicStorageReference) {

        if (user != null) {
            //Toast.makeText(this, "Email : " + R.id.user_email, Toast.LENGTH_SHORT).show();
            //profilePic.
            profilePicStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(profileImage);

                }
            });
            userName.setText(user.getDisplayName());
            userEmail.setText(user.getEmail());

        } else {

            Toast.makeText(getActivity().getApplicationContext(),
                    "You are not Signed in ! ", Toast.LENGTH_SHORT).show();

        }

    }


}
