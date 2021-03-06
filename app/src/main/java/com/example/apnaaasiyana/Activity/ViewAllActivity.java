package com.example.apnaaasiyana.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.example.apnaaasiyana.Adapters.HorizontalProductScrollAdapter;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.HorizontalProductScrollModel;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        recyclerView = findViewById(R.id.view_all_recycler_view);
        gridView = findViewById(R.id.view_all_grid_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Todo : change the title here by changing it as need and taking this thing to required function and changing things there
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Deals of the day");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int layout_code = getIntent().getIntExtra("layout_code", -1);

        /**
         * layout code :
         * 1. verticle display, recycler view
         * 2. grid view
         */

        //recycler view
        if(layout_code == 1){

//            getSupportActionBar().setDisplayShowTitleEnabled(true);
//            getSupportActionBar().setTitle("Deals of the day");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            gridView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            //add items to the  list through firebase
            // till then when testing, can add dummy data as well
            List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
            HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
            recyclerView.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();



        }else if(layout_code == 2){

            // grid view, populate it after wards
            recyclerView.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);

//populate grid view and set its adapter here

        }




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
