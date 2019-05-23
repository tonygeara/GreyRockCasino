package com.example.tony.greycasino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;

public class availableJobsrecycler extends AppCompatActivity {

    RecyclerView mRecyclerView;
    String[] Items = {"EVENT SALES & PLANNING MANAGER", "RESTAURANT MANAGER", "POKER DEALER", "COOK","BARTENDER/SERVER","CASHIER/CAGE ATTENDANT"};
    String[] Items2 = { "APPLY TODAY!", "APPLY TODAY!", "APPLY TODAY! NO EXPERIENCE NEEDED! TRAINING OFFERED BY EMPLOYER.","APPLY TODAY!","APPLY TODAY!","APPLY TODAY!"};
    String[] Items3 = { "We are currently looking for an Event Sales & Planning Manager to join our team!", "We are currently looking for servers and bartenders to join our team.",
            "We are currently looking for Poker Dealers to join our team.","We are currently looking for a Cook to join our team.",
            "We are currently looking for servers and bartenders to join our team.","We are currently looking for a Cashier/Cage attendant to join our team."};

String[] Items4  = {"If you are looking to be part of an innovating, vibrant, enthusiastic and motivated team, this is an exciting career opportunity.",
"If you are looking to be part of an innovating and motivated team, this is an exciting career opportunity.",
"If you are looking to be part of an innovating, vibrant, enthusiastic and motivated team, this is an exciting career opportunity",
"If you are looking to be part of an innovating, vibrant, enthusiastic and motivated team, this is an exciting career opportunity",
"If you are looking to be part of an innovating and motivated team, this is an exciting career opportunity.",
        "If you are looking to be part of an innovating, vibrant, enthusiastic team, this is an exciting career opportunity. "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobsrecycleractivity);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerjobs);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new AdapterJobsAvailable(this,Items,Items2,Items3,Items4));

        // To be fixed later for divider
        /* */
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),mRecyclerView.computeHorizontalScrollExtent()
        );

        dividerItemDecoration.setDrawable(
                ContextCompat.getDrawable(getApplicationContext(), R.drawable.divider_10dp)
        );

        Button readmore = (Button)findViewById(R.id.buttonreadmore);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),careersabout.class);
                startActivity(intent);
            }
        });

    }
}
