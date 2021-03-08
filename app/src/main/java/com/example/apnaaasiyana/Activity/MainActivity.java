package com.example.apnaaasiyana.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.apnaaasiyana.Fragments.HomeScreenFragment;
import com.example.apnaaasiyana.Fragments.MyAccountFragment;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private StorageReference storageReference;

    private static final int DRAWER_CLOSE_DELAY_MS = 1000;
    private AppBarConfiguration mAppBarConfiguration;

    private Window window;

    private Toolbar toolbar;

    private FrameLayout frameLayout;
    private int currentFragement = -1;

    private ImageView actionBarLogo;
    private ImageView profilePic;
    private TextView userName;
    private TextView userEmail;
    private ImageView isProfileVerified;

    private FirebaseUser user;
    private NavigationView navigationView;
    private View header;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.action_bar_logo);
        setSupportActionBar(toolbar);

        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        frameLayout = findViewById(R.id.main_frame_layout);

        FloatingActionButton fab = findViewById(R.id.fab);


        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profilePicStorageReference = storageReference.child("USERS").child("profile_pics")
                .child(FirebaseAuth.getInstance().getCurrentUser().getEmail() + "_profile_pic");
        //Uri uri = (Uri)getIntent().getExtras();

        //Todo : change the function of floating btn afterwards or remove it
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        header = navigationView.getHeaderView(0);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.my_history, R.id.nav_my_wishlist,
                R.id.rate_us, R.id.nav_signout)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        user = FirebaseAuth.getInstance().getCurrentUser();

        profilePic = header.findViewById(R.id.upload_profile_pic_image_view);
        userName = header.findViewById(R.id.user_name);
        userEmail = header.findViewById(R.id.user_email);
        isProfileVerified = findViewById(R.id.is_profile_verified);



        setUserDetailsInNavView(user, profilePicStorageReference);


        //navigationView.setNavigationItemSelectedListener(this);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {

                Snackbar.make(navigationView, menuItem.getTitle() + " pressed", Snackbar.LENGTH_SHORT).show();

                selectDrawerItem(menuItem);
                return true;

            }
        });
        navigationView.getMenu().getItem(0).setChecked(true);

        navigationView.bringToFront();
        navigationView.requestLayout();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setFragment(new HomeScreenFragment());



    }

    public void selectDrawerItem(MenuItem menuItem) {

        Fragment fragment = null;


        int id = menuItem.getItemId();
        menuItem.setChecked(true);

        //Toast.makeText(this, " ID : " + id, Toast.LENGTH_SHORT).show();



        if (id == R.id.nav_home) {//nav_my_mall
            actionBarLogo.setVisibility(View.INVISIBLE);

//            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeScreenFragment());
        } else if (id == R.id.my_history) {

            //gotoFragment("My Orders", new MyOrderFragment(),ORDERS_FRAGMENT);

        } else if (id == R.id.nav_my_wishlist) {

            //gotoFragment("My Rewards",new MyRewardsFragement(),REWARDS_FRAGMENT);


        } else if (id == R.id.rate_us) {
            // gotoFragment("My Cart",new MyCartFragment(),CART_FRAGMENT);
        } else if (id == R.id.nav_my_wishlist) {
            //gotoFragment("My WishList",new MyWishlistFragment(),WISHLIST_FRAGMENT);

        } else if (id == R.id.nav_signout) {

            signOUtFunction(MainActivity.this);

        }else if(id == R.id.nav_share){

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");

            //TODO : in shareBody, put the link of the app to download
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        }else if (id == R.id.nav_my_account){

            //TODO : populate it as needed

            setFragment(new MyAccountFragment());


        }


        setTitle(menuItem.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public boolean onNavigationItemSelected(MenuItem menuItem) {
        //Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        menuItem.setChecked(true);
        //drawerLayout.closeDrawers();

        if (id == R.id.nav_home) {//nav_my_mall
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeFragment());
        } else if (id == R.id.my_history) {

            //gotoFragment("My Orders", new MyOrderFragment(),ORDERS_FRAGMENT);

        } else if (id == R.id.nav_my_wishlist) {

            //gotoFragment("My Rewards",new MyRewardsFragement(),REWARDS_FRAGMENT);


        } else if (id == R.id.rate_us) {
            // gotoFragment("My Cart",new MyCartFragment(),CART_FRAGMENT);
        } else if (id == R.id.nav_my_wishlist) {
            //gotoFragment("My WishList",new MyWishlistFragment(),WISHLIST_FRAGMENT);

        } else if (id == R.id.nav_signout) {

            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                FirebaseAuth.getInstance().signOut();
            }
            RegisterActivity.setSignUpFragment = false;

            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            finish();
            //gotoFragment("My Account", new MyAccountFragment(),ACCOUNT_FRAGMENT);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(frameLayout.getId(), fragment);
        transaction.commit();
    }


    public void signOUtFunction(final Context context){
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
        }
        RegisterActivity.setSignUpFragment = false;

        context.startActivity(new Intent(context, RegisterActivity.class));

        finish();


    }

    private void setUserDetailsInNavView(final FirebaseUser user,
                                         final StorageReference profilePicStorageReference){

        if(user != null ){
            profilePicStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(profilePic);

                }
            });
            userName.setText(user.getDisplayName());
            userEmail.setText(user.getEmail());

        }else{

            Toast.makeText(this, "You are not Signed in ! ", Toast.LENGTH_SHORT).show();

        }

    }


}
