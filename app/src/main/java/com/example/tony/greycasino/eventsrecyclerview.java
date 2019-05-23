package com.example.tony.greycasino;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class eventsrecyclerview extends AppCompatActivity {

    RecyclerView mRecyclerView;
    String[] Items1 = {"NEW YEAR'S EVE GREY ROCK STYLE!","PHILIPPE BOUCHARD","CATHY HUTCH DUO","JAN & GIL PICARD","GISÈLE & MIKE","THE VINYLS","THE GRAND ILLUSION STYX EXPERIENCE","SOUVIENS TOI JOE"};
    String[] Items2 = {"EVENT DATE: MONDAY, 31 DECEMBER 2018 21:30","EVENT DATE: SATURDAY, 01 DECEMBER 2018 20:00","EVENT DATE: SATURDAY, 08 DECEMBER 2018 19:00","EVENT DATE: SATURDAY, 15 DECEMBER 2018 19:00","EVENT DATE: SATURDAY, 22 DECEMBER 2018 19:00","EVENT DATE: SATURDAY, 19 JANUARY 2019 20:30","EVENT DATE: SATURDAY, 30 MARCH 2019 21:00","EVENT DATE: FRIDAY, 03 MAY 2019 21:00"};
    String[] Items3 = {"NEW YEAR'S EVE GREY ROCK STYLE\n FEATURING FAMOUS ",
            "Valley View Restaurant","Valley View Restaurant",
            "Valley View Restaurant",
           "Valley View Restaurant", "Grey Rock Casino presents\n The Vinyls January 19th! A 19+ rock event! "," ","Créé depuis à peine deux ans,\n le spectacle se démarque de toute concurrence\n à commencer par la voix de l'interprète,\n d'une ressemblance troublante \nà celle du chanteur américano-français."};
    int [] Images = {R.drawable.newyeareve,R.drawable.philippe,R.drawable.cathy,R.drawable.jan,R.drawable.giseleetmike,R.drawable.vinyls,R.drawable.illusion,R.drawable.souviens};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        mRecyclerView = (RecyclerView) findViewById(R.id.eventsrecyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new eventsrecycler(this,Items1,Items2,Items3,Images));

    }
    public void called(String data) {
        Intent intent = new Intent(this, JobViewActivity.class);
//        Log.d(TAG, "URl is " + data);
        intent.putExtra("long", data);
        startActivity(intent);
    }
}
