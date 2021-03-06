package com.example.apnaaasiyana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.LoginSignUpModel.SignInFragment;
import com.example.apnaaasiyana.data.LoginSignUpModel.SignUpFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private FirebaseAuth mAuth;

    public static boolean OnResetPasswordFragment = false;
    public static boolean setSignUpFragment = false;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        //Todo : get specific user details, and take him to the mainIntent
//        //for now just dummy intent being used
//
//        if(currentUser != null){
//
//            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//
//        }
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();

        frameLayout = findViewById(R.id.register_frame_layout);

        if (setSignUpFragment) {
            setSignUpFragment = false;
            setDefaultFragment(new SignUpFragment());
        } else {
            setDefaultFragment(new SignInFragment());
        }


        FirebaseUser currentUser = mAuth.getCurrentUser();
        //Todo : get specific user details, and take him to the mainIntent
        //for now just dummy intent being used

        if(currentUser != null){

            startActivity(new Intent(RegisterActivity.this, MainActivity.class));

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(OnResetPasswordFragment){
                OnResetPasswordFragment = false;
                setFragment(new SignInFragment());
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    private void setDefaultFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(), fragment);
        transaction.commit();
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_out_from_right);
        transaction.replace(frameLayout.getId(), fragment);
        transaction.commit();

    }

}
