package com.android.baitap_firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FishAdapter extends RecyclerView.Adapter<FishAdapter.StudentViewHolder>{
    private List<Fish> listFish;

    public FishAdapter(List<Fish> listFish) {
        this.listFish = listFish;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Fish fish = listFish.get(position);
        if(fish == null) {
            return;
        }
        holder.name.setText(fish.getName());
        holder.scienceName.setText(fish.getScienceName());
        holder.characteristic.setText("Đặc tính: " + fish.getCharacteristic());
        holder.color.setText("Màu sắc: " + fish.getColor());
    }

    @Override
    public int getItemCount() {
        if(listFish != null) {
            return listFish.size();
        }
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView name, scienceName, characteristic, color;
        private ImageView img;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            scienceName = itemView.findViewById(R.id.scienceName);
            characteristic = itemView.findViewById(R.id.characteristic);
            color = itemView.findViewById(R.id.color);
            img = itemView.findViewById(R.id.img);
        }
    }
}
