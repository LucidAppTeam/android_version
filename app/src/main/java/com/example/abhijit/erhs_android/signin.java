package com.example.abhijit.erhs_android;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signin extends AppCompatActivity {

    private FirebaseAuth mAuth;

    boolean clicked = false;
    Button signInButton;
    TextView nameUser, emailSignIn, passwordSignIn,passwordAgain;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Firebase sign in
        mAuth = FirebaseAuth.getInstance();
        nameUser = (EditText) findViewById(R.id.username);
        passwordSignIn = (EditText) findViewById(R.id.passwordLogin);
        emailSignIn = (EditText) findViewById(R.id.emailLogin);
        signInButton = findViewById(R.id.signinbutton);
        signInNow();

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin);

        //gradient animation
        ConstraintLayout constraintLayout = findViewById(R.id.signin);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();









    }
    public void resetUsername(View view){
        Intent intent = new Intent(this, resetUsername.class);
        startActivity(intent);

    }



    private void signInNow() {

        if (passwordSignIn.getText() == passwordAgain.getText()) {
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicked = true;
                    loginFirebase();
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
        }

    }
    private void loginFirebase () {
        if (clicked) {
            //Get text of text field
            final String email = emailSignIn.getText().toString().trim();
            final String password = passwordSignIn.getText().toString().trim();


            if (email.isEmpty()) {
                emailSignIn.setError("Email is required");
                emailSignIn.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                passwordSignIn.setError("Password is Required");
                passwordSignIn.requestFocus();
                return;
            }

            if (email.length() > 0 && password.length() >0) {

                //code to attempt authentication with Firebase
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    finish();
                                    startActivity(new Intent(signin.this, signin.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Login failed, please recheck username and password", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            } else {
                Toast.makeText(getApplicationContext(), "Login failed, please recheck username and password", Toast.LENGTH_SHORT).show();
            }
        }


    }









}


