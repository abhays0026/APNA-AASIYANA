package com.example.apnaaasiyana.Adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnaaasiyana.R;
import com.example.apnaaasiyana.data.Model.InterestedBuyerModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HouseDetailsActivityAdapter extends RecyclerView.Adapter<HouseDetailsActivityAdapter.ViewHolder> {

    List<InterestedBuyerModel> interestedBuyersList;

    StorageReference storageReference;

    public HouseDetailsActivityAdapter(List<InterestedBuyerModel> interestedBuyersList) {
        this.interestedBuyersList = interestedBuyersList;
    }


    @NonNull
    @Override
    public HouseDetailsActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interested_buyer_activity,
                parent, false);
        return new HouseDetailsActivityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseDetailsActivityAdapter.ViewHolder viewHolder, int position) {

        String mail = interestedBuyersList.get(position).getEmail();
        String name = interestedBuyersList.get(position).getName();

        viewHolder.setProfileImage(mail);
        viewHolder.setName(name);

    }

    @Override
    public int getItemCount() {
        return interestedBuyersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView interestedBuyerPic;
        private TextView interestedBuyerName;
        private Button approveRequestButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            interestedBuyerName = itemView.findViewById(R.id.interested_buyer_name);
            interestedBuyerPic = itemView.findViewById(R.id.interested_buyer_pic);
            approveRequestButton = itemView.findViewById(R.id.approve_request_button);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //interestedBuyersList
                    //String mail = interestedBuyersList.get(getAdapterPosition()).getEmail();

                    approveRequestButton.setText("Approval Successful");

                }
            });
        }


        private void setProfileImage(final String email) {

            StorageReference profilePicStorageReference = storageReference.child("USERS").child("profile_pics")
                    .child(email + "_profile_pic");

            profilePicStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(interestedBuyerPic);

                }
            });

        }

        private void setName(final String name) {
            interestedBuyerName.setText(name);
        }

    }
}


