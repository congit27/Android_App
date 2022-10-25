package com.android.baitapcontentprovide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.baitapcontentprovide.R;
import com.android.baitapcontentprovide.model.Contact;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Contact> contacts;

    public ContactAdapter(Context context, int layout, List<Contact> contacts) {
        this.context = context;
        this.layout = layout;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return  contacts.size();
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
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            // Ánh xạ View
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.description);
            holder.img = (ImageView) convertView.findViewById(R.id.img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Gán giá trị
        Contact contact = contacts.get(position);

        holder.txtName.setText(contact.getName());
        holder.txtDescription.setText(contact.getPhone());
        holder.img.setImageResource(contact.getImg());

        return convertView;
    }

}
