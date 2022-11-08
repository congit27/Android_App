package com.android.baitap_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText inputCode, inputName;
    private Button addBtn;
    private RecyclerView studentsRV;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        getDataFromRealTimeDB();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = inputCode.getText().toString().trim();
                String name = inputName.getText().toString().trim();
                handleClickAddStudent(new Student(code, name));
            }
        });
    }

    private void handleClickAddStudent(Student student) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_student");

        String pathObject = student.getCode();
        myRef.child(pathObject).setValue(student, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Add success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        inputCode = (EditText) findViewById(R.id.inputCode);
        inputName = (EditText) findViewById(R.id.inputName);
        addBtn = (Button) findViewById(R.id.addStudent);
        studentsRV = findViewById(R.id.studentsRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        studentsRV.setLayoutManager(linearLayoutManager);

        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList);

        studentsRV.setAdapter(studentAdapter);
    }

    private void getDataFromRealTimeDB() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_student");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Student student = dataSnapshot.getValue(Student.class);
                    studentList.add(student);
                }

                studentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}