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

public class PlaceListRajFragment extends Fragment {

    RecyclerView rajListRecycle;
    FirebaseFirestore db;



    //place list recycle
    PlaceListAdapter rajAdapter;
    List<PlaceListModel> rajModelList;


    public PlaceListRajFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.place_list_raj_frag, container, false);

        db = FirebaseFirestore.getInstance();

        rajListRecycle = root.findViewById(R.id.placelist_raj);

        //place list db
        rajListRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        rajModelList = new ArrayList<>();
        rajAdapter = new PlaceListAdapter(getContext(),rajModelList);

        rajListRecycle.setAdapter(rajAdapter);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlaceListModel rajModel = document.toObject(PlaceListModel.class);
                                if(document.get("division").equals("Rajshahi")){
                                    rajModelList.add(rajModel);
                                    rajAdapter.notifyDataSetChanged();
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