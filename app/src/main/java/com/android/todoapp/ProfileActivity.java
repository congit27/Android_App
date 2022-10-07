package com.android.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout backBtn;
    ImageView openHomeBtn, openListTodoBtn, openProfileBtn, logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mapping();

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

        openProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickOpenProfileBtn();
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
        backBtn = (LinearLayout) findViewById(R.id.backBtn);
        openHomeBtn = (ImageView) findViewById(R.id.openHomeBtn);
        openListTodoBtn = (ImageView) findViewById(R.id.openListTodoBtn);
        openProfileBtn = (ImageView) findViewById(R.id.openProfileBtn);
        logoutBtn = (ImageView) findViewById(R.id.logoutBtn);
    }

    private void handleClickBackBtn() {
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }

    private void handleClickOpenHomeBtn() {

    }

    private void handleClickOpenListTodoBtn() {
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }

    private void handleClickOpenProfileBtn() {

    }

    private void handleClicklogoutBtn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
