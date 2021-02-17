package com.mudasir.poetry.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudasir.poetry.R;
import com.mudasir.poetry.activities.ImagesSlider;
import com.mudasir.poetry.adapter.ImageAdapter;
import com.mudasir.poetry.listener.OnImageClickListener;

import java.util.ArrayList;


public class EnglishPoetryFragment extends Fragment {

    private DatabaseReference mDatabaseRef;
    private ArrayList<String> imagesList;
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_english_poetry, container, false);

        init(root);


        final OnImageClickListener listener = new OnImageClickListener() {
            @Override
            public void onClick(View v, int pos) {
                Intent intent=new Intent(getContext(), ImagesSlider.class);
                intent.putStringArrayListExtra("list",imagesList);
                intent.putExtra("pos",pos);
                intent.putExtra("type","English Poetry");
                startActivity(intent);

            }
        };

        Query urduQuery = mDatabaseRef.orderByChild("type").equalTo("English");

        // todo Enable Offline Capabilities
        urduQuery.keepSynced(true);

        urduQuery.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {


                imagesList.clear();
                for (DataSnapshot urduSnapshot: dataSnapshot.getChildren()) {
                    String url = urduSnapshot.child("url").getValue().toString();
                    imagesList.add(url);
                }
                imageAdapter=new ImageAdapter(imagesList,getContext(),listener);
                recyclerView.setAdapter(imageAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return root;

    }

    private void init(View root) {

        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Images");
        recyclerView=root.findViewById(R.id.rv_images_english);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        imagesList=new ArrayList<>();

    }
}