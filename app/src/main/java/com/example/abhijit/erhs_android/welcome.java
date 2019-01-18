package com.example.abhijit.erhs_android;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);







    }



    public void openLoginPage(View view){
        Intent intent = new Intent(this, signin.class);
        startActivity(intent);
    }




    public void openCreateAccount(View view){
        Intent intent = new Intent(this, createAccount.class);
        startActivity(intent);
    }

}



//TODO: go on the xml file for this activity and there's more comments there