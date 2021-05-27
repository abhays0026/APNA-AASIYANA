package com.example.apnaaasiyana.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.apnaaasiyana.Adapters.CategoryActivityAdapter;
import com.example.apnaaasiyana.Adapters.CategoryAdapter;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.HomePageModel;
import com.example.apnaaasiyana.data.Model.HorizontalProductScrollModel;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.categoriesMap;
import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.loadCategoryData;

public class CategoryActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private Toolbar toolbar;
    private RecyclerView categoryRecyclerView;

    private List<HorizontalProductScrollModel> categoryList = new ArrayList<>();
    private CategoryActivityAdapter categoryActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        categoryRecyclerView = findViewById(R.id.category_recyclerview);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);

        categoryRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));


        Toast.makeText(this, "Property : " + title, Toast.LENGTH_SHORT).show();


        if (!title.toLowerCase().equals("home")) {
            if (categoriesMap.get(title) != null) {

                categoryList = categoriesMap.get(title);
                categoryActivityAdapter = new CategoryActivityAdapter(categoriesMap.get(title));
                categoryRecyclerView.setAdapter(categoryActivityAdapter);
                //Toast.makeText(this, "First statement ", Toast.LENGTH_SHORT).show();

            } else {

                categoriesMap.put(title, new ArrayList<HorizontalProductScrollModel>());
                categoryActivityAdapter = new CategoryActivityAdapter(categoriesMap.get(title));
                loadCategoryData(categoryActivityAdapter, title, this);

            }

        }else{
            String titleArray[] = {"Flats", "Villa", "Rooms", "Independent"};

            for( String i : titleArray){


                if (categoriesMap.get(title) != null) {

                    categoryList = categoriesMap.get(title);
                    categoryActivityAdapter = new CategoryActivityAdapter(categoriesMap.get(title));
                    categoryRecyclerView.setAdapter(categoryActivityAdapter);
                    //Toast.makeText(this, "First statement ", Toast.LENGTH_SHORT).show();

                } else {

                    categoriesMap.put(title, new ArrayList<HorizontalProductScrollModel>());
                    categoryActivityAdapter = new CategoryActivityAdapter(categoriesMap.get(title));



                    loadCategoryData(categoryActivityAdapter, title, this);

                }

                for(HorizontalProductScrollModel j : categoriesMap.get(i)){


                    categoriesMap.get("Home").add(j);

                }



            }
        }

        categoryRecyclerView.setAdapter(categoryActivityAdapter);


    }
}
