package register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqlitedb.R;

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

        user_email = name.getText().toString();
        user_email = email.getText().toString();
        user_phone = phone.getText().toString();
        user_password = password.getText().toString();
    }
}