package com.tourism.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.tourism.app.R;
import com.tourism.app.fragments.PlaceListBarFragment;
import com.tourism.app.fragments.PlaceListCtgFragment;
import com.tourism.app.fragments.PlaceListDhaFragment;
import com.tourism.app.fragments.PlaceListKhulFragment;
import com.tourism.app.fragments.PlaceListMymenFragment;
import com.tourism.app.fragments.PlaceListRajFragment;
import com.tourism.app.fragments.PlaceListRangFragment;
import com.tourism.app.fragments.PlaceListSylFragment;
import com.tourism.app.models.DivisionModel;


public class PlaceListActivity extends AppCompatActivity {

    Fragment placeListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        String name = getIntent().getExtras().getString("DivisionName");




        if(name.equals("Mymensingh Division")){
            placeListFragment = new PlaceListMymenFragment();
            loadFragment(placeListFragment);
        }else if(name.equals("Sylhet Division")){
            placeListFragment = new PlaceListSylFragment();
            loadFragment(placeListFragment);
        }else if(name.equals("Chittagong Division")){
            placeListFragment = new PlaceListCtgFragment();
            loadFragment(placeListFragment);
        }else if(name.equals("Barisal Division")){
            placeListFragment = new PlaceListBarFragment();
            loadFragment(placeListFragment);
        }else if(name.equals("Rajshahi Division")){
            placeListFragment = new PlaceListRajFragment();
            loadFragment(placeListFragment);
        }else if(name.equals("Khulna Division")){
            placeListFragment = new PlaceListKhulFragment();
            loadFragment(placeListFragment);
        }else if(name.equals("Dhaka Division")){
            placeListFragment = new PlaceListDhaFragment();
            loadFragment(placeListFragment);
        }else if(name.equals("Rangpur Division")){
            placeListFragment = new PlaceListRangFragment();
            loadFragment(placeListFragment);
        }

    }

    private void loadFragment(Fragment placeListFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.placelist_container, placeListFragment);
        transaction.commit();
    }
}