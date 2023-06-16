package com.capstoneprodukbangkit.batik.ui.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.capstoneprodukbangkit.batik.R;

public class RetailerStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_start);
    }

    public void callLoginBtn(View view) {
        Intent intent = new Intent(RetailerStartActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void callSignupBtn(View view) {
        Intent intent = new Intent(RetailerStartActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}
