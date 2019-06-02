package com.example.tony.greycasino;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tony.greycasino.R;
import com.example.tony.greycasino.Events;
import com.example.tony.greycasino.Jobs;
import com.google.firebase.database.annotations.NotNull;
//import com.example.tony.greycasino.utils.toolbar.IBackButtonCallback;

//import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tony Geara
 * on 30/5/19.
 */
public class ActivityEvents extends AppCompatActivity implements AdapterEvent.OnEventClick , IBackButtonCallback {

    String url = "http://www.greyrockcasino.com/en/events";
    ArrayList<Events> allEvents = new ArrayList<>();

    ImageView ivBackArrow;

    RecyclerView rvEvents;
    AdapterEvent adapter;
    @Override
    public void onToolbarBackPressed(@NotNull View view) {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_events);

        rvEvents= findViewById(R.id.rvEvents);
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        adapter =new AdapterEvent(this, allEvents, this);
        rvEvents.setAdapter(adapter);
        ivBackArrow = findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


        Toast.makeText(this, "Events", Toast.LENGTH_SHORT).show();

        new ParserTask().execute();

    }


    @Override
    public void eventClicked(@NotNull Events orderItemModel) {
        Intent intent =new Intent(this , ActivityEventDetails.class);
        intent.putExtra("event", orderItemModel);
        startActivity(intent);
    }


    // parse server for bonanza and badbeat
    private class ParserTask extends AsyncTask<Void, Void, Jobs> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Jobs jobs) {
            super.onPostExecute(jobs);
            if (rvEvents != null && adapter != null)
                adapter.notifyDataSetChanged();
        }
        @Override
        protected Jobs doInBackground(Void... voids) {

            //Execute all the lon running tasks here
            try {

                Document mBlogDocument = Jsoup.connect(url).get();

                Elements mElementDataSize = mBlogDocument.select("article[class=uk-article]");

                for (int i = 0 ; i<mElementDataSize.size(); i++) {

                    Events event =new Events("", "", "", "", "","","","","");
                    event.setTitle(mBlogDocument.select("div[class=uk-align-medium-left]").select("a").eq(i).attr("title"));
                    event.setDetailsLink(mBlogDocument.select("div[class=uk-align-medium-left]").select("a").eq(i).attr("href"));
                    event.setThumbnail(mBlogDocument.select("div[class=uk-align-medium-left]").select("a").select("img").eq(i).attr("src"));

                    event.setEventDate(mBlogDocument.select("div[class=casino-main-content-block]")
                            .select("p[class=uk-article-meta]").select("span[class=custom-date-style]").eq(i).text());

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

