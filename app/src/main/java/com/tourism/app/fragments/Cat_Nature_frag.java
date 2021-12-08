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

public class Cat_Nature_frag extends Fragment {

    RecyclerView natureListRecycle;
    FirebaseFirestore db;


    //place list recycle
    CategoryDetailsAdapter natureAdapter;
    List<PlaceListModel> natureModelList;


    public Cat_Nature_frag() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.cat_nature_frag, container, false);

        db = FirebaseFirestore.getInstance();

        natureListRecycle = root.findViewById(R.id.placelist_nature);

        //place list db
        natureListRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        natureModelList = new ArrayList<>();
        natureAdapter = new CategoryDetailsAdapter(getContext(),natureModelList);

        natureListRecycle.setAdapter(natureAdapter);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlaceListModel natureModel = document.toObject(PlaceListModel.class);
                                if(document.get("type").equals("nature")){
                                    natureModelList.add(natureModel);
                                    natureAdapter.notifyDataSetChanged();
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