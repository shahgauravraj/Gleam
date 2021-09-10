package com.example.gleam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    ImageView backBtn;
    Button buttonLgn;
    TextView registerTxt;
    EditText editTxtEmailLgn, editTxtPwdLgn;

    FirebaseAuth auth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backBtn = findViewById(R.id.backButton);
        buttonLgn = findViewById(R.id.buttonLoginLgn);
        registerTxt = findViewById(R.id.textViewRegisterLgn);

        editTxtEmailLgn = findViewById(R.id.editTxtEmailLgn);
        editTxtPwdLgn = findViewById(R.id.editTxtPwdLgn);

        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");

        backBtn.setOnClickListener(v -> finish());
        registerTxt.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        buttonLgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String email, pwd;

                email = editTxtEmailLgn.getText().toString();
                pwd = editTxtPwdLgn.getText().toString();

                auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}