package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserDetails extends AppCompatActivity {

    public int id;
    UserModelClass userValues = new UserModelClass();
    TextView name, email, phone, password;
    Button logoutBtn, updateBtn, deleteBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        name = findViewById(R.id.user_details_name);
        email = findViewById(R.id.user_details_email);
        phone = findViewById(R.id.user_details_phone);
        password = findViewById(R.id.user_details_password);

        logoutBtn = findViewById(R.id.logout_btn);
        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);

        // getting id from intent
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        // getting all data from sqlite DB
        setUserData(id);


        // implement logout button
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        // implement update button
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), UserDetailsUpdate.class);
                intent.putExtra("userValues", userValues);
                startActivity(intent);
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

        /// assigning to update to User Model Class for passing through Intent
        userValues = userData.get(0);

        name.setText(userData.get(0).getName());
        email.setText(userData.get(0).getEmail());
        phone.setText(userData.get(0).getPhone());
        password.setText(userData.get(0).getPassword());
    }
}