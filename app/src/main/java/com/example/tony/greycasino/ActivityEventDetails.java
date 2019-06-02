package com.example.tony.greycasino;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tony.greycasino.R;
import com.example.tony.greycasino.Events;
import com.example.tony.greycasino.Jobs;
import com.example.tony.greycasino.IBackButtonCallback;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;

;

/**
 * Created by Tony on 30/5/19.
 */
public class ActivityEventDetails extends AppCompatActivity implements IBackButtonCallback {

    private int PERMISSIONS_STORAGE = 1006;

    final String BASE_URL = "http://www.greyrockcasino.com";

    CompositeDisposable compositeDisposable =new  CompositeDisposable();

    MutableLiveData<String> mapLocation = new MutableLiveData<String>();

    Events selectedEvent =new Events("", "", "", "", "","", "","","");


    WebView webViewMap;

    TextView tvTitle, tvItemTitle, tvEventDate, tvDetails;
    TextView tvAddress, tvPhone, tvEmail;

    ImageView ivEvent;
    FrameLayout frameProgress;
    ProgressBar progress;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_details);
        findViewById(R.id.ivBackArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        if (getIntent() != null)
            selectedEvent = getIntent().getParcelableExtra("event");

        webViewMap =   findViewById(R.id.webViewMap);
        webViewMap.getSettings().setJavaScriptEnabled(true);

        new ParserTask().execute();
//        getJobDetails();
        observeMapLink();


        tvTitle = findViewById(R.id.tvTitle);
        tvItemTitle = findViewById(R.id.tvItemTitle);

        tvEventDate = findViewById(R.id.tvEventDate);
        tvDetails = findViewById(R.id.tvDetails);

        tvAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        ivEvent= findViewById(R.id.ivEvent);

        frameProgress= findViewById(R.id.frameProgress);
        progress= findViewById(R.id.progress);

        tvTitle.setText(selectedEvent.getTitle());
        tvItemTitle.setText(selectedEvent.getTitle());
        tvEventDate.setText("EVENT DATE: ${selectedEvent.eventDate}");

        tvDetails.setText(Html.fromHtml(selectedEvent.getDetails()));
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

            if (webViewMap != null) {
                webViewMap.loadData(selectedEvent.getMapsLink(), "text/html", "utf-8");
                tvAddress.setText(Html.fromHtml(selectedEvent.getAddressHtml()));
                tvPhone.setText(selectedEvent.getPhoneNo());
                tvEmail.setText(selectedEvent.getEmail());


            }

            if (selectedEvent.getThumbnail() != null)
                Glide.with(ivEvent).load(selectedEvent.getThumbnail()).into(ivEvent);
        }

        @Override
        protected Jobs doInBackground(Void... voids) {

            //Execute all the lon running tasks here
            try {



                Document mBlogDocument = Jsoup.connect(BASE_URL + selectedEvent.getDetailsLink()).get();

                selectedEvent.setThumbnail(mBlogDocument.select("article[class=uk-article]").eq(0).select("img").attr("src"));
                selectedEvent.setDetails(mBlogDocument.select("div[class=casino-content-block]").eq(0).html());


                selectedEvent.setEmail(mBlogDocument.select("div[class=uk-panel uk-panel-box uk-panel-box-primary]")
                        .select("a").eq(0).text());

                selectedEvent.setMapsLink(mBlogDocument.select("div[class=uk-panel uk-panel-box]").eq(0).html());

                selectedEvent.setPhoneNo(mBlogDocument.select("div[class=uk-panel uk-panel-box uk-panel-box-primary]")
                        .select("p").eq(1).text());

                selectedEvent.setAddressHtml(mBlogDocument.select("div[class=tm-block tm-block-bottom-d]")
                        .select("p").eq(2).text());




            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }






    private void observeMapLink() {

        mapLocation.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String it) {
                if (it != null) {
                    webViewMap.loadData(it, "text/html", "UTF-8");

                }
            }
        });

    }

    @Override
    public void onToolbarBackPressed(@NotNull View view) {
        super.onBackPressed();
    }


    //    override fun onToolbarBackPressed(view: View) {
//        super.onBackPressed()
//    }




}
