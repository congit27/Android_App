package com.android.intent_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListItemActivity extends AppCompatActivity {
    ListView lvSubject;
    ArrayList<Course> arrayCourse;
    CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcourse);

        mapping();

        adapter = new CourseAdapter(this, R.layout.activity_item, arrayCourse);
        lvSubject.setAdapter(adapter);

        lvSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = arrayCourse.get(position);
                Intent intent = new Intent(ListItemActivity.this, DetailActivity.class);

                intent.putExtra("img", course.getImage());
                intent.putExtra("courseName", course.getName());
                intent.putExtra("detail", course.getDetail());

                startActivity(intent);
            }
        });
    }

    private void mapping() {
        lvSubject = (ListView) findViewById(R.id.lvCourse);
        arrayCourse = new ArrayList<>();

        arrayCourse.add(new Course("React", "ReactJS", R.drawable.react, "React makes it painless to create interactive UIs. Design simple views for each state in your application, and React will efficiently update and render just the right components when your data changes."));
        arrayCourse.add(new Course("IOS", "IOS 10.0", R.drawable.apple, "React makes it painless to create interactive UIs. Design simple views for each state in your application, and React will efficiently update and render just the right components when your data changes."));
        arrayCourse.add(new Course("ASP.NET", "ASP.NET", R.drawable.aspdotnet, "React makes it painless to create interactive UIs. Design simple views for each state in your application, and React will efficiently update and render just the right components when your data changes."));
        arrayCourse.add(new Course("PHP", "PHP", R.drawable.php, "React makes it painless to create interactive UIs. Design simple views for each state in your application, and React will efficiently update and render just the right components when your data changes."));

    }
}
