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

import java.util.ArrayList;
import java.util.List;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.categoriesMap;
import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.loadCategoryData;

public class CategoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView categoryRecyclerView;

    private List<HorizontalProductScrollModel> categoryList = new ArrayList<>();
    private CategoryActivityAdapter categoryActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //toolbar = findViewById(R.id.category_activity_toolbar);
        //setSupportActionBar(toolbar);

        // setSupportActionBar(toolbar);


        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);

        categoryRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));


          if (categoriesMap.get(title) != null) {

              categoryList = categoriesMap.get(title);
              categoryActivityAdapter = new CategoryActivityAdapter(categoriesMap.get(title));
              categoryRecyclerView.setAdapter(categoryActivityAdapter);

          }else{

              categoriesMap.put(title, new ArrayList<HorizontalProductScrollModel>());
              categoryActivityAdapter = new CategoryActivityAdapter(categoriesMap.get(title));
              loadCategoryData(categoryActivityAdapter, title, this);

          }

          categoryRecyclerView.setAdapter(categoryActivityAdapter);



    }
}
