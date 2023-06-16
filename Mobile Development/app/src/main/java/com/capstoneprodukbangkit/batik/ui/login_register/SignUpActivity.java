package com.capstoneprodukbangkit.batik.ui.login_register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.capstoneprodukbangkit.batik.R;
import com.capstoneprodukbangkit.batik.model.HelperLR;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    EditText rName, rUsername, rEmail, rPassword, rPhone;
    Button button;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        rName = findViewById(R.id.rName);
        rUsername = findViewById(R.id.rUsername);
        rEmail = findViewById(R.id.rEmail);
        rPassword = findViewById(R.id.rPassword);
        rPhone = findViewById(R.id.rPhone);
        button = findViewById(R.id.signup_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = rName.getText().toString();
                String username = rUsername.getText().toString();
                String email = rEmail.getText().toString();
                String password = rPassword.getText().toString();
                String phone = rPhone.getText().toString();

                HelperLR helperLR = new HelperLR(name, username, email, password, phone);
                reference.child(name).setValue(helperLR);
                Toast.makeText(SignUpActivity.this, "Account Create Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void callSignupBack(View view) {
        Intent intent = new Intent(SignUpActivity.this, RetailerStartActivity.class);
        startActivity(intent);
    }
}
