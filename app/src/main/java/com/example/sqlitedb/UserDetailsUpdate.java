package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserDetailsUpdate extends AppCompatActivity {
    
    private static int id;
    EditText name, email, phone, password;
    Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_update);

        name = findViewById(R.id.name_update);
        email = findViewById(R.id.email_update);
        phone = findViewById(R.id.phone_update);
        password = findViewById(R.id.password_update);

        updateBtn = findViewById(R.id.update_data);

        /// getting  user data
        UserModelClass userData = (UserModelClass) getIntent().getSerializableExtra("userValues");

        // set data
        setData(userData);

        //update implementation
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // data set to object of User Model Class
                userData.setName(name.getText().toString());
                userData.setEmail(email.getText().toString());
                userData.setPhone(phone.getText().toString());
                userData.setPassword(password.getText().toString());
                
                // SQLite DB Object creating
                SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                boolean status = db.updateUserData(userData);

                if(status) {
                    startActivity(new Intent(getApplicationContext(), UserDetails.class).putExtra("id", id));
                }else {
                    Toast.makeText(UserDetailsUpdate.this, "Can't be Updated!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setData(UserModelClass userData) {
        
        id = userData.getId();
        name.setText(userData.getName());
        email.setText(userData.getEmail());
        phone.setText(userData.getPhone());
        password.setText(userData.getPassword());
    }
}