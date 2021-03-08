package com.example.apnaaasiyana.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apnaaasiyana.Activity.MainActivity;
import com.example.apnaaasiyana.Activity.RegisterActivity;
import com.example.apnaaasiyana.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {


    private FirebaseUser firebaseUser;

    private Button signOutButton;
    private TextView userEmail;
    private TextView userName;



    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        userEmail = view.findViewById(R.id.user_email);
        userName = view.findViewById(R.id.user_name);

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

        return view;
    }


    private void setUserDetails() {

        if(firebaseUser != null){

            userName.setText(firebaseUser.getDisplayName());
            userEmail.setText(firebaseUser.getEmail());

        }

    }


}
