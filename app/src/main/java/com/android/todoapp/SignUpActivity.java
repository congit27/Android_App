package com.android.todoapp;

import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText inputUname, inputPword, inputRePword, inputName, inputCompany;
    CheckBox agreeCb;
    Button signUpBtn;
    TextView openSignInBtn;
    LinearLayout backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mapping();

        // Click sign up button
        handleClickSignUpBtn();

        // Click open sign in screen btn
        handleClickOpenSignInBtn();

        // Click back button
        handleClickBackBtn();
    }

    private void mapping() {
        inputUname = (EditText) findViewById(R.id.inputUsername);
        inputPword = (EditText) findViewById(R.id.intputPassword);
        inputRePword = (EditText) findViewById(R.id.inputRePassword);
        inputName = (EditText) findViewById(R.id.inputName);
        inputCompany = (EditText) findViewById(R.id.inputCompany);
        agreeCb = (CheckBox) findViewById(R.id.agreeCb);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        openSignInBtn = (TextView) findViewById(R.id.openSignInBtn);
        backBtn = (LinearLayout) findViewById(R.id.backBtn);
    }

    private void handleClickSignUpBtn() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(
                    inputUname.getText().toString().equals("") ||
                    inputPword.getText().toString().equals("") ||
                    inputRePword.getText().toString().equals("") ||
                    inputName.getText().toString().equals("") ||
                    inputCompany.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập đầy đủ thông tin! ", Toast.LENGTH_SHORT).show();
                    return;
                } else if(!agreeCb.isChecked()) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng chọn chấp nhận với chính sách của chúng tôi", Toast.LENGTH_SHORT).show();
                }
                else if(!inputPword.getText().toString().trim().equals(inputRePword.getText().toString().trim())) {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu không giống", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String username = inputUname.getText().toString().trim();
                    String password = inputPword.getText().toString().trim();
                    String name = inputName.getText().toString().trim();
                    String company = inputCompany.getText().toString().trim();
                    User user = new User(username, password, name, company);

                    String url = "https://fake-api-todosapp.herokuapp.com/users";
                    RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);

                    StringRequest request = new StringRequest(
                            Request.Method.POST,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject respObj = new JSONObject(response);
                                        Toast.makeText(SignUpActivity.this, "Hello " + respObj.getString("name"), Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(SignUpActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            // below line we are creating a map for
                            // storing our values in key and value pair.
                            Map<String, String> params = new HashMap<String, String>();

                            // on below line we are passing our key
                            // and value pair to our parameters.
                            params.put("name", user.getName());
                            params.put("company", user.getCompany());
                            params.put("username", user.getUsername());
                            params.put("password", user.getPassword());

                            // at last we are
                            // returning our params.
                            return params;
                        }
                    };
                    Intent intent = new Intent(SignUpActivity.this, TodoListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("objectUser", user);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    queue.add(request);
                }
            }

        });
    }

    // Handle clicked open signin button
    private void handleClickOpenSignInBtn() {
        openSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    // Handle clicked back button
    private void handleClickBackBtn() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
