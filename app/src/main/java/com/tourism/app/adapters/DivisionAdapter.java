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
import com.tourism.app.activity.PlaceListActivity;
import com.tourism.app.models.DivisionModel;

import java.util.List;

public class DivisionAdapter extends RecyclerView.Adapter<DivisionAdapter.ViewHolder> {

    private Context context;
    private List<DivisionModel> list;

    public DivisionAdapter(Context context, List<DivisionModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DivisionAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.divisionlist_activity,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.divImg);
        holder.divName.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlaceListActivity.class);
                intent.putExtra("DivisionName", list.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView divImg;
        TextView divName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            divImg = itemView.findViewById(R.id.div_img);
            divName = itemView.findViewById(R.id.div_name);
        }
    }
}
