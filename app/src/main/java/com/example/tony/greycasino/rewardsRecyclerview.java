package com.example.tony.greycasino;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class rewardsRecyclerview extends AppCompatActivity {

    RecyclerView mRecyclerView;
    String[] Items = {"WINTER BLAST AND YAMAHA NIGHTS", "UNLIMITED MONDAYS", "HOT SEAT DRAWS", "MAGNIFICENT TUESDAYS","BATTLE OF THE BUTTONS","LADIES NIGHT","THURSDAY SPIN TO WIN!","POKER SPIN TO WIN THURSDAYS","HIGH HAND FRIDAYS","SWEET SATURDAYS"};
    String[] Items2 = { "EARN BALLOTS TODAY!", "WEEKLY SLOT TOURNAMENT", "WIN YOUR AGE IN FREE PLAY!","RECEIVE $5 IN TOTEM REWARDS!","WEEKLY SLOT TOURNAMENT","EVERY WEDNESDAY EVENING!","SHARE $4000 IN FREE PLAY","WEEKLY POKER ROOM PROMOTION","WEEKLY POKER ROOM PROMOTION","WEEKLY POKER ROOM PROMOTION"};
    String[] Items3 = {"The next Yamaha Night is Friday,\n November 16!", "Join us for Unlimited Mondays\n Slot Tournament\n\n$2025 in Free Play to be won\n" ,
            " ","Members who are 50+ will\n receive $5 in free play \nand the chance to\n earn $5 food credit!  ","Join us every Wednesday for \nBattle of the Buttons!\nTop Prize of $500 in Free Play",
    "Every Wednesday night is Ladies Night\n at Grey Rock Casino Win up to $500\n in Free Play every hour from 6PM to 9PM\nBonus draw at 10PM for up to $1500\n in Free Play!","Share $4000 in free play every Thursday!","Spin for your chance to win\n in the Poker room every Thursday\n evening!",
    "Will you win $50 cash this Friday\n in the poker room for High Hand Fridays?\n Join us to be part of the action! ","Sweet Saturdays are here!\n Bounty tournaments, Deepstack tournaments\n and more!\n"};

    int [] Images = {R.drawable.winterblast, R.drawable.unlimitedmondays,R.drawable.hotseats,R.drawable.mag,R.drawable.bottle,R.drawable.ladiesnighttt,R.drawable.spinthursday,R.drawable.pokerpromo1,R.drawable.pokerpromo2,R.drawable.pokerpromo3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotionsrecyclerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyeclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new Adapter(this,Items,Items2,Items3,Images));

    }
}