package com.example.tony.greycasino;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;

public class bingowhatshappening extends AppCompatActivity {
    public static String bon = "";
    public static String badb = "";
    TextView textView2;

    ImageView imageView;

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingowhatshappening);
        ParserTask parserTask = new ParserTask();
        parserTask.execute();

//        imageView = (ImageView) findViewById(R.id.imageView3);
//
//
//
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isImageFitToScreen) {
//                    isImageFitToScreen=false;
//                    imageView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
//                    imageView.setAdjustViewBounds(true);
//                }else{
//                    isImageFitToScreen=true;
//                    imageView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT));
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                }
//            }
//        });


    }



    @SuppressLint("SetTextI18n")
    public void displayResults(Pair<String, String> pair) {

        textView2 = findViewById(R.id.resultbingo);
        StringBuilder sb = new StringBuilder("Bonanza ");
        sb.append(pair.first).append(" BadBeat ").append(pair.second);


        textView2.setText("$" + bon);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim));
            }
        });


    }



    // parse server for bonanza and badbeat
    private class ParserTask extends AsyncTask<Void, Void, Pair<String, String>> {

        private static final String URL = "http://www.greyrockcasino.com/en/";
        private static final String DIV_BONANZA = "div.top-banner-bingo-content";
        private static final String DIV_BAD_BEAT = "div.top-banner-poker-content";
        private static final int INDEX_CHILD = 1;
        private static final int INDEX_ELEMENT = 0;


        @Override
        protected Pair<String, String> doInBackground(Void... voids) {

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
                Log.i("", "doInBackgroundsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss: " + badb);


                return new Pair<>(bonanzaString, badBeatString);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Pair<String, String> stringStringPair) {
            super.onPostExecute(stringStringPair);
            displayResults(stringStringPair);
        }
    }
}
