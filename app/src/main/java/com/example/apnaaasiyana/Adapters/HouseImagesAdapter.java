package com.example.apnaaasiyana.Adapters;

import android.graphics.pdf.PdfDocument;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.apnaaasiyana.R;

import java.util.List;

public class HouseImagesAdapter extends PagerAdapter {

    private List<String> houseImages;

    public HouseImagesAdapter(List<String> houseImages) {
        this.houseImages = houseImages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView houseImage = new ImageView(container.getContext());

        houseImage.setImageResource(R.mipmap.home);

        //Todo : change to glide when you connect it to firebase
//        Glide.with(container.getContext()).load(houseImages.get(position))
//                .apply(new RequestOptions().placeholder(R.mipmap.home)).into(houseImage);
//        container.addView(houseImage, 0);
        return houseImage;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ImageView) object);

    }

    @Override
    public int getCount() {
        return houseImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
