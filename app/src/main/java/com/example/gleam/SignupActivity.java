package com.example.gleam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    ImageView backBtn;
    TextView loginTxt;
    EditText editTxtNameSgnUp, editTextTextEmailSgnUp, editTxtPwdSgnUp, editTxtRepwdSgnUp;
    Button btnSgnUp;

    FirebaseAuth auth;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        backBtn = findViewById(R.id.backButtonSgnUp);
        loginTxt = findViewById(R.id.textViewLoginSgn);

        editTxtNameSgnUp = findViewById(R.id.editTxtNameSgnUp);
        editTextTextEmailSgnUp = findViewById(R.id.editTextTextEmailSgnUp);
        editTxtPwdSgnUp = findViewById(R.id.editTxtPwdSgnUp);
        editTxtRepwdSgnUp = findViewById(R.id.editTxtRepwdSgnUp);
        btnSgnUp = findViewById(R.id.buttonSignUp);

        backBtn.setOnClickListener(v -> finish());
        loginTxt.setOnClickListener(v -> finish());

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        btnSgnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, email, pwd, rePwd;

                name = editTxtNameSgnUp.getText().toString();
                email = editTextTextEmailSgnUp.getText().toString();
                pwd = editTxtPwdSgnUp.getText().toString();
                rePwd = editTxtRepwdSgnUp.getText().toString();

                User user = new User();
                user.setName(name);
                user.setEmail(email);

                auth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            database.collection("Users")
                                    .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                }
                            });
                            Toast.makeText(SignupActivity.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}