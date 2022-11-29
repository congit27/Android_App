package com.android.baikt3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.baikt3.my_interface.IClickItemLanguagueListener;

import java.util.List;

public class LanguaguesAdapter extends RecyclerView.Adapter<LanguaguesAdapter.DataViewHolder>{
    private List<Languague> languagues;
    private Context context;
    private IClickItemLanguagueListener iClickItemLanguagueListener;

    public LanguaguesAdapter(List<Languague> languagues, Context context, IClickItemLanguagueListener iClickItemLanguagueListener) {
        this.languagues = languagues;
        this.context = context;
        this.iClickItemLanguagueListener = iClickItemLanguagueListener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_item, null);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Languague languague = languagues.get(position);
        holder.languagueName.setText(languague.getName());
        holder.description.setText(languague.getDescription());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemLanguagueListener.onClickItemLanguague(languague);
            }
        });
    }

    @Override
    public int getItemCount() {
        return languagues == null? 0 : languagues.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutItem;
        private TextView languagueName, description;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);
            languagueName = (TextView) itemView.findViewById(R.id.languagueName);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
