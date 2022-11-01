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
import com.android.baitapth_sqlite.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryViewHolder>{
    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, null);
        return new categoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewHolder holder, int position) {
        Category category = categories.get(position);

        holder.categoryName.setText(category.getName());
        Bitmap bmImgCategory = BitmapFactory.decodeByteArray(category.getImg(), 0, category.getImg().length);
        holder.categoryImg.setImageBitmap(bmImgCategory);
    }

    @Override
    public int getItemCount() {
        return (categories != null? categories.size() : 0);
    }

    public class categoryViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;
        private ImageView categoryImg;

        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);

            this.categoryName = (TextView) itemView.findViewById(R.id.categoryName);
            this.categoryImg = (ImageView) itemView.findViewById(R.id.cateogryImg);
        }
    }
}
