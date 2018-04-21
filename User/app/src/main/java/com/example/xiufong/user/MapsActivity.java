package com.example.xiufong.user;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.ic_action_name);

        Bitmap smallMarker = Bitmap.createScaledBitmap(bitmapdraw.getBitmap(), 50, 50, false);

        BitmapDrawable bitmapdrawuserpos = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_action_name2);
        Bitmap smallMarkerUserPos = Bitmap.createScaledBitmap(bitmapdrawuserpos.getBitmap(), 50, 50, false);

        // User
        LatLng userpos = new LatLng(22.337204, 114.259235);
        mMap.addMarker(new MarkerOptions().position(userpos).title("You are here!").icon(BitmapDescriptorFactory.fromBitmap(smallMarkerUserPos)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userpos));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userpos,10));
        //Destination
        LatLng dest = new LatLng(22.315596,114.264393);
        mMap.addMarker(new MarkerOptions().position(dest).title("Destination"));
        //Buses
        LatLng bus = new LatLng(22.329488, 114.263583);
        LatLng bus2 = new LatLng(22.338698, 114.262090);
        mMap.addMarker(new MarkerOptions().position(bus).title("Bus").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.addMarker(new MarkerOptions().position(bus2).title("Bus2").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

    }
}