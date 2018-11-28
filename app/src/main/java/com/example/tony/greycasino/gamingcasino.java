package com.example.tony.greycasino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.ScrollView;
import android.widget.TextView;

public class gamingcasino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamingcasino);

        this.setTitle("Grey Rock Casino");

        TextView textView = (TextView) findViewById(R.id.textView9);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}
