package com.mudasir.poetry.ui.Home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTabHost;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.mudasir.poetry.R;
import com.mudasir.poetry.adapter.SectionPagerAdapter;
import com.mudasir.poetry.ui.fragments.EnglishPoetryFragment;
import com.mudasir.poetry.ui.fragments.SindhiPoetryFragment;
import com.mudasir.poetry.ui.fragments.UrduPoetryFragment;

public class HomeFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private HomeViewModel homeViewModel;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager =root.findViewById(R.id.viewpager);
        tabLayout=root.findViewById(R.id.tablayout);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpWithViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }




    @Override
    public void onStart() {
        super.onStart();


    }

    private void setUpWithViewPager(ViewPager viewPager) {
        SectionPagerAdapter sectionPagerAdapter=new SectionPagerAdapter(getChildFragmentManager());

        sectionPagerAdapter.addFragment(new UrduPoetryFragment(),"Urdu Poetry");
        sectionPagerAdapter.addFragment(new SindhiPoetryFragment(),"Sindhi Poetry");
        sectionPagerAdapter.addFragment(new EnglishPoetryFragment(),"English Poetry");

        viewPager.setAdapter(sectionPagerAdapter);

    }

}

