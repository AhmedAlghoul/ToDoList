package com.example.android.applicationlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    EditText YourName, password, Email;
    Button register;
    TextView login;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth=FirebaseAuth.getInstance();
        YourName = findViewById(R.id.YourName);
        password = findViewById(R.id.editTextTextPassword);
        Email = findViewById(R.id.Email);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ( mAuth.createUserWithEmailAndPassword(Email.getText().toString(),password.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Log.d("123", "onComplete: "+task.getException());
                                    Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}