package com.example.apnaaasiyana.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apnaaasiyana.Adapters.CategoryActivityAdapter;
import com.example.apnaaasiyana.Adapters.MyPropertiesAdapter;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.PropertyTypeModel;

import java.util.List;

import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.categoriesMap;
import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.loadCategoryData;
import static com.example.apnaaasiyana.FireBaseQueries.DBqueries.myPropertiesList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPropertiesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyPropertiesAdapter myPropertiesAdapter;
    private List<PropertyTypeModel> propertiesModel;
    private TextView nothingAvailable;


    public MyPropertiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_properties, container, false);

        recyclerView = view.findViewById(R.id.my_properties_fragment_recycler_view);
        nothingAvailable = view.findViewById(R.id.nothing_available);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        myPropertiesAdapter = new MyPropertiesAdapter(myPropertiesList);
        recyclerView.setAdapter(myPropertiesAdapter);
        nothingAvailable.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        //CategoryActivityAdapter categoryAdapter = new CategoryActivityAdapter(categoriesMap.get("Rooms"));
        if (myPropertiesList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            //loadCategoryData(categoryAdapter, "Rooms", getActivity());
            nothingAvailable.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            myPropertiesAdapter.notifyDataSetChanged();
        }

        return view;
    }


}
