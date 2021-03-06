package com.example.apnaaasiyana.Adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaaasiyana.Activity.ViewAllActivity;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.HomePageModel;
import com.example.apnaaasiyana.data.Model.HorizontalProductScrollModel;

import org.w3c.dom.Text;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout,
                        parent, false);

        //note : only one view as of now, use a switch case for different views if made and
        // return them respectively by creating their respective objects of their classes
        return new HorizontalProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {


        //For now only horizontalProductViewHolder being used
        //add others subsequently as and when added

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageModelList.get(position)
                .getHorizontalProductScrollModelListList();

        String layoutColor = homePageModelList.get(position).getBackgroundColor();
        String horizontalLayoutTitle = homePageModelList.get(position).getTitle();
        ((HorizontalProductViewHolder) viewHolder).setHorizontalProductLayout(horizontalProductScrollModelList, horizontalLayoutTitle, layoutColor);

    }

    @Override
    public int getItemCount() {

        return homePageModelList.size();
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {

        ////

        private ConstraintLayout container;
        private TextView horizontalLayoutTitle;
        private Button horizontalLayoutViewAllBtn;
        private RecyclerView horizontalRecyclerView;


        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            horizontalLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontalLayoutViewAllBtn = itemView.findViewById(R.id.horizontal_scroll_view_all_btn);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recycler_view);
            //horizontalRecyclerView.setRecycledViewPool(recycledViewPool);
        }

        //Todo : write this function for setting the horizontal product Layout
//        private void setHorizontalProductLayout()
        private void setHorizontalProductLayout(List<HorizontalProductScrollModel>
                                                        horizontalProductScrollModelList,
                                                String title, String color) {

            container.setBackgroundTintList(ColorStateList.valueOf(
                    Color.parseColor(color)
            ));
            horizontalLayoutTitle.setText(title);

            if (horizontalProductScrollModelList.size() > 8) {
                horizontalLayoutViewAllBtn.setVisibility(View.VISIBLE);
                horizontalLayoutViewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Todo : make this View all activity
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code", 0);
                        //TODO: find out the type of property and pass it in the intent
                        viewAllIntent.putExtra("typeOfProperty","");
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            } else {
                horizontalLayoutViewAllBtn.setVisibility(View.INVISIBLE);
            }

            HorizontalProductScrollAdapter horizontalProductScrollAdapter =
                    new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(layoutManager);
            horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();

        }
    }
}
