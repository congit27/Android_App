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

import com.android.todoapp.model.User;

public class SignInActivity extends AppCompatActivity {
    EditText inputUname, inputPword;
    CheckBox rememberCb;
    Button loginBtn;
    TextView openSignUpBtn;
    SharedPreferences adminUser, newUser;
    LinearLayout backBtn;

    User admin = new User("admin", "admin");
    User user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mapping();
        // Click login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickLoginButton();
            }
        });

        // Click open sign up screen btn
        openSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickOpenSignUpBtn();
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
        inputPword = (EditText) findViewById(R.id.inputPassword);
        rememberCb = (CheckBox) findViewById(R.id.rememberCb);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        openSignUpBtn = (TextView) findViewById(R.id.openSignUpBtn);
        backBtn = (LinearLayout) findViewById(R.id.backBtn);

        // Lấy dữ liệu tài khoản đã có
        adminUser = getSharedPreferences("dataLogin", MODE_PRIVATE);
        inputUname.setText(adminUser.getString("username",""));
        inputPword.setText(adminUser.getString("password", ""));
        rememberCb.setChecked(adminUser.getBoolean("checked", false));

        // Lấy dữ liệu tài khoản đã đăng ký
        newUser = getSharedPreferences("newUser", MODE_PRIVATE);
        user = new User(newUser.getString("username", ""), newUser.getString("password", ""));
    }

    private void handleClickLoginButton() {
        String username = inputUname.getText().toString().trim();
        String password = inputPword.getText().toString().trim();

        if(username.equals("") && password.equals("")) {
            Toast.makeText(SignInActivity.this, "Please enter full information!", Toast.LENGTH_SHORT).show();
        } else if(
                (username.equals(admin.getUsername()) && password.equals(admin.getPassword()))
                ||
                (username.equals(user.getUsername()) && password.equals(user.getPassword()))
        ) {
            if(rememberCb.isChecked()) {
                SharedPreferences.Editor editor = adminUser.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.putBoolean("checked", true);
                editor.commit();
            }
            Intent intent = new Intent(SignInActivity.this, TodoListActivity.class);
            startActivity(intent);
            Toast.makeText(SignInActivity.this, "Login sucessfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SignInActivity.this, "Incorrect account or password!", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleClickOpenSignUpBtn() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void handleClickBackBtn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
