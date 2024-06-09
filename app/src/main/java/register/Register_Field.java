package register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitedb.MainActivity;
import com.example.sqlitedb.R;
import com.example.sqlitedb.SQLiteHelper;

public class Register_Field extends AppCompatActivity {

    EditText name, email, phone, password;
    Button register_btn;

    String user_name, user_email, user_phone, user_password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_field);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        register_btn = findViewById(R.id.register);
    }

    public void userRegistration(View view) {

        /// getting data from activity_register_field.xml
        user_email = name.getText().toString();
        user_email = email.getText().toString();
        user_phone = phone.getText().toString();
        user_password = password.getText().toString();

        /// creating an object of sqlite db
        SQLiteHelper userDetails = new SQLiteHelper(getApplicationContext());

        /// inserting user details into SQLite DB through the object( userDetail) of SQLiteHelper.java
        boolean checking = userDetails.inserUserData();

        if(checking == false) {

            Toast.makeText(getApplicationContext(), "User Registration failed", Toast.LENGTH_LONG).show();
        }else {

            Toast.makeText(getApplicationContext(), "Successfully user registered", Toast.LENGTH_LONG).show();

            /// moving to Main_Activity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}