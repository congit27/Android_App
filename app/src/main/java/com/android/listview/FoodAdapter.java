package com.android.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Food> foodList;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView img ;
        TextView txtName;
        TextView txtDescription;
        TextView txtPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();

            holder.txtName = (TextView) convertView.findViewById(R.id.foodName);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.foodDescription);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.foodPrice);
            holder.img = (ImageView) convertView.findViewById(R.id.img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Food food = foodList.get(position);

        holder.txtName.setText(food.getName());
        holder.txtDescription.setText(food.getDescription());
        holder.txtPrice.setText(food.getPrice());
        holder.img.setImageResource(food.getImgIndex());
        convertView.setMinimumHeight(100);

        return convertView;
    }
}
