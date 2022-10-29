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

import com.android.todoapp.models.User;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    EditText inputUname, inputPword;
    CheckBox rememberCb;
    Button loginBtn;
    TextView openSignUpBtn;
    LinearLayout backBtn;
    RequestQueue requestQueue;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mapping();

        // get data remember
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        //  Lấy giá trị
        inputUname.setText(sharedPreferences.getString("username", ""));
        inputPword.setText(sharedPreferences.getString("password", ""));
        rememberCb.setChecked(sharedPreferences.getBoolean("checked", false));

        // Click login button
        handleClickLoginButton();

        // Click open sign up screen btn
        handleClickOpenSignUpBtn();

        // Click back button
        handleClickBackBtn();
    }

    private void mapping() {
        inputUname = (EditText) findViewById(R.id.inputUsername);
        inputPword = (EditText) findViewById(R.id.inputPassword);
        rememberCb = (CheckBox) findViewById(R.id.rememberCb);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        openSignUpBtn = (TextView) findViewById(R.id.openSignUpBtn);
        backBtn = (LinearLayout) findViewById(R.id.backBtn);
    }

    // Handle clicked Login button
    private void handleClickLoginButton() {
        requestQueue = Volley.newRequestQueue(this);
        List<User> users = new ArrayList<>();

        String url = "https://635d232ccb6cf98e56adbf2f.mockapi.io/api/users";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                String id = object.getString("id");
                                String username = object.getString("username");
                                String password = object.getString("password");
                                String name = object.getString("name");
                                String company = object.getString("company");

                                User user  = new User(id, username, password, name, company);
                                users.add(user);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignInActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputUname.getText().toString().equals("") || inputPword.getText().toString().equals("")) {
                    Toast.makeText(SignInActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    for (User user : users ) {
                        if(inputUname.getText().toString().equals(user.getUsername()) && inputPword.getText().toString().equals(user.getPassword())) {
                            if(rememberCb.isChecked()) {
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
                            Intent intent = new Intent(SignInActivity.this, TodoListActivity.class);
                            startActivity(intent);
                            return;
                        }
                    }

                    Toast.makeText(SignInActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Handle clicked Open SignUp button
    private void handleClickOpenSignUpBtn() {
        openSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    // Handle clicked back button
    private void handleClickBackBtn() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
