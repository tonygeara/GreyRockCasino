package com.example.tony.greycasino;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/*****
 * Created by Tony Geara
 * May 30, 2019
 * Poker Tournaments
 */

class PokerTour extends AppCompatActivity implements AdapterEvent.OnEventClick {

    String url = "http://www.greyrockcasino.com/en/poker/tournament";
    ArrayList<Events> allEvents = new ArrayList<>();

    ImageView ivBackArrow;

    RecyclerView rvEvents;
    AdapterEvent adapter;

    public static String bon = "";
    public static String badb = "";
    TextView textView2;

    static String badddbeat =
            "";


//    @Override
//    public void onToolbarBackPressed(@NotNull View view) {
//        super.onBackPressed();
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, "AIzaSyCT4LZQaP2d_ZLFlF5-nP2v2Z1g30LWwU8");

        setContentView(R.layout.fragments_events2);

        rvEvents = findViewById(R.id.rvEvents);
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterEvent(this, allEvents, this);
        rvEvents.setAdapter(adapter);
//        ivBackArrow = findViewById(R.id.ivBackArrow);
//        ivBackArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//
//            }
//        });
        textView2 = findViewById(R.id.result2234);

        textView2.setText("$" + badb );

        Toast.makeText(this, "Poker Tournaments ... ", Toast.LENGTH_SHORT).show();

        new ParserTask().execute();

    }


    @Override
    public void eventClicked(@NotNull Events orderItemModel) {
        Intent intent = new Intent(this, ActivityEventDetails.class);
        intent.putExtra("event", orderItemModel);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    public void displayResults(Pair<String, String> pair) {

        textView2 = findViewById(R.id.result2234);
        StringBuilder sb = new StringBuilder("Bonanza ");
        sb.append(pair.first).append(" BadBeat ").append(pair.second);


        textView2.setText("$" + badddbeat );
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim));
            }
        });


    }

    // parse server for bonanza and badbeat
    private class ParserTask extends AsyncTask<Void, Void, Jobs> {

        private static final String URL = "http://www.greyrockcasino.com/en/";
        private static final String DIV_BONANZA = "div.top-banner-bingo-content";
        private static final String DIV_BAD_BEAT = "div.top-banner-poker-content";
        private static final int INDEX_CHILD = 1;
        private static final int INDEX_ELEMENT = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Jobs jobs) {
            super.onPostExecute(jobs);
            if (rvEvents != null && adapter != null)
                adapter.notifyDataSetChanged();
            textView2.setText("$" + badddbeat );

        }

        @Override
        protected Jobs doInBackground(Void... voids) {

            //Execute all the lon running tasks here
            try {

                Document document = Jsoup.connect(URL).get();
                // select document
                Elements bonanzaElements = document.select(DIV_BONANZA);
                Elements badBeatElements = document.select(DIV_BAD_BEAT);

                // get the index for each element
                Element bonanzaElement = bonanzaElements.get(INDEX_ELEMENT);
                Element badBeatElement = badBeatElements.get(INDEX_ELEMENT);

                // substring the index for bnanza
                Node bonanza = bonanzaElement.childNode(INDEX_CHILD);
                String bonanzaString = bonanza.toString();
                bonanzaString = bonanzaString.substring(bonanzaString.indexOf('$') + 1, bonanzaString.lastIndexOf('<'));
                bon = bonanzaString;
                Log.i("", "doInBackgroundsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss: " + bon);


                Node badBeat = badBeatElement.childNode(INDEX_CHILD);
                String badBeatString = badBeat.toString();
                badBeatString = badBeatString.substring(badBeatString.indexOf('$') + 1, badBeatString.lastIndexOf('<'));
                badb = badBeatString;
                badddbeat = badb.toString();
                Log.i("", "doInBackgroundsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss: " + badb);


                Document mBlogDocument = Jsoup.connect(url).get();

                Elements mElementDataSize = mBlogDocument.select("article[class=uk-article]");

                for (int i = 0; i < mElementDataSize.size(); i++) {

                    Events event = new Events("", "", "", "", "", "", "", "", "");
                    event.setTitle(mBlogDocument.select("div[class=uk-align-medium-left]").select("a").eq(i).attr("title"));
                    event.setDetailsLink(mBlogDocument.select("div[class=uk-align-medium-left]").select("a").eq(i).attr("href"));
                    event.setThumbnail(mBlogDocument.select("div[class=uk-align-medium-left]").select("a").select("img").eq(i).attr("src"));

                    event.setEventDate(mBlogDocument.select("div[class=casino-main-content-block]").eq(i).select("p").eq(2).text());

                    event.setDetails(mBlogDocument.select("div[class=casino-main-content-block]").eq(i).select("p").eq(1).text());

                    allEvents.add(event);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}