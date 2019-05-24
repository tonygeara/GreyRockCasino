package com.example.tony.greycasino;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ProgressBar;

import java.util.Objects;

public class splash extends AppCompatActivity {

    ProgressBar progressBar;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        progressBar = findViewById(R.id.progressBar);
        progressBar.drawableHotspotChanged(1,4);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splash.this,MainActivity.class);
                startActivity(mainIntent);

            }
        }, 1000);

        progressBar.drawableHotspotChanged(3,6);

    }



}
