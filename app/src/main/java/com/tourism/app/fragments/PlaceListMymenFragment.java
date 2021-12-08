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
import com.tourism.app.activity.PlaceListActivity;
import com.tourism.app.adapters.PlaceListAdapter;
import com.tourism.app.models.DivisionModel;
import com.tourism.app.models.PlaceListModel;

import java.util.ArrayList;
import java.util.List;

public class PlaceListMymenFragment extends Fragment {

    RecyclerView mymenListRecycle;
    FirebaseFirestore db;
    DivisionModel divModel = new DivisionModel();

    PlaceListActivity placeActivity;


    //place list recycle
    PlaceListAdapter mymenAdapter;
    List<PlaceListModel> mymenModelList;


    public PlaceListMymenFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.place_list_mym_frag, container, false);

        db = FirebaseFirestore.getInstance();

        mymenListRecycle = root.findViewById(R.id.placelist_mymen);

        //place list db
        mymenListRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        mymenModelList = new ArrayList<>();
        mymenAdapter = new PlaceListAdapter(getContext(),mymenModelList);

        mymenListRecycle.setAdapter(mymenAdapter);


        db.collection("place_db")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                    PlaceListModel mymenModel = document.toObject(PlaceListModel.class);
                                   if(document.get("division").equals("Mymensingh")){
                                       mymenModelList.add(mymenModel);
                                       mymenAdapter.notifyDataSetChanged();
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