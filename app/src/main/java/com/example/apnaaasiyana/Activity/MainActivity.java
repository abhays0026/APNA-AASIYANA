package com.example.apnaaasiyana.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.apnaaasiyana.HomeScreenFragment;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.ui.home.HomeFragment;
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

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.loadCategoryData;

public class MainActivity extends AppCompatActivity {

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

    FirebaseUser user;
    private NavigationView navigationView;

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

        profilePic = findViewById(R.id.profile_pic);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);


        user = FirebaseAuth.getInstance().getCurrentUser();

        //if(user != null){

            //Todo : user glide to replace it the image in the firebase that is uploaded
            // profilePic.setImageResource(R.mipmap.home);
           // userName.setText(user.getDisplayName());
            //userEmail.setText(user.getEmail());

        //}


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
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.explore_properties, R.id.nav_my_wishlist,
                R.id.rate_us, R.id.nav_signout)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


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



//        loadCategoryData( "Flats", this);
//        loadCategoryData( "Independent", this);
//        loadCategoryData( "Villa", this);
//        loadCategoryData( "Rooms", this);






    }

    public void selectDrawerItem(MenuItem menuItem) {

        Fragment fragment = null;


        int id = menuItem.getItemId();
        menuItem.setChecked(true);


        if (id == R.id.nav_home) {//nav_my_mall
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeFragment());
        } else if (id == R.id.explore_properties) {

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
        } else if (id == R.id.explore_properties) {

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

}
