package com.tourism.app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tourism.app.R;
import com.tourism.app.activity.PlaceDetailsActivity;
import com.tourism.app.models.PlaceListModel;

import java.util.List;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {

    private Context context;
    private List<PlaceListModel> list;

    public PlaceListAdapter(Context context, List<PlaceListModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override

    public PlaceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaceListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.placelists,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.placeImg);
        holder.placeName.setText(list.get(position).getName());
        holder.placeAddress.setText(list.get(position).getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlaceDetailsActivity.class);
                intent.putExtra("detailed", list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView placeImg;
        TextView placeName, placeAddress, divisionName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImg = itemView.findViewById(R.id.place_img);
            placeName = itemView.findViewById(R.id.place_name);
            placeAddress = itemView.findViewById(R.id.place_address);
            divisionName = itemView.findViewById(R.id.division_name);
        }
    }
}
