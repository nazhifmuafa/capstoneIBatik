package com.capstoneprodukbangkit.batik.ui.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.capstoneprodukbangkit.batik.MainActivity;
import com.capstoneprodukbangkit.batik.R;
import com.capstoneprodukbangkit.batik.ui.drawer.SampleActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText lUsername, lPassword;
    Button loginButton, registerButton;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lUsername = findViewById(R.id.lUsername);
        lPassword = findViewById(R.id.lPassword);
        loginButton = findViewById(R.id.login_login_button);
        registerButton = findViewById(R.id.login_register_button);
        backButton = findViewById(R.id.login_back_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validatePassword()){

                } else {
                    checkUser();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, RetailerStartActivity.class);
                    startActivity(intent);
            }
        });
    }

    public Boolean validateUsername(){
        String val = lUsername.getText().toString();
        if (val.isEmpty()){
            lUsername.setError("Username cannot be empty!");
            return false;
        } else {
            lUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = lPassword.getText().toString();
        if (val.isEmpty()){
            lPassword.setError("Password cannot be empty!");
            return false;
        } else {
            lPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String checkUsername = lUsername.getText().toString().trim();
        String checkPassword = lPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(checkUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    lUsername.setError(null);
                    String passwordFromDB = snapshot.child(checkUsername).child("password").getValue(String.class);

                    if (!Objects.equals(passwordFromDB, checkPassword)){
                        lUsername.setError(null);
                        Intent intent = new Intent(LoginActivity.this, SampleActivity.class);
                        startActivity(intent);
                    } else {
                        lPassword.setError("Invalid password!");
                        lPassword.requestFocus();
                    }
                } else {
                    lUsername.setError("User doesn't exist!");
                    lUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void callLoginBackButton(View view) {
        Intent intent = new Intent(LoginActivity.this, RetailerStartActivity.class);
        startActivity(intent);
    }

    public void callRegisterButton(View view) {
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }

}
