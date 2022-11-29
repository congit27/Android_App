package com.android.baikt3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView languagueName, exampleBox;
    private EditText exampleEdt;
    String example;
    Button saveBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        languagueName = (TextView) findViewById(R.id.languagueName);
        exampleEdt = (EditText) findViewById(R.id.example);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        exampleBox = (TextView) findViewById(R.id.exampleBox);

        SharedPreferences myprefs = getSharedPreferences("mysave",MODE_PRIVATE);
        example = myprefs.getString("ls","");
        exampleBox.setText(example);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ex = exampleEdt.getText().toString();
                example += ex;
                exampleBox.setText(example);
                example += "\n";
            }
        });

        Intent intent = getIntent();
        Languague languague = (Languague) intent.getSerializableExtra("language");
        languagueName.setText(languague.getName());
    }
}
