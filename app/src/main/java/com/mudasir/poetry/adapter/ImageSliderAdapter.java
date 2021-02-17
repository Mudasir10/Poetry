package com.mudasir.poetry.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mudasir.poetry.R;
import com.mudasir.poetry.ui.models.Images;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageSliderAdapter extends PagerAdapter {

    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;


    public ImageSliderAdapter(ArrayList<String> IMAGES, Context context) {
        this.IMAGES = IMAGES;
        this.context = context;
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager vp=(ViewPager)container;
        View v=(View)object;
        vp.removeView(v);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

       inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View v=inflater.inflate(R.layout.sliding_image_item,null);

       ImageView imageView=v.findViewById(R.id.slide_image);

       Picasso.get().load(IMAGES.get(position))
            .into(imageView);

        ViewPager vp=(ViewPager)container;
        vp.addView(v,0);

        return v;

    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
