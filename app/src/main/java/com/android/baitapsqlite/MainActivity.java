package com.android.baitapsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.baitapsqlite.models.Student;
import com.android.baitapsqlite.sqlite.DBHelper;
import com.android.baitapsqlite.sqlite.StudentDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button getDataBtn, addDataBtn;
    private ListView studentLv;
    private EditText idEdt, nameEdt, markEdt;
    private StudentAdapter studentAdapter;


    List<Student> listStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        StudentDAO studentDAO = new StudentDAO(this);
        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listStudent = studentDAO.getAll();

                studentAdapter = new StudentAdapter(MainActivity.this, listStudent);
                studentLv.setAdapter(studentAdapter);
            }
        });

        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEdt.getText().toString().trim();
                String name = nameEdt.getText().toString().trim();
                Float mark =  Float.parseFloat(markEdt.getText().toString().trim());

                Student std = new Student(id, name, mark);

                studentDAO.insert(std);
                Toast.makeText(MainActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mapping() {
        getDataBtn = (Button) findViewById(R.id.button);
        addDataBtn = (Button) findViewById(R.id.addBtn);
        studentLv = (ListView) findViewById(R.id.studentLv);
        idEdt = (EditText) findViewById(R.id.idEdt);
        nameEdt = (EditText) findViewById(R.id.nameEdt);
        markEdt = (EditText) findViewById(R.id.markEdt);
    }
}