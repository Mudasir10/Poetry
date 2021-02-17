package com.mudasir.poetry.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mudasir.poetry.R;
import com.mudasir.poetry.activities.ImagesSlider;
import com.mudasir.poetry.listener.OnImageClickListener;
import com.mudasir.poetry.ui.models.Images;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private ArrayList<String> list;
    private Context mContext;
    private OnImageClickListener listener;

    public ImageAdapter(ArrayList<String> list, Context mContext, OnImageClickListener listener) {
        this.list = list;
        this.mContext = mContext;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.single_item_image,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Picasso.get().load(list.get(position))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(list.get(position))
                                .into(holder.imageView);
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private ProgressBar progressBar;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            progressBar=itemView.findViewById(R.id.progressbar);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
}
