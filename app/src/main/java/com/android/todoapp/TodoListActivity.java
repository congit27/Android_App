package com.android.todoapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.todoapp.models.Task;
import com.android.todoapp.models.User;
import com.android.todoapp.my_interface.IClickItemTaskListener;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class TodoListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton addTaskBtn;
    ImageView showMenuBtn;
    LinearLayout backActivityBtn;
    TextView headingText, dateTV;
    int t1Hour, t1Minute, t2Hour, t2Minute;
    private DrawerLayout drawerLayout;
    DayScrollDatePicker dayScrollDatePicker;
    private RecyclerView rvTasks;
    TaskAdapter taskAdapter;
    List<Task> listTasks;
    private User user;
    private String JSON_URL;
    String currentDay;
    String currentMonth;
    String  currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoslist);

        mapping();

        // Xử lí page
        handleInitState();

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

        // Xử lí RecyclerView
        handleRecyclerViewTasks();

    }

    private void handleInitState() {
        currentDay = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        currentMonth = new SimpleDateFormat("MMM", Locale.getDefault()).format(new Date());
        currentYear = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            Toast.makeText(this, "Không thể lấy dữ liệu!", Toast.LENGTH_SHORT).show();
        }
        user = (User) bundle.get("objectUser");

        JSON_URL = "https://fake-api-todosapp.herokuapp.com/tasks?userId="+user.getId()+"&"+"date="+currentDay+",+"+currentMonth+",+"+currentYear;
        headingText.setText("Hi, " + user.getName());
        dateTV.setText(currentDay + ", " + currentMonth + ", " + currentYear);
    }

    // Mapping
    private void mapping() {
        addTaskBtn = (ImageButton) findViewById(R.id.addItemBtn);
        drawerLayout = findViewById(R.id.drawerLayout);
        showMenuBtn = (ImageView) findViewById(R.id.showMenuBtn);
        backActivityBtn = (LinearLayout) findViewById(R.id.backActivityBtn);
        dayScrollDatePicker = findViewById(R.id.dayDatePicker);
        headingText = (TextView) findViewById(R.id.headingText);
        dateTV = (TextView) findViewById(R.id.dateTV);
        rvTasks = (RecyclerView) findViewById(R.id.rvTasks);
    }

    //Handle selected date of Timeline
    private void handleSelectedDateOfTimeline() {
        dayScrollDatePicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                String currentDay = new SimpleDateFormat("dd", Locale.getDefault()).format(date);
                String currentMonth = new SimpleDateFormat("MMM", Locale.getDefault()).format(date);
                String currentYear = new SimpleDateFormat("yyyy", Locale.getDefault()).format(date);
                Toast.makeText(TodoListActivity.this, currentDay+" " + currentMonth + " " +currentYear, Toast.LENGTH_SHORT).show();

                JSON_URL = "https://fake-api-todosapp.herokuapp.com/tasks?userId="+user.getId()+"&"+"date="+currentDay+",+"+currentMonth+",+"+currentYear;
                dateTV.setText(currentDay+" " + currentMonth + " " +currentYear);
                handleRecyclerViewTasks();
            }
        });
    }

    // Handle clicked back button
    private void handleClickedBackBtn() {
        backActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodoListActivity.this, ProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objectUser", user);
                intent.putExtras(bundle);
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
                Button addTaskBtn = bottomSheetView.findViewById(R.id.addTaskBtn);
                EditText taskTitleInput = bottomSheetView.findViewById(R.id.taskTitleInput);

                // Handle clicked close button
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                // Set default value for datepicker
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) ;
                int year = calendar.get(Calendar.YEAR);
                datepicker.setText(currentDay + ", " + currentMonth + ", " + currentYear);

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
                                        Calendar calendar = Calendar.getInstance();
                                        // Set hour and minute
                                        calendar.set(year, month, dayOfMonth, 0, 0);
                                        datepicker.setText(DateFormat.format("dd, MMM, yyyy", calendar));
                                    }
                                }, year, month, day
                        );
                        datePickerDialog.show();
                    }
                });

                // Handle clicked add task button
                addTaskBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = taskTitleInput.getText().toString().trim();
                        String startTime = startTimeBtn.getText().toString().trim();
                        String endTime = endTimeBtn.getText().toString().trim();
                        String date = datepicker.getText().toString();
                        String userId = user.getId();
                        Task task = new Task(userId, title, startTime, endTime, date, false);

                        String url = "https://fake-api-todosapp.herokuapp.com/tasks";
                        RequestQueue queue = Volley.newRequestQueue(TodoListActivity.this);
                        StringRequest request = new StringRequest(
                            Request.Method.POST,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        handleRecyclerViewTasks();
                                        Toast.makeText(TodoListActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(TodoListActivity.this, "Có lỗi xảy ra khi thêm task!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        ) {
                            @Override
                            protected Map<String, String> getParams() {
                                // below line we are creating a map for storing our values in key and value pair.
                                Map<String, String> params = new HashMap<String, String>();

                                // on below line we are passing our key and value pair to our parameters.
                                params.put("userId", task.getUserId());
                                params.put("title", task.getTitle());
                                params.put("startTime", task.getStartTime());
                                params.put("endTime", task.getEndTime());
                                params.put("date", task.getDate());
                                params.put("state", task.getState().toString());

                                // at last we are returning our params.
                                return params;
                            }
                        };

                        queue.add(request);
                        bottomSheetDialog.dismiss();
                        rvTasks.getLayoutManager().scrollToPosition(listTasks.indexOf(task));
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
                Bundle bundle = new Bundle();
                bundle.putSerializable("objectUser", user);
                intentHome.putExtras(bundle);
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

    
    // Handle get data for task adapter
    private void handleRecyclerViewTasks() {
        listTasks = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                JSON_URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);

                                Task task = new Task();
                                task.setId(object.getString("id").toString());
                                task.setUserId(object.getString("userId").toString());
                                task.setStartTime(object.getString("startTime").toString());
                                task.setEndTime(object.getString("endTime").toString());
                                task.setDate(object.getString("date").toString());
                                task.setTitle(object.getString("title").toString());
                                task.setState(object.getBoolean("state"));

                                listTasks.add(task);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        rvTasks.setLayoutManager(layoutManager);
                        rvTasks.setHasFixedSize(true);
                        taskAdapter = new TaskAdapter(TodoListActivity.this, listTasks, new IClickItemTaskListener() {
                            @Override
                            public void onClickItemTask(Task task) {
                                handleClickItemTasksRV(task);
                            }
                        });
                        rvTasks.setAdapter(taskAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("tag", "OnErrorReponse: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }

    // handle click item of recyclerView tasks
    private void handleClickItemTasksRV(Task task) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                TodoListActivity.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(
                        R.layout.task_controls_box,
                        (LinearLayout) findViewById(R.id.taskControlsBox)
                );

        // mapping
        TextView dateTask  = bottomSheetView.findViewById(R.id.dateTask);
        TextView taskTitle = bottomSheetView.findViewById(R.id.taskTitle);
        ImageView closeBtn = bottomSheetView.findViewById(R.id.closeBtn);
        Button deleteTaskBtn = bottomSheetView.findViewById(R.id.deleteTaskBtn);
        Button copyTaskBtn = bottomSheetView.findViewById(R.id.copyTaskBtn);
        Button updateTaskBtn = bottomSheetView.findViewById(R.id.updateTaskBtn);
        Button setDoneTaskBtn = bottomSheetView.findViewById(R.id.setDoneTaskBtn);


        dateTask.setText(task.getStartTime() + "-" + task.getEndTime() + ", " + task.getDate());
        taskTitle.setText(task.getTitle());

        // Handle clicked done task button
        setDoneTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setState(true);
                taskAdapter.notifyItemChanged(listTasks.indexOf(task));
                bottomSheetDialog.dismiss();
            }
        });

        // Handle clicked delete task item
        deleteTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://fake-api-todosapp.herokuapp.com/tasks/" + task.getId();
                RequestQueue requestQueue = Volley.newRequestQueue(TodoListActivity.this);

                JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.DELETE,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                handleRecyclerViewTasks();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                requestQueue.add(request);
                bottomSheetDialog.dismiss();
            }
        });

        // handle clicked update button
        updateTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        TodoListActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                    .inflate(
                            R.layout.update_task_box,
                            (LinearLayout) findViewById(R.id.updateTaskBox)
                    );

                // Mapping

                Button startTimeBtn = bottomSheetView.findViewById(R.id.timer1Btn);
                Button endTimeBtn = bottomSheetView.findViewById(R.id.timer2Btn);
                TextView datepicker = bottomSheetView.findViewById(R.id.datepicker);
                ImageView closeBtn = bottomSheetView.findViewById(R.id.closeBtn);
                Button saveChangeBtn = bottomSheetView.findViewById(R.id.saveChangeBtn);
                EditText taskTitleInput = bottomSheetView.findViewById(R.id.taskTitleInput);

                taskTitleInput.setText(task.getTitle());
                startTimeBtn.setText(task.getStartTime());
                endTimeBtn.setText(task.getEndTime());

                // Set default value for datepicker
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) ;
                int year = calendar.get(Calendar.YEAR);
                datepicker.setText(currentDay + ", " + currentMonth + ", " + currentYear);

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

                // Handle clicked close button
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                // Handle clicked save change button
                saveChangeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = taskTitleInput.getText().toString().trim();
                        String startTime = startTimeBtn.getText().toString().trim();
                        String endTime = endTimeBtn.getText().toString().trim();
                        String date = datepicker.getText().toString();

                        String url = "https://fake-api-todosapp.herokuapp.com/tasks/"+ task.getId();
                        RequestQueue queue = Volley.newRequestQueue(TodoListActivity.this);

                        StringRequest request = new StringRequest(
                                Request.Method.PUT,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            Toast.makeText(TodoListActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                                            handleRecyclerViewTasks();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(TodoListActivity.this, "Có lỗi xảy ra khi sửa task!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ) {
                            @Override
                            protected Map<String, String> getParams() {

                                // below line we are creating a map for storing
                                // our values in key and value pair.
                                Map<String, String> params = new HashMap<String, String>();

                                // on below line we are passing our key
                                // and value pair to our parameters.
                                params.put("title", title);
                                params.put("startTime", startTime);
                                params.put("endTime", endTime);
                                params.put("date", date);
                                params.put("state", task.getState().toString());
                                params.put("userId", task.getUserId());

                                // at last we are
                                // returning our params.
                                return params;
                            }
                        };
                        queue.add(request);
                        bottomSheetDialog.dismiss();
                        taskTitle.setText(title);
                        rvTasks.getLayoutManager().scrollToPosition(listTasks.indexOf(task));
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        // Handle clicked close button
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

}
