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

public class PlaceListCtgFragment extends Fragment {

    RecyclerView ctgListRecycle;
    FirebaseFirestore db;


    //place list recycle
    PlaceListAdapter ctgAdapter;

    List<PlaceListModel> ctgModelList;


    public PlaceListCtgFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.place_list_ctg_frag, container, false);

        db = FirebaseFirestore.getInstance();

        ctgListRecycle = root.findViewById(R.id.placelist_ctg);

        //place list db
        ctgListRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        ctgModelList = new ArrayList<>();
        ctgAdapter = new PlaceListAdapter(getContext(),ctgModelList);

        ctgListRecycle.setAdapter(ctgAdapter);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PlaceListModel ctgModel = document.toObject(PlaceListModel.class);
                                if(document.get("division").equals("Chittagong")){
                                    ctgModelList.add(ctgModel);
                                    ctgAdapter.notifyDataSetChanged();
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