package com.example.apnaaasiyana.Fragments;


import android.os.Bundle;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.firestore;
import static  com.example.apnaaasiyana.FireBaseQueries.DBqueries.loadCategories;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.apnaaasiyana.Adapters.CategoryAdapter;
import com.example.apnaaasiyana.Adapters.HomePageAdapter;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.CategoryModel;
import com.example.apnaaasiyana.data.Model.HomePageModel;
import com.google.api.Distribution;

import java.util.ArrayList;
import java.util.List;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.categoryModelList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeScreenFragment extends Fragment {

    public static int VACANT_HOUSE = 1;
    public static long RENTED_HOUSE = 2;

    private EditText searchBar;
    private RecyclerView homePageRecyclerView;
    private RecyclerView categoryRecycelerView;
    //private List<CategoryModel> categoryModelList;
    private CategoryAdapter categoryAdapter;
    private List<HomePageModel> homePageModelList;
    private HomePageAdapter homePageAdapter;


    public HomeScreenFragment() {
        // Required empty public constructor
    }

    


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        categoryRecycelerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        /***
         * Category Recycler view being worked upon
         */
        categoryRecycelerView.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecycelerView.setAdapter(categoryAdapter);

        if(categoryModelList.size() == 0){

            loadCategories(categoryAdapter, getContext());

        }else{

            categoryAdapter.notifyDataSetChanged();

        }


        //Todo : put the items in the categoryModelList from Firebase

        /**
         * Home Page Recycler View being worked upon
         */


        homePageRecyclerView = view.findViewById(R.id.home_page_recycler_view);
        LinearLayoutManager homePageLayoutManager = new LinearLayoutManager(getContext());
        homePageLayoutManager.setOrientation(RecyclerView.VERTICAL);
        homePageRecyclerView.setLayoutManager(homePageLayoutManager);

        homePageModelList = new ArrayList<>();
        homePageAdapter = new HomePageAdapter(homePageModelList);
        homePageRecyclerView.setAdapter(homePageAdapter);

        return view;
    }

}
