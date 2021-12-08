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

public class Cat_Amusement_frag extends Fragment {

    RecyclerView amuseListRecycle;
    FirebaseFirestore db;


    //place list recycle
    CategoryDetailsAdapter amuseAdapter;
    List<PlaceListModel> amuseModelList;


    public Cat_Amusement_frag() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.cat_amusement_frag, container, false);

        db = FirebaseFirestore.getInstance();

        amuseListRecycle = root.findViewById(R.id.placelist_amusement);

        //place list db
        amuseListRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        amuseModelList = new ArrayList<>();
        amuseAdapter = new CategoryDetailsAdapter(getContext(),amuseModelList);

        amuseListRecycle.setAdapter(amuseAdapter);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlaceListModel amuseModel = document.toObject(PlaceListModel.class);
                                if(document.get("type").equals("amusement")){
                                    amuseModelList.add(amuseModel);
                                    amuseAdapter.notifyDataSetChanged();
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