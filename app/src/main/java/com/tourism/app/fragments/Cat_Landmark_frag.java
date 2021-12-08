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

public class Cat_Landmark_frag extends Fragment {

    RecyclerView landListRecycle;
    FirebaseFirestore db;


    //place list recycle
    CategoryDetailsAdapter landAdapter;
    List<PlaceListModel> landModelList;


    public Cat_Landmark_frag() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.cat_landmark_frag, container, false);

        db = FirebaseFirestore.getInstance();

        landListRecycle = root.findViewById(R.id.placelist_landmark);

        //place list db
        landListRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        landModelList = new ArrayList<>();
        landAdapter = new CategoryDetailsAdapter(getContext(),landModelList);

        landListRecycle.setAdapter(landAdapter);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlaceListModel landModel = document.toObject(PlaceListModel.class);
                                if(document.get("type").equals("landmark")){
                                    landModelList.add(landModel);
                                    landAdapter.notifyDataSetChanged();
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