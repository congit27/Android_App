package com.android.baitapcontentprovide;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView nameContact, phoneContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameContact = (TextView) findViewById(R.id.name);
        phoneContact = (TextView) findViewById(R.id.phone);

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");

        nameContact.setText(name);
        phoneContact.setText(phone);
    }
}
