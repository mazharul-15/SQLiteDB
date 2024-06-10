package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    EditText user_email, user_password;
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        user_email = findViewById(R.id.login_email);
        user_password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);

        // user details
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// getting user credentials
                String email = user_email.getText().toString();
                String password = user_password.getText().toString();

                SQLiteHelper userLogIn = new SQLiteHelper(getApplicationContext());
                Cursor cursor = userLogIn.userLogInData(email, password);

                // checking valid user or not
                if(cursor.moveToFirst()) {

                    Intent intent = new Intent(getApplicationContext(), UserDetails.class);


                }else {

                    Toast.makeText(LogIn.this, "Invalid User email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}