package com.example.abhijit.erhs_android;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class createAccount extends AppCompatActivity {

    ProgressBar progressBar;
    EditText editTextEmail, editTextPassword, nameFirst, nameLast,nameUser, passwordAgain;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_account);


        passwordAgain= (EditText) findViewById(R.id.passwordoncemore);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        //TODO: Create progress bar in xml for this file
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);


        mAuth = FirebaseAuth.getInstance();



        //gradient animation
        ConstraintLayout constraintLayout = findViewById(R.id.createAccount);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();







    }
    private void registerUser() {
        final String firstName = nameFirst.getText().toString().trim();
        final String lastName = nameLast.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String userName = nameUser.getText().toString().trim();
        String passwordagainreenter = passwordAgain.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();




        if (passwordagainreenter == password){
            editTextPassword.setError("Passwords Do Not Match");
            editTextPassword.requestFocus();
            return;
        }

        if (userName.isEmpty()) {
            nameUser.setError("userName is required");
            nameUser.requestFocus();
            return;
        }

        if (firstName.isEmpty()) {
            nameFirst.setError("First Name is Required");
            nameFirst.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            nameLast.setError("Last Name is required");
            nameLast.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {






                    finish();
                    startActivity(new Intent(createAccount.this, signin.class));
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }


   public void beginRegistration(View view) {

        registerUser();
   }







}
