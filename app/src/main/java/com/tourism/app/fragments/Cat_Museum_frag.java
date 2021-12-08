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

public class Cat_Museum_frag extends Fragment {

    RecyclerView musuemList;
    FirebaseFirestore db;


    //place list recycle
    CategoryDetailsAdapter museumAdapter;
    List<PlaceListModel> museumModelList;


    public Cat_Museum_frag() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.cat_museum_frag, container, false);

        db = FirebaseFirestore.getInstance();

        musuemList = root.findViewById(R.id.placelist_museum);

        //place list db
        musuemList.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        museumModelList = new ArrayList<>();
        museumAdapter = new CategoryDetailsAdapter(getContext(),museumModelList);

        musuemList.setAdapter(museumAdapter);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlaceListModel museumModel = document.toObject(PlaceListModel.class);
                                if(document.get("type").equals("museum")){
                                    museumModelList.add(museumModel);
                                    museumAdapter.notifyDataSetChanged();
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