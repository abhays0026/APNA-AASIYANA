package com.example.apnaaasiyana.data.LoginSignUpModel;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.example.apnaaasiyana.Activity.MainActivity;
import com.example.apnaaasiyana.Activity.UploadProfilePic;
import com.example.apnaaasiyana.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.apnaaasiyana.FireBaseQueries.UserAuthentication.createUser;
import static com.example.apnaaasiyana.FireBaseQueries.UserAuthentication.user;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private FrameLayout parentFrameLayout;
    private Button loginPageBtn;
    private EditText userName;
    private EditText userPassword;
    private EditText userConfirmPassword;
    private EditText userEmail;
    private FloatingActionButton signUpFloatingBtn;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);
        loginPageBtn = view.findViewById(R.id.sign_in_page_btn);
        userName = view.findViewById(R.id.name_register_page_edit_text);
        userPassword = view.findViewById(R.id.password_register_page_edit_text);
        userConfirmPassword = view.findViewById(R.id.confirm_password_register_page_edit_text);
        userEmail = view.findViewById(R.id.email_register_page_edit_text);
        signUpFloatingBtn = view.findViewById(R.id.register_page_floating_btn);

        signUpFloatingBtn.setEnabled(false);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });

        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signUpFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString());

            }
        });

    }

    private void checkEmailAndPassword(final String userEmail, final String userPassword) {

        if (userEmail.matches(emailPattern)) {

            if (userPassword.equals(userConfirmPassword.getText().toString())) {

                signUpFloatingBtn.setEnabled(false);

                createUser(getActivity().getApplicationContext(),userEmail, userPassword,userName.getText().toString() );

//                if(user != null){
//
//                    Intent intent = new Intent(getActivity().getApplicationContext(), UploadProfilePic.class);
//                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//
//                }

            } else {

                signUpFloatingBtn.setEnabled(true);
                Toast.makeText(getActivity(), "Email format is wrong", Toast.LENGTH_LONG).show();

            }

        } else {

            signUpFloatingBtn.setEnabled(true);
            Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_LONG).show();


        }

    }

    private void checkInputs() {

        if (!TextUtils.isEmpty(userEmail.getText())) {
            if (!TextUtils.isEmpty(userName.getText())) {
                if (!TextUtils.isEmpty(userPassword.getText())) {
                    if (!TextUtils.isEmpty(userConfirmPassword.getText())) {

                        signUpFloatingBtn.setEnabled(true);

                    } else {
                        signUpFloatingBtn.setEnabled(false);
                    }
                } else {
                    signUpFloatingBtn.setEnabled(false);
                }
            } else {
                signUpFloatingBtn.setEnabled(false);
            }
        } else {
            signUpFloatingBtn.setEnabled(false);
        }

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_out_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();

    }


    private void mainIntent() {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);

    }


}
