package com.example.tony.greycasino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class tourismcasino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourismcasino);
        this.setTitle("Grey Rock Casino");


        TextView textView = (TextView) findViewById(R.id.textView15);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}
