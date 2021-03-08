package com.example.apnaaasiyana.Adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.apnaaasiyana.Activity.HouseDetailsActivity;
import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.HorizontalProductScrollModel;

import java.util.List;

import static com.example.apnaaasiyana.Fragments.HomeScreenFragment.RENTED_HOUSE;
import static com.example.apnaaasiyana.Fragments.HomeScreenFragment.VACANT_HOUSE;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter <HorizontalProductScrollAdapter.ViewHolder>{

    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_details_layout, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder viewHolder, int position) {

        long houseType = horizontalProductScrollModelList.get(position).getType();
        String houseAddress = horizontalProductScrollModelList.get(position).getAddress();
        String imageURL = horizontalProductScrollModelList.get(position).getHouseImageLink();
        String date = horizontalProductScrollModelList.get(position).getDate();
        String houseName = horizontalProductScrollModelList.get(position).getHouseName();

        viewHolder.setHouseImage(imageURL);
        viewHolder.setHouseType(houseType,date);
        viewHolder.setNameAddress(houseName,houseAddress);

    }

    @Override
    public int getItemCount() {
        return horizontalProductScrollModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // private ConstraintLayout container;
        private TextView houseType;
        private TextView houseName;
        private TextView houseAddress;
        private ImageView houseImage;
        private TextView date;
        private ImageView houseTypeIcon;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);


            houseType = itemView.findViewById(R.id.house_type_text_view);
            houseName = itemView.findViewById(R.id.house_name_text_view);
            houseAddress = itemView.findViewById(R.id.house_address_text_view);
            houseImage = itemView.findViewById(R.id.house_details_image_view);
            date = itemView.findViewById(R.id.house_date_text_view);
            houseTypeIcon = itemView.findViewById(R.id.house_type_icon_image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), HouseDetailsActivity.class);
                }
            });

        }

        private void setHouseImage(String resource) {
            Glide.with(itemView.getContext()).load(resource)
                    .apply(new RequestOptions().placeholder(R.mipmap.home)).into(houseImage);
        }

        private void setNameAddress(String name, String address) {

            houseName.setText(name);
            houseAddress.setText(address);

        }

        private void setHouseType(long houseType1, String date1) {

            //Todo : add vacant and rented icons in mipmap then replace them here in both types


            if (houseType1 == VACANT_HOUSE) {
                //change image to vacant one
                houseType.setText("VACANT");
                houseType.setBackgroundColor(Color.parseColor("#ff0000"));
                houseTypeIcon.setImageResource(R.mipmap.home);
                date.setText("Since \n" + date1);

            } else if (houseType1 == RENTED_HOUSE) {
                //change image to rented one
                houseType.setBackgroundColor(Color.parseColor("#3333ff"));
                houseTypeIcon.setImageResource(R.mipmap.home);
                date.setText("Rental Date \n" + date + " monthly");

            }

        }

    }
}
