package com.example.tony.greycasino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class promotionsrecyclerview extends AppCompatActivity {

    RecyclerView mRecyclerView;
    String[] Items = {"POKER SPIN TO WIN THURSDAYS", "HIGH HAND FRIDAYS", "SWEET SATURDAYS", "SUNDAY TOURNAMENTS"};
    String[] Items2 = { "WEEKLY POKER ROOM PROMOTION", "WEEKLY POKER ROOM PROMOTION", "WEEKLY POKER ROOM PROMOTION","WEEKLY POKER ROOM PROMOTION"};
    String[] Items3 = {"Spin for your chance to win in the Poker room every Thursday evening!", "Will you win $50 cash this Friday in the poker room for High Hand Fridays? Join us to be part of the action!",
            "Sweet Saturdays are here! Bounty tournaments, Deepstack tournaments and more!","  "};

    int [] Images = {R.drawable.pokerpromo1, R.drawable.pokerpromo2,R.drawable.pokerpromo3,R.drawable.pokerpromo4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotionsrecyclerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyeclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new Adapter(this,Items,Items2,Items3,Images));
    }
}
