package com.example.tony.greycasino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;

class JobViewActivity extends Activity {
    private static final String TAG = "JobViewActivity";
    private static final String BASE_URL = "www.greyrockcasino.com";
    private WebView webView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_view_layout);

        webView = (WebView) findViewById(R.id.webview);

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("long");
        String activity = url;
//        url = BASE_URL + url;
        displayPage(activity);
    }

    private void displayPage(final String act) {
        if (Integer.valueOf(act)  == 0){
            Intent intent = new Intent(getApplicationContext(),newyear.class);
            startActivity(intent);
        }else if(Integer.valueOf(act)  == 1 ) {
            Log.i(TAG, "displayPagedddddddddddddddddddddddddddddddddddddd: " + act);
            Intent intent = new Intent(getApplicationContext(),thevinyls.class);
            startActivity(intent);
        }else if(Integer.valueOf(act)  == 2 ) {
            Log.i(TAG, "displayPagedddddddddddddddddddddddddddddddddddddd: " + act);
            Intent intent = new Intent(getApplicationContext(),thevinyls.class);
            startActivity(intent);
        }
//        Log.d(TAG, "displayPage: " + url);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
    }
}