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
import com.tourism.app.adapters.CategoryAdapter;
import com.tourism.app.adapters.DivisionAdapter;
import com.tourism.app.models.CategoryModel;
import com.tourism.app.models.DivisionModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView catRecycle, divRecycle;
    FirebaseFirestore db;

    //catgory recycle
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    //division recycle
    DivisionAdapter divisionAdapter;
    List<DivisionModel> divisionModelList;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        db = FirebaseFirestore.getInstance();

        catRecycle = root.findViewById(R.id.category_rec);
        divRecycle = root.findViewById(R.id.division_rec);

        //category db
        catRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);
        catRecycle.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {

                        }
                    }
                });

        //division db
        divRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        divisionModelList = new ArrayList<>();
        divisionAdapter = new DivisionAdapter(getContext(),divisionModelList);
        divRecycle.setAdapter(divisionAdapter);

        db.collection("Division")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DivisionModel divisionModel = document.toObject(DivisionModel.class);
                                divisionModelList.add(divisionModel);
                                divisionAdapter.notifyDataSetChanged();
                            }
                        } else {

                        }
                    }
                });

        return root;
    }
}