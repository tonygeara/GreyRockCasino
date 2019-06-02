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
import android.widget.TextView;
import android.widget.Toast;

import com.example.tony.greycasino.R;
import com.example.tony.greycasino.Jobs;
//import com.example.tony.greycasino.utils.toolbar.IBackButtonCallback;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tony on 30/5/19.
 */
public class ActivityAvailableJobs extends AppCompatActivity implements AdapterJobs.OnJobClick, IBackButtonCallback {

    String url = "http://www.greyrockcasino.com/en/careers";
    ArrayList<Jobs> allJobs = new ArrayList<>();

    ImageView ivBackArrow;
    TextView tvTitle;

    RecyclerView rvJobs;
    AdapterJobs adapter;
    @Override
    public void onToolbarBackPressed(@NotNull View view) {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_available_jobs);

        rvJobs= findViewById(R.id.rvJobs);
        rvJobs.setLayoutManager(new LinearLayoutManager(this));
        adapter =new AdapterJobs(this, allJobs, this);
        rvJobs.setAdapter(adapter);
        tvTitle= findViewById(R.id.tvTitle);
        tvTitle.setText("Jobs");
        ivBackArrow = findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


        Toast.makeText(this, "Jobs", Toast.LENGTH_SHORT).show();

        new ParserTask().execute();

    }

    @Override
    public void jobClicked(@NotNull Jobs orderItemModel) {

        Intent intent =new Intent(this , ActivityJobDetails.class);
        intent.putExtra("job", orderItemModel);
        startActivity(intent);
    }

    @Override
    public void showPdf(@NotNull String url) {

        Intent intent =new Intent(this , ActivityShowPdfFiles.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

//    @Override
//    public fun onToolbarBackPressed() {
//        return null;
//    }


    // parse server for bonanza and badbeat
    private class ParserTask extends AsyncTask<Void, Void, Jobs> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Jobs jobs) {
            super.onPostExecute(jobs);
            if (rvJobs != null && adapter != null)
                adapter.notifyDataSetChanged();
        }
        @Override
        protected Jobs doInBackground(Void... voids) {

            //Execute all the lon running tasks here
            try {

                Document mBlogDocument = Jsoup.connect(url).get();

                Elements mElementDataSize = mBlogDocument.select("article[class=uk-article]");

                for (int i = 0 ; i<mElementDataSize.size(); i++) {

                    Jobs event =new Jobs("", "", "", "", "","","","","");
                    event.setTitle( mBlogDocument.select("div[class=casino-main-content-block]").select("h1[class=uk-article-title]")
                            .select("a").eq(i).attr("title"));

                    event.setDetailsLink(mBlogDocument.select("div[class=casino-main-content-block]").select("h1[class=uk-article-title]")
                            .select("a").eq(i).attr("href"));

                    event.setApply( mBlogDocument.select("div[class=casino-main-content-block]").select("p[class=uk-article-meta]")
                            .eq(i).text());


                    event.setDetails(mBlogDocument.select("div[class=casino-main-content-block]").select("p").eq(i*4+1).text());


                    event.setFileLink(mBlogDocument.select("div[class=casino-main-content-block]")
                            .select("p[class=lire_plus]").select("span[class=uk-button]").select("a").eq(i).attr("href"));


                    allJobs.add(event);
                }



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}

