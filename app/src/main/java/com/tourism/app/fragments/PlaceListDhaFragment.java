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

public class PlaceListDhaFragment extends Fragment {

    RecyclerView dhaListRecycle;
    FirebaseFirestore db;



    //place list recycle
    PlaceListAdapter dhaAdapter;
    List<PlaceListModel> dhaModelList;


    public PlaceListDhaFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.place_list_dha_frag, container, false);

        db = FirebaseFirestore.getInstance();

        dhaListRecycle = root.findViewById(R.id.placelist_dha);

        //place list db
        dhaListRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        dhaModelList = new ArrayList<>();
        dhaAdapter = new PlaceListAdapter(getContext(),dhaModelList);

        dhaListRecycle.setAdapter(dhaAdapter);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlaceListModel dhaModel = document.toObject(PlaceListModel.class);
                                if(document.get("division").equals("Dhaka")){
                                    dhaModelList.add(dhaModel);
                                    dhaAdapter.notifyDataSetChanged();
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