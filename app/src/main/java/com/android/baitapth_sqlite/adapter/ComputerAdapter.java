package com.android.baitapth_sqlite.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.baitapth_sqlite.R;
import com.android.baitapth_sqlite.models.Computer;

import java.util.List;

public class ComputerAdapter extends RecyclerView.Adapter<ComputerAdapter.ComputerViewHolder>{
    private Context context;
    private List<Computer> computers;

    public ComputerAdapter(Context context, List<Computer> computers) {
        this.context = context;
        this.computers = computers;
    }

    @NonNull
    @Override
    public ComputerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.computer_item, null);
        return new ComputerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComputerViewHolder holder, int position) {
        Computer computer = computers.get(position);

        holder.computerName.setText(computer.getName());
        holder.computerPrice.setText(computer.getPrice());
        Bitmap bmImgComputer = BitmapFactory.decodeByteArray(computer.getImg(), 0, computer.getImg().length);
        holder.computerImg.setImageBitmap(bmImgComputer);
    }

    @Override
    public int getItemCount() {
        return (computers != null? computers.size() : 0);
    }

    public class ComputerViewHolder extends RecyclerView.ViewHolder {
        private ImageView computerImg;
        private TextView computerName, computerPrice;

        public ComputerViewHolder(@NonNull View itemView) {
            super(itemView);

            this.computerImg = (ImageView) itemView.findViewById(R.id.computerImg);
            this.computerName = (TextView) itemView.findViewById(R.id.computerName);
            this.computerPrice = (TextView) itemView.findViewById(R.id.computerPrice);
        }
    }
}
