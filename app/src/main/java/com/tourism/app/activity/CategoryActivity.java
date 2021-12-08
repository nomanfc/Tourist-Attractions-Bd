package com.tourism.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.tourism.app.R;
import com.tourism.app.fragments.Cat_Amusement_frag;
import com.tourism.app.fragments.Cat_Landmark_frag;
import com.tourism.app.fragments.Cat_Museum_frag;
import com.tourism.app.fragments.Cat_Nature_frag;
import com.tourism.app.fragments.Cat_Zoo_frag;


public class CategoryActivity extends AppCompatActivity {

    Fragment catDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String name = getIntent().getExtras().getString("name");

        if (name.equals("Amusement Parks")) {
            catDetails = new Cat_Amusement_frag();
            loadFragment(catDetails);
        }else if (name.equals("Landmarks")) {
            catDetails = new Cat_Landmark_frag();
            loadFragment(catDetails);
        }else if (name.equals("Nature Attractions")) {
            catDetails = new Cat_Nature_frag();
            loadFragment(catDetails);
        }else if (name.equals("Museums")) {
            catDetails = new Cat_Museum_frag();
            loadFragment(catDetails);
        }else if (name.equals("Zoo")) {
            catDetails = new Cat_Zoo_frag();
            loadFragment(catDetails);
        }


    }

    private void loadFragment(Fragment catDetails) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.categoyDetails_container, catDetails);
        transaction.commit();
    }
}