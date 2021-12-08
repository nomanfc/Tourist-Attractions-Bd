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
import com.tourism.app.adapters.PlaceListAdapter;
import com.tourism.app.models.PlaceListModel;

import java.util.ArrayList;
import java.util.List;

public class PlaceListKhulFragment extends Fragment {

    RecyclerView khulListRecycle;
    FirebaseFirestore db;



    //place list recycle
    PlaceListAdapter khulAdapter;
    List<PlaceListModel> khulModelList;


    public PlaceListKhulFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.place_list_khul_frag, container, false);

        db = FirebaseFirestore.getInstance();

        khulListRecycle = root.findViewById(R.id.placelist_khul);

        //place list db
        khulListRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        khulModelList = new ArrayList<>();
        khulAdapter = new PlaceListAdapter(getContext(),khulModelList);

        khulListRecycle.setAdapter(khulAdapter);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlaceListModel rajModel = document.toObject(PlaceListModel.class);
                                if(document.get("division").equals("Khulna")){
                                    khulModelList.add(rajModel);
                                    khulAdapter.notifyDataSetChanged();
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