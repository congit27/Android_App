package com.android.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.todoapp.model.Task;
import com.android.todoapp.my_interface.IClickItemTaskListener;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {
    private RecyclerView rvTodoslist;
    private ImageButton addItemBtn;
    LinearLayout backBtn;
    ImageView openHomeBtn, openListTodoBtn, openDetailBtn, logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoslist);

        mapping();
        List<Task> tasks = getListTask();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvTodoslist.setLayoutManager(layoutManager);
        rvTodoslist.setHasFixedSize(true);
        rvTodoslist.setAdapter(new TaskDataAdapter(tasks, new IClickItemTaskListener() {
            @Override
            public void onClickItemTask(Task task) {
                handleClickItemRV(task);
            }
        }));

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

            }
        });


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicklogoutBtn();
            }
        });

        // Click add item button
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddItem();
            }
        });
    }

    private void mapping() {
        rvTodoslist = (RecyclerView) findViewById(R.id.rvTodosList);
        addItemBtn = (ImageButton) findViewById(R.id.addItemBtn);
        backBtn = (LinearLayout) findViewById(R.id.backBtn);
        openHomeBtn = (ImageView) findViewById(R.id.openHomeBtn);
        openListTodoBtn = (ImageView) findViewById(R.id.openListTodoBtn);
        openDetailBtn = (ImageView) findViewById(R.id.openDetailBtn);
        logoutBtn = (ImageView) findViewById(R.id.logoutBtn);
    }

    private List<Task> getListTask() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Learn Javascript", "10 AM", "12 AM", "Learn about Promise, Callback. How to uses and purpose!", true));
        tasks.add(new Task("Learn HTML&CSS", "02 PM", "4 PM", "Learn GridView. Understanded and Master that layout!", true));
        tasks.add(new Task("Learn Android", "07 PM", "11 PM", "Research RecyclerView. How to uses and it's purpose.", false));
        tasks.add(new Task("Learn Guitar", "11 PM", "12 PM", "Learn chords. Hit two chords Bm and Fa.", true));
        tasks.add(new Task("Go to bed", "12 PM", "6AM", "Good Night!", false));

        return tasks;
    }

    private void handleAddItem() {

    }

    private void handleClickBackBtn() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void handleClickOpenHomeBtn() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void handleClicklogoutBtn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void handleClickItemRV(Task task) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objecTask", task);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
