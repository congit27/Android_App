package com.android.intent_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {
    TextView transferToLoginBtn, signUpBtn;
    TextInputEditText username, password, email, address;
    User newUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        transferToLoginBtn = (TextView) findViewById(R.id.transferToLogin);
        signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        email = (TextInputEditText) findViewById(R.id.email);
        address = (TextInputEditText) findViewById(R.id.address);

        transferToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("") || password.getText().toString().equals("") || email.getText().toString().equals("") || address.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    newUser = new User(username.getText().toString(), password.getText().toString(), email.getText().toString(), address.getText().toString());

                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    intent.putExtra("username",  newUser.getUsername());
                    intent.putExtra("password", newUser.getPassword());
                    startActivity(intent);
                }
            }
        });
    }
}
