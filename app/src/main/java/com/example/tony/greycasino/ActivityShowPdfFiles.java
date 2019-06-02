package com.example.tony.greycasino;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.tony.greycasino.R;

/**
 * Created by Tony on 30/5/19.
 */
public class ActivityShowPdfFiles  extends AppCompatActivity {

    String BASE_URL = "http://www.greyrockcasino.com";

    String fileUrl = "";

    ImageView ivBackArrow;
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_web_view);

        webView = findViewById(R.id.webView);
        ivBackArrow = findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fileUrl = BASE_URL+ getIntent().getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);


        webView.setWebViewClient( new WebViewClient());



        webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+fileUrl);
    }
}
