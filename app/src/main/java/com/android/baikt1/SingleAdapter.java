package com.android.baikt1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.singleViewholder>{
    private List<Single> singles;
    private Context context;

    public SingleAdapter(List<Single> singles, Context context) {
        this.singles = singles;
        this.context = context;
    }

    @NonNull
    @Override
    public singleViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new singleViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull singleViewholder holder, int position) {
        Single single = singles.get(position);

        holder.img.setImageResource(single.getImg());
        holder.name.setText(single.getName());
        holder.stage.setText(single.getStage());
        holder.country.setText(single.getCountry());
        holder.rating.setImageResource(single.getRating());
    }

    @Override
    public int getItemCount() {
        return singles == null? 0 : singles.size();
    }

    public class singleViewholder extends RecyclerView.ViewHolder {
        private TextView name, stage, country;
        private ImageView img, rating;
        public singleViewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            stage = itemView.findViewById(R.id.stage);
            country = itemView.findViewById(R.id.country);

            img = itemView.findViewById(R.id.img);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
