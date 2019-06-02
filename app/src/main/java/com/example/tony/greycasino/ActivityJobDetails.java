package com.example.tony.greycasino;


import android.arch.lifecycle.MutableLiveData;;
import android.arch.lifecycle.Observer;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
//import com.example.tony.greycasino.model.Jobs;
//import com.example.tony.greycasino.remote.RemoteDataManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.Intent;
import android.net.Uri;
import com.example.tony.greycasino.R;
import android.content.ActivityNotFoundException;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tony.greycasino.IBackButtonCallback;
import com.example.tony.greycasino.remote.RemoteDataManager;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Tony Geara  on 30/5/19.
 */
public abstract class ActivityJobDetails extends AppCompatActivity implements IBackButtonCallback {

    private int PERMISSIONS_STORAGE = 1006;

    final String BASE_URL = "http://www.greyrockcasino.com";

    CompositeDisposable compositeDisposable =new  CompositeDisposable();

    MutableLiveData<String> mapLocation = new MutableLiveData<String>();

    Jobs selectedJob =new Jobs("", "", "", "", "", "", "", "", "");


    WebView webViewMap;

    TextView tvTitle, tvItemTitle, tvApply, tvDetails;
    TextView tvAddress, tvPhone, tvEmail;

    Button btnDownloadAttachment, btnClickToApply;
    FrameLayout frameProgress;
    ProgressBar progress;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_job_details);
        findViewById(R.id.ivBackArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        if (getIntent() != null)
            selectedJob = getIntent().getParcelableExtra("job");

        webViewMap =   findViewById(R.id.webViewMap);
        webViewMap.getSettings().setJavaScriptEnabled(true);

        new ParserTask().execute();
//        getJobDetails();
        observeMapLink();


        tvTitle = findViewById(R.id.tvTitle);
        tvItemTitle = findViewById(R.id.tvItemTitle);

        tvApply = findViewById(R.id.tvApply);
        tvDetails = findViewById(R.id.tvDetails);


        tvAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);




        btnDownloadAttachment= findViewById(R.id.btnDownloadAttachment);
        btnClickToApply= findViewById(R.id.btnClickToApply);
        frameProgress= findViewById(R.id.frameProgress);
        progress= findViewById(R.id.progress);

        tvTitle.setText( selectedJob.getTitle());
        tvItemTitle.setText( selectedJob.getTitle());
        tvApply.setText(selectedJob.getApply());
        tvDetails.setText(selectedJob.getDetails());



        btnDownloadAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isStoragePermissionsGranted()) {
                    if (selectedJob.getFileLink() != null) {


                        showProgress(true);
                        DisposableSingleObserver<ResponseBody> singleDisposable = new RemoteDataManager().downloadFile(selectedJob.getFileLink())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableSingleObserver<ResponseBody>()
                                {

                                    @Override
                                    public void onSuccess(ResponseBody responseBody) {
                                        Boolean writtenToDisk = writeResponseBodyToDisk(responseBody);

                                        showProgress(false);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        showProgress(false);

                                    }
                                });



                        compositeDisposable.add(singleDisposable);


                    }
                }

            }
        });


        btnClickToApply.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (selectedJob.getEmailAddress() != null) {
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                            emailIntent.setData(Uri.parse(selectedJob.getEmailAddress()));
                            try {
                                startActivity(emailIntent);
                            } catch (ActivityNotFoundException e) {

                            }

                        }
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

            if (webViewMap != null) {
                webViewMap.loadData(selectedJob.getMapsLink(), "text/html", "utf-8");
                tvAddress.setText(Html.fromHtml(selectedJob.getAddressHtml()));
                tvPhone.setText(selectedJob.getPhoneNo());
                tvEmail.setText(selectedJob.getEmailAddress());
            }
        }

        @Override
        protected Jobs doInBackground(Void... voids) {

            //Execute all the lon running tasks here
            try {
                Document mBlogDocument = Jsoup.connect(BASE_URL + selectedJob.getDetailsLink()).get();

                Elements mElementDataSize = mBlogDocument.select("article[class=uk-article]");



                selectedJob.setEmailAddress( mBlogDocument.select("div[class=uk-panel uk-panel-box gros-boutton]")
                        .select("a").eq(0).attr("href") );

                selectedJob.setMapsLink(
                        mBlogDocument.select("div[class=tm-block tm-block-bottom-c tm-block-padding-collapse]")
                                .select("div[class=uk-panel uk-panel-box]").html() );

                selectedJob.setPhoneNo( mBlogDocument.select("div[class=tm-block tm-block-bottom-d]")
                        .select("p").eq(1).text());

                selectedJob.setAddressHtml( mBlogDocument.select("div[class=tm-block tm-block-bottom-d]")
                        .select("p").eq(2).text());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }




    private Boolean writeResponseBodyToDisk(ResponseBody fileBody) {


        String fileName = selectedJob.getTitle() + ".pdf";


        File pdfFile =new  File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);


        try {
            pdfFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {

            OutputStream os = new FileOutputStream(pdfFile);
            os.write(fileBody.bytes());
            System.out.println("Write bytes to file.");

            showProgress(false);

            os.close();

            Intent intent =new Intent(Intent.ACTION_VIEW);
            Uri fileUri = FileProvider.getUriForFile(
                    this,
                    getApplicationContext()
                            .getPackageName() + ".provider", pdfFile);
            intent.setDataAndType(fileUri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }


    private Boolean isStoragePermissionsGranted(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED
                    ) {
                return true;
            } else {
                requestPermissions(new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_STORAGE);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
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



    private void showProgress( Boolean show)
    {
        if (show) {
            progress.setVisibility( View.VISIBLE);
            frameProgress.setVisibility( View.VISIBLE);
        }
        else {
            progress.setVisibility( View.GONE);
            frameProgress.setVisibility( View.GONE);
        }
    }

}
