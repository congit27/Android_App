package com.android.intent_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    ImageView image;
    TextView courseName, courseDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        image = (ImageView) findViewById(R.id.img);
        courseName = (TextView) findViewById(R.id.courseName);
        courseDetail = (TextView) findViewById(R.id.detail);

        String name = getIntent().getStringExtra("courseName");
        String detail = getIntent().getStringExtra("detail");
        Bundle bundle = getIntent().getExtras();
        int img = bundle.getInt("img");

        courseName.setText(name);
        courseDetail.setText(detail);
        image.setImageResource(img);
    }
}