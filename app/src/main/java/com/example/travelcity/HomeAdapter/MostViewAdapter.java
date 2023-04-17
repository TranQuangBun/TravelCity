package com.example.travelcity.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelcity.R;

import java.util.ArrayList;

public class MostViewAdapter extends RecyclerView.Adapter<MostViewAdapter.MostViewedViewHolder> {
    ArrayList<MostViewHelperClass> mostViewedLocations;
    public MostViewAdapter(ArrayList<MostViewHelperClass> mostViewedLocations) {
        this.mostViewedLocations = mostViewedLocations;
    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design, parent, false);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(view);
        return mostViewedViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewHolder holder, int position) {

        MostViewHelperClass helperClass = mostViewedLocations.get(position);
        holder.imageView.setImageResource(helperClass.getImageView());
        holder.textView.setText(helperClass.getTitleView());
        holder.descView.setText(helperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return mostViewedLocations.size();
    }

    public static class MostViewedViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, descView;
        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.joli_image);
            textView = itemView.findViewById(R.id.joli_title);
            descView = itemView.findViewById(R.id.joli_desc);

        }
    }
}