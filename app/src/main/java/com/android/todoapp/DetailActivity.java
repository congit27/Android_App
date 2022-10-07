package com.android.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.android.todoapp.model.Task;

public class DetailActivity extends AppCompatActivity {
    private EditText taskName,deadDate, deadTime, taskNotes;
    LinearLayout backBtn;
    ImageView openHomeBtn, openListTodoBtn, openDetailBtn, logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mapping();
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }

        Task task = (Task) bundle.get("objecTask");
        taskName.setText(task.getTitle());
        deadTime.setText(task.getEndTime());
        taskNotes.setText(task.getDetail());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickBackBtn();
            }
        });


        openHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickOpenHomeBtn();
            }
        });

        openListTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickOpenListTodoBtn();
            }
        });

        openDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickOpenDetailBtn();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicklogoutBtn();
            }
        });
    }

    private void mapping() {
        taskName = (EditText) findViewById(R.id.taskName);
        deadDate = (EditText) findViewById(R.id.deadDate);
        deadTime = (EditText) findViewById(R.id.deadTime);
        taskNotes = (EditText) findViewById(R.id.taskNotes);
        backBtn = (LinearLayout) findViewById(R.id.backBtn);
        openHomeBtn = (ImageView) findViewById(R.id.openHomeBtn);
        openListTodoBtn = (ImageView) findViewById(R.id.openListTodoBtn);
        openDetailBtn = (ImageView) findViewById(R.id.openDetailBtn);
        logoutBtn = (ImageView) findViewById(R.id.logoutBtn);
    }

    private void handleClickBackBtn() {
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }

    private void handleClickOpenHomeBtn() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void handleClickOpenListTodoBtn() {
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }

    private void handleClickOpenDetailBtn() {

    }

    private void handleClicklogoutBtn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
