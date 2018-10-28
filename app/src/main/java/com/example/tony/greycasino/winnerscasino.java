package com.example.tony.greycasino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.MapView;

public class winnerscasino extends AppCompatActivity {

    MapView mMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winnerscasino);
        mMapView = (MapView)findViewById(R.id.mapView);
        
    }
}
