package com.example.epicierbouha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class signInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button sign_in;
    private Button sign_up;
    private Button ren;

    private static final int RC_SIGN_IN = 123; // You can use any integer value here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sign_in = findViewById(R.id.buttonSignIn);
        sign_up = findViewById(R.id.buttonSignUp);
        ren = findViewById(R.id.buttonForgotPassword);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                login();
            }
        });
        ren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ren_le_mp();
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                signup();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }
        // Check if user is already signed in
        public void signup (){
            String user = ((EditText) findViewById(R.id.editTextUsername)).getText().toString();
            String pass = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
            if (user.isEmpty()) {
                Toast.makeText(signInActivity.this, "entrer un email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pass.isEmpty()) {
                Toast.makeText(signInActivity.this, "entrer un mot de pass", Toast.LENGTH_SHORT).show();
                return;
            }
            System.out.println("before creating user ");
            mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(signInActivity.this, "compte cree avec sucess", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(signInActivity.this, "echec" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            System.out.println("after creating user ");

        }
     public void login(){
         String user = ((EditText) findViewById(R.id.editTextUsername)).getText().toString();
         String pass = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
         if (user.isEmpty()) {
             Toast.makeText(signInActivity.this, "entrer un email", Toast.LENGTH_SHORT).show();
             return;
         }
         if (pass.isEmpty()) {
             Toast.makeText(signInActivity.this, "entrer un mot de pass", Toast.LENGTH_SHORT).show();
             return;
         }
         mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {if (task.isSuccessful()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
             } else {
                 Toast.makeText(signInActivity.this, "echec" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
             }
             }
         });
     }
    public void ren_le_mp(){
        String user=((EditText)findViewById(R.id.editTextUsername)).getText().toString();
        String pass=((EditText)findViewById(R.id.editTextPassword)).getText().toString();
        if(user.isEmpty()){
            Toast.makeText(signInActivity.this,"Entrer une adresse email", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.sendPasswordResetEmail(user).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signInActivity.this,
                                    "Reset email envoyé avec succés", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(signInActivity.this,
                                    "Echec:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }
