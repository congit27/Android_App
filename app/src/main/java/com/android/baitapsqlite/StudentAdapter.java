package com.android.baitapsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.baitapsqlite.models.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> list;

    public StudentAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.student_item, null);

            TextView tvId = convertView.findViewById(R.id.tvId);
            TextView tvName = convertView.findViewById(R.id.tvName);
            TextView tvMark = convertView.findViewById(R.id.tvMark);

            Student std = list.get(position);
            tvName.setText(std.getName());
            tvId.setText(std.getId());
            tvMark.setText("" + std.getMark());

        }
        return convertView;
    }
}
