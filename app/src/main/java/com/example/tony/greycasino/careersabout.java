package com.example.tony.greycasino;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class careersabout extends AppCompatActivity implements OnMapReadyCallback {
    public static GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careersabout);

        TextView mail = (TextView)findViewById(R.id.textView42);
        TextView phone = (TextView)findViewById(R.id.textView43);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(v);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(v);
            }
        });

        RecyclerView mRecyclerView;
        String[] Items = {"Please go to our website to apply or go to Careers/Available Jobs","Thank you"};
        String[] Items2 = { "APPLY TODAY!", "APPLY TODAY!"};
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewcareers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new AdapterCareers(this,Items,Items2));

    }
    public void dialContactPhone(View view) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "(506) 735-2820", null)));
    }

    // send an email to the casino
    public void sendEmail(View view) {
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + "Subject:"+ "&body=" + "Please type in your text here , thank you \n Grey Rock Casino " + "&to=" + "info@greyrock-casino.com");
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setBuildingsEnabled(true);
        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(18.0f);

        // Add a marker in Sydney and move the camera
        LatLng greyrock = new LatLng(47.373417  , -68.306244);
        mMap.addMarker(new MarkerOptions().position(greyrock).title("Grey Rock Casino"));
        float zoomlevel = 16.0f;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(greyrock,zoomlevel));
    }

}
