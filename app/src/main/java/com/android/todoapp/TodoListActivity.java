package com.android.todoapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Date;


public class TodoListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton addTaskBtn;
    ImageView showMenuBtn;
    LinearLayout backActivityBtn;
    int t1Hour, t1Minute, t2Hour, t2Minute, tYear, tMonth, tDay;
    private DrawerLayout drawerLayout;
    DayScrollDatePicker dayScrollDatePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoslist);

        mapping();

        // Xử lí dayDatePicker
        handleSelectedDateOfTimeline();

        // Xử lí click add task button
        handleClickedAddTaskButton();
        // Xử lí click show menu button
        handleCLickedShowMenuButton();

        // Xử lí click item Navigation
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        // Xử lí click vào back button
        handleClickedBackBtn();
    }

    // Mapping
    private void mapping() {
        addTaskBtn = (ImageButton) findViewById(R.id.addItemBtn);
        drawerLayout = findViewById(R.id.drawerLayout);
        showMenuBtn = (ImageView) findViewById(R.id.showMenuBtn);
        backActivityBtn = (LinearLayout) findViewById(R.id.backActivityBtn);
        dayScrollDatePicker = findViewById(R.id.dayDatePicker);
    }

    //Handle selected date of Timeline
    private void handleSelectedDateOfTimeline() {
        dayScrollDatePicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                String selectedDate = date.toString();
                Toast.makeText(TodoListActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Handle clicked back button
    private void handleClickedBackBtn() {
        backActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodoListActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    // Handle clicked add task button
    private void handleClickedAddTaskButton() {
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        TodoListActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.create_task_box,
                                (LinearLayout) findViewById(R.id.createTaskBox)
                        );
                Button startTimeBtn = bottomSheetView.findViewById(R.id.timer1Btn);
                Button endTimeBtn = bottomSheetView.findViewById(R.id.timer2Btn);
                TextView datepicker = bottomSheetView.findViewById(R.id.datepicker);
                ImageView closeBtn = bottomSheetView.findViewById(R.id.closeBtn);

                // Handle clicked close button
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                // Set default valute for datepicker
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) ;
                int year = calendar.get(Calendar.YEAR);
                datepicker.setText(day + "/" + (month + 1) + "/" + year);

                // Handle clicked start time
                startTimeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                TodoListActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        t1Hour = hourOfDay;
                                        t1Minute = minute;
                                        // Initialize Calender
                                        Calendar calendar = Calendar.getInstance();
                                        // Set hour and minute
                                        calendar.set(0, 0, 0, t1Hour, t1Minute);
                                        // Set time to button
                                        startTimeBtn.setText(DateFormat.format("hh:mm aa", calendar));
                                    }
                                },12,0,false
                        );
                        // Displayed previous selected time
                        timePickerDialog.updateTime(t1Hour, t1Minute);
                        // Show Dialog
                        timePickerDialog.show();
                    }
                });

                // Handle clicked end time
                endTimeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                TodoListActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        t2Hour = hourOfDay;
                                        t2Minute = minute;
                                        // Initialize Calender
                                        Calendar calendar = Calendar.getInstance();
                                        // Set hour and minute
                                        calendar.set(0, 0, 0, t2Hour, t2Minute);
                                        // Set time to button
                                        endTimeBtn.setText(DateFormat.format("hh:mm aa", calendar));
                                    }
                                },12,0,false
                        );
                        // Displayed previous selected time
                        timePickerDialog.updateTime(t1Hour, t1Minute);
                        // Show Dialog
                        timePickerDialog.show();
                    }
                });

                // Handle clicked datepick
                datepicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                TodoListActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        month = month + 1;
                                        datepicker.setText(dayOfMonth + "/" + month + "/" + year);
                                    }
                                }, year, month, day
                        );
                        datePickerDialog.show();
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
    }

    // Handle Clicked show menu button
    private void handleCLickedShowMenuButton() {
        showMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
    }

    // Handle click item of Navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.navigationHome:
                Intent intentHome = new Intent(TodoListActivity.this, ProfileActivity.class);
                startActivity(intentHome);
                break;
            case R.id.navigationList:

                break;

            case R.id.navigationSettings:

                break;

            case R.id.navigationLogout:
                Intent intentMain = new Intent(TodoListActivity.this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.END);
        return true;
    }

    // Handle when click back pressed of device with Navigation
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }else {
            super.onBackPressed();
        }
    }
}
