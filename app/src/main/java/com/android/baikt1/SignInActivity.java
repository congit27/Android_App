package com.android.baikt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    EditText inputUname, inputPword;
    Button loginBtn;
    CheckBox rememberCb;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        String uname = "admin";
        String password = "admin";

        inputUname = (EditText) findViewById(R.id.inputUsername);
        inputPword = (EditText) findViewById(R.id.inputPassword);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        rememberCb = (CheckBox) findViewById(R.id.rememberCb);

        // get data remember
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        //  Lấy giá trị
        inputUname.setText(sharedPreferences.getString("username", ""));
        inputPword.setText(sharedPreferences.getString("password", ""));
        rememberCb.setChecked(sharedPreferences.getBoolean("checked", false));

        // Xử lý
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputUname.getText().toString().equals(uname) && inputPword.getText().toString().equals(password)) {
                    if (rememberCb.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", inputUname.getText().toString().trim());
                        editor.putString("password", inputPword.getText().toString().trim());
                        editor.putBoolean("checked", true);
                        editor.commit();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", "");
                        editor.putString("password", "");
                        editor.putBoolean("checked", false);
                        editor.commit();
                    }
                    Toast.makeText(SignInActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignInActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}