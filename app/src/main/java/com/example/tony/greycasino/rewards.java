package com.example.tony.greycasino;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class rewards extends AppCompatActivity {
    ImageView image1;
    ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        this.setTitle("Grey Rock Casino");


        image1 = (ImageView)findViewById(R.id.imageView12);
        image2 =(ImageView)findViewById(R.id.imageView15);
        image1.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim));
        image2.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim));

        Button submit = (Button)findViewById(R.id.submitbutt);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"IN PROCESS ....",Toast.LENGTH_LONG).show();

            }
        });
    }
}
