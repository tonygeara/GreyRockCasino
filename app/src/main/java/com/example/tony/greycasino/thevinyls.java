package com.example.tony.greycasino;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class thevinyls extends AppCompatActivity implements OnMapReadyCallback {
    TextView textcall;
    public static GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newyear);


    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
