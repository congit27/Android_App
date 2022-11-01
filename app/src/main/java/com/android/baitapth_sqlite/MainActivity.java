package com.android.baitapth_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.android.baitapth_sqlite.adapter.CategoryAdapter;
import com.android.baitapth_sqlite.adapter.ComputerAdapter;
import com.android.baitapth_sqlite.models.Category;
import com.android.baitapth_sqlite.models.Computer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String DATABASE_NAME = "DB_Manager_Computers.db";
    SQLiteDatabase database;
    private RecyclerView rvCategory, rvComputer;
    List<Category> categories;
    List<Computer> computers;
    CategoryAdapter categoryAdapter;
    ComputerAdapter computerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Database.initDatabase(this, DATABASE_NAME);

        handleComputerRV();
        handleCategoryRV();
    }

    private void handleCategoryRV() {
        rvCategory = (RecyclerView) findViewById(R.id.rvCategory);
        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, categories);
        rvCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCategory.setAdapter(categoryAdapter);

        getDataCategory();
    }

    private void handleComputerRV() {
        rvComputer = (RecyclerView) findViewById(R.id.rvComputer);
        computers = new ArrayList<>();
        computerAdapter = new ComputerAdapter(this, computers);
        rvComputer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvComputer.setAdapter(computerAdapter);

        getDataComputer();
    }

    private void getDataCategory() {

        Cursor cursor = database.rawQuery("Select * from Category", null);
        categories.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            byte[] img = cursor.getBlob(2);


            categories.add(new Category(name, img, id));
        }
    }

    private void getDataComputer() {
        Cursor cursor2 = database.rawQuery("Select * from Computers", null);
        computers.clear();
        for (int i = 0; i < cursor2.getCount(); i++) {
            cursor2.moveToPosition(i);
            String name = cursor2.getString(1);
            String price = cursor2.getString(2);
            byte[] img = cursor2.getBlob(3);
            String categoryId = cursor2.getString(4);


            computers.add(new Computer(name, price, categoryId, img));
        }
    }
}