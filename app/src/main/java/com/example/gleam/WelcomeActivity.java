package com.example.gleam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    Button lgnBtn;
    TextView registerTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        lgnBtn = findViewById(R.id.loginButton);
        registerTxt = findViewById(R.id.registerText);

        lgnBtn.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, LoginActivity.class)));
        registerTxt.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, SignupActivity.class)));
    }
}