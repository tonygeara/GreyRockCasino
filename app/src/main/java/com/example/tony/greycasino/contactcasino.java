package com.example.tony.greycasino;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class contactcasino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactcasino);
        this.setTitle("Grey Rock Casino");

    }

    public void dialContactPhone(View view) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "(506) 735-2820", null)));

    }

    public void sendEmail(View view) {
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + "Subject:"+ "&body=" + "Please type in your text here , thank you \n Grey Rock Casino " + "&to=" + "info@greyrock-casino.com");
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));
    }
}
