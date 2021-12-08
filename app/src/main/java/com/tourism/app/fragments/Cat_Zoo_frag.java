package com.tourism.app.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tourism.app.R;
import com.tourism.app.adapters.CategoryDetailsAdapter;
import com.tourism.app.models.PlaceListModel;

import java.util.ArrayList;
import java.util.List;

public class Cat_Zoo_frag extends Fragment {

    RecyclerView zooList;
    FirebaseFirestore db;


    //place list recycle
    CategoryDetailsAdapter zooAdap;
    List<PlaceListModel> zooModel;


    public Cat_Zoo_frag() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.cat_zoo_frag, container, false);

        db = FirebaseFirestore.getInstance();

        zooList = root.findViewById(R.id.placelist_zoo);

        //place list db
        zooList.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        zooModel = new ArrayList<>();
        zooAdap = new CategoryDetailsAdapter(getContext(),zooModel);

        zooList.setAdapter(zooAdap);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlaceListModel zModel = document.toObject(PlaceListModel.class);
                                if(document.get("type").equals("zoo")){
                                    zooModel.add(zModel);
                                    zooAdap.notifyDataSetChanged();
                                }else{

                                }
                            }
                        } else {

                        }
                    }
                });

        return root;
    }
}