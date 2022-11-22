package com.android.baitap_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private EditText inputUsername, inputPassword, confirmPassword;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initUi();
        initListener();
    }

    private void initListener() {
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignUpClick();
            }
        });
    }

    private void handleSignUpClick() {
        String username = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String confirmPass = confirmPassword.getText().toString().trim();
        User user;

        if(
            username.equals("") ||
            password.equals("") ||
            confirmPass.equals("") ) {
            Toast.makeText(SignUpActivity.this, "Vui lòng nhập đầy đủ thông tin! ", Toast.LENGTH_SHORT).show();
            return;
        }  else if(!password.equals(confirmPass)) {
            Toast.makeText(SignUpActivity.this, "Mật khẩu không giống", Toast.LENGTH_SHORT).show();
            return;
        } else {
            user = new User(username, password);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_user");

        String pathObject = user.getUsername();
        myRef.child(pathObject).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initUi() {
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        signupBtn = findViewById(R.id.signupBtn);
        confirmPassword = findViewById(R.id.confirmPassword);
    }
}