package com.android.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.todoapp.models.User;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageView showMenuBtn;
    private DrawerLayout drawerLayout;
    LinearLayout backActivityBtn;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Mapping();

        // Xử lí dữ liệu init
        handleInitState();

        // Xử lí clicked vào show menu button
        handleCLickedShowMenuButton();

        // Xử lí click item Navigation
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        // Xử lí click back activity button
        handleClickedBackActivity();

    }

    private void handleInitState() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            Toast.makeText(this, "Không thể lấy dữ liệu!", Toast.LENGTH_SHORT).show();
        }
        user = (User) bundle.get("objectUser");
    }

    private void Mapping() {
        showMenuBtn = (ImageView) findViewById(R.id.showMenuBtn);
        drawerLayout = findViewById(R.id.drawerLayout);
        backActivityBtn = findViewById(R.id.backActivityBtn);
    }

    // Handle clicked back activity button
    private void handleClickedBackActivity() {
        backActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, TodoListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objectUser", user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // Handle Clicked show menu button
    private void handleCLickedShowMenuButton() {
        showMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
    }

    // Handle click item of Navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.navigationHome:

                break;
            case R.id.navigationList:
                Intent intentList = new Intent(ProfileActivity.this, TodoListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objectUser", user);
                intentList.putExtras(bundle);
                startActivity(intentList);
                break;

            case R.id.navigationSettings:

                break;

            case R.id.navigationLogout:
                Intent intentMain = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.END);
        return true;
    }

    // Handle when click back pressed of device with Navigation
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }else {
            super.onBackPressed();
        }
    }

}
