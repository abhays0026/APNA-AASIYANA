package com.example.apnaaasiyana.Adapters;

import android.content.Intent;
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
import com.example.apnaaasiyana.data.Model.PropertyTypeModel;

import java.util.List;

public class MyPropertiesAdapter extends RecyclerView.Adapter<MyPropertiesAdapter.ViewHolder> {


    private List<PropertyTypeModel> propertiesModel;
    int houseType = 1;

    public MyPropertiesAdapter(List<PropertyTypeModel> propertiesModel) {
        this.propertiesModel = propertiesModel;
    }

    @NonNull
    @Override
    public MyPropertiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_properties, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyPropertiesAdapter.ViewHolder viewHolder, int position) {

        String icon = propertiesModel.get(position).getImageUrl();
        String address = propertiesModel.get(position).getHouseAddress();
        String typeOfPropertyName = propertiesModel.get(position).getTypeOfPropertyName();
        String name = propertiesModel.get(position).getHouseName();

        viewHolder.setHouseImage(icon);
        viewHolder.setHouseNameAddressType(name, address, typeOfPropertyName, propertiesModel.get(position).getIndex());

        houseType = 1;
        String name1 = typeOfPropertyName + "";

        if (name1.toLowerCase().equals("flats")) {
            houseType = 1;
        } else if (name1.toLowerCase().equals("villa")) {

            houseType = 2;
        } else if (name1.toLowerCase().equals("rooms")) {

            houseType = 3;
        } else if (name1.toLowerCase().equals("independent")) {
            houseType = 4;
        }

    }

    @Override
    public int getItemCount() {
        return propertiesModel.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView myPropertyTypeTextView;
        private ImageView myPropertyImageView;
        private TextView myPropertyNameTextView;
        private TextView myPropertyAddressTextView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            //myPropertyTypeTextView.setTextSize(12);

            myPropertyImageView = itemView.findViewById(R.id.my_properties_image_view);
            myPropertyNameTextView = itemView.findViewById(R.id.my_properties_property_name);
            myPropertyAddressTextView = itemView.findViewById(R.id.my_properties_property_address);
            myPropertyTypeTextView = itemView.findViewById(R.id.my_properties_type_text_view);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//
//                    System.out.println("Hi properties");
//                    Intent intent = new Intent(itemView.getContext(), HouseDetailsActivity.class);
//
//                    intent.putExtra("typeOfProperty", houseType + "");
//                    intent.putExtra("index", propertiesModel.get(getAdapterPosition()).getIndex() + "");
//                    intent.putExtra("houseType", houseType + "");
//
//                    /**
//                     * if previousActivity == CategoryActivityAdapter then 1
//                     * else if previousActivity == MyPropertiesAdapter then 2
//                     */
//                    intent.putExtra("previousActivity", 2 + "");
//
//
//                    itemView.getContext().startActivity(intent);
//                }
//            });


        }

        private void setHouseImage(final String houseImage) {

            if (!houseImage.equals("null")) {
                Glide.with(itemView.getContext()).load(houseImage).
                        apply(new RequestOptions().placeholder(R.mipmap.home)).into(myPropertyImageView);
            }

        }

        private void setHouseNameAddressType(final String name, final String address, final String typeOfProperty, final
                                             long index) {

            myPropertyTypeTextView.setText(typeOfProperty + ", index : " + index);
            myPropertyAddressTextView.setText(address);
            myPropertyNameTextView.setText(name);

        }

    }


}
