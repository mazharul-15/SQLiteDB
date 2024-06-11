package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserDetails extends AppCompatActivity {

    public int id;
    TextView name, email, phone, password;
    Button updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        name = findViewById(R.id.user_details_name);
        email = findViewById(R.id.user_details_email);
        phone = findViewById(R.id.user_details_phone);
        password = findViewById(R.id.user_details_password);

        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);

        // getting id from intent
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        // getting all data from sqlite DB
        setUserData(id);

        // implement update button
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //implement delete button
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void setUserData(int id) {

        SQLiteHelper db = new SQLiteHelper(getApplicationContext());
        ArrayList<UserModelClass> userData = new ArrayList<>();
        userData = db.userLogInData(id);

        name.setText(userData.get(0).getName());
        email.setText(userData.get(0).getEmail());
        phone.setText(userData.get(0).getPhone());
        password.setText(userData.get(0).getPassword());
    }
}