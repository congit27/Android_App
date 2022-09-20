package com.android.listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvFood;
    ArrayList<Food> arrayFood;
    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        lvFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteConfirm(position);

                return true;
            }
        });

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailAcitivy.class);
                startActivity(intent);

            }
        });

        adapter = new FoodAdapter(this, R.layout.list_item, arrayFood);
        lvFood.setAdapter(adapter);
    }

    private void mapping() {
        lvFood = (ListView) findViewById(R.id.lvFood);
        arrayFood = new ArrayList<>();

        arrayFood.add(new Food("Fried rice", "Description of fried rice", "6$", R.drawable.sticky_rice));
        arrayFood.add(new Food("Fried rice", "Description of fried rice", "6$", R.drawable.sticky_rice));
        arrayFood.add(new Food("Fried rice", "Description of fried rice", "6$", R.drawable.sticky_rice));
        arrayFood.add(new Food("Fried rice", "Description of fried rice", "6$", R.drawable.sticky_rice));
        arrayFood.add(new Food("Fried rice", "Description of fried rice", "6$", R.drawable.sticky_rice));
    }

    private void deleteConfirm(int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Xác nhận");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có muốn xóa đơn này không?");

        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayFood.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.show();
    }
}