package com.android.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText inputUname, inputPword, inputRePword;
    CheckBox agreeCb;
    Button signUpBtn;
    TextView openSignInBtn;
    LinearLayout backBtn;

    SharedPreferences newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mapping();

        // Click sign up button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickSignUpBtn();
            }
        });

        // Click open sign in screen btn
        openSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickOpenSignInBtn();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickBackBtn();
            }
        });
    }

    private void mapping() {
        inputUname = (EditText) findViewById(R.id.inputUsername);
        inputPword = (EditText) findViewById(R.id.intputPassword);
        inputRePword = (EditText) findViewById(R.id.inputRePassword);
        agreeCb = (CheckBox) findViewById(R.id.agreeCb);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        openSignInBtn = (TextView) findViewById(R.id.openSignInBtn);
        backBtn = (LinearLayout) findViewById(R.id.backBtn);

        newUser = getSharedPreferences("newUser", MODE_PRIVATE);
    }

    private void handleClickSignUpBtn() {
        String username = inputUname.getText().toString().trim();
        String password = inputPword.getText().toString().trim();
        String rePassword = inputRePword.getText().toString().trim();

        if(username.equals("") || password.equals("") || rePassword.equals("")) {
            Toast.makeText(SignUpActivity.this, "Please enter full information!", Toast.LENGTH_SHORT).show();
        } else if(!password.equals(rePassword)) {
            Toast.makeText(SignUpActivity.this, "Password is not same!", Toast.LENGTH_SHORT).show();
        } else if(agreeCb.isChecked() == false) {
            Toast.makeText(SignUpActivity.this, "Please read and agree with rules!", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = newUser.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.commit();

            Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
    }

    private void handleClickOpenSignInBtn() {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    private void handleClickBackBtn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
