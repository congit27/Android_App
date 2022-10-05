package com.android.baitap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userName, passWord;
    TextView loginBtn;
    CheckBox rememberCb;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mapping();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        //  Lấy giá trị
        userName.setText(sharedPreferences.getString("taikhoan", ""));
        passWord.setText(sharedPreferences.getString("matkhau", ""));
        rememberCb.setChecked(sharedPreferences.getBoolean("checked", false));

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userName.getText().toString().trim();
                String password = passWord.getText().toString().trim();

                if(username.equals("admin1") && password.equals("admin")) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

//                     If check ?
                    if(rememberCb.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", username);
                        editor.putString("matkhau", password);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mapping() {
        loginBtn = (TextView) findViewById(R.id.signInBtn) ;
        userName = (EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.password);
        rememberCb = (CheckBox) findViewById(R.id.rememberCheckbox);

    }
}