package com.example.maps;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int TAG_CODE_PERMISSION_LOCATION = 1;
    private TextView tvGpsLat, tvGpsLong;
    private GoogleMap mMap;
    private ImageButton send,cari;
    private EditText etLat, etLong, etZoom,tempat;
    LinearLayout linearLayout;
    BottomSheetBehavior bottomSheetBehavior;

    private LocationManager locationManager;
    private Location currentLocation;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        linearLayout = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        tvGpsLat= findViewById(R.id.tvGpsLat);
        tvGpsLong = findViewById(R.id.tvGpsLong);

        send = findViewById(R.id.btnGo);
        cari = findViewById(R.id.btnSearch);

        send.setOnClickListener(op);
        cari.setOnClickListener(op);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION },
                        TAG_CODE_PERMISSION_LOCATION);
            }
        }

        currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        tvGpsLong.setText(currentLocation.getLongitude()+"");
        tvGpsLat.setText(currentLocation.getLatitude()+"");

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                currentLocation = location;
                tvGpsLat.setText(currentLocation.getLatitude() + "");
                tvGpsLong.setText(currentLocation.getLongitude() + "");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200, 10, locationListener);
    }

    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnGo:
                    hideKeyboard(v);
                    gotoLocation();
                    break;
                case R.id.btnSearch:
                    goCari();
                    break;
            }

        }
    };

    private void goCari(){
        tempat = findViewById(R.id.etAlamat);

        Geocoder g = new Geocoder(getBaseContext());
        try {
            List<android.location.Address> daftar = g.getFromLocationName(tempat.getText().toString(), 1);
            Address alamat = daftar.get(0);
            String nemuAlamat = alamat.getAddressLine(0);
            Double lintang = alamat.getLatitude();
            Double bujur = alamat.getLongitude();

            Toast.makeText(getBaseContext(),"ketemu" + nemuAlamat,Toast.LENGTH_SHORT).show();

            etZoom = (EditText) findViewById(R.id.etZoom);
            Float dblzoom = Float.parseFloat(etZoom.getText().toString());
            Toast.makeText(getBaseContext(), "Move to" + nemuAlamat +"lat: " +lintang+ "long: " + bujur, Toast.LENGTH_LONG).show();
            gotoPeta(lintang,bujur,dblzoom);


            EditText lat = (EditText) findViewById(R.id.etLat);
            EditText lng = (EditText) findViewById(R.id.etLong);
            Double dbllat = Double.parseDouble(etLat.getText().toString());
            Double dbllng = Double.parseDouble(etLong.getText().toString());
            hitungJarak(dbllat,dbllng,lintang,bujur);

            lat.setText(lintang.toString());
            lng.setText(bujur.toString());
        } catch(IOException e){
            e.printStackTrace();
        }


    }

    private void hitungJarak(Double latAsal, Double lngAsal, Double latTujuan, Double lngTujuan) {
        Location asal = new Location("asal");
        Location tujuan = new Location("tujuan");
        tujuan.setLatitude(latTujuan);
        tujuan.setLatitude(lngTujuan);
        asal.setLatitude(latAsal);
        asal.setLongitude(lngTujuan);
        float jarak = (float) asal.distanceTo(tujuan)/1000;
        String jaraknya = String.valueOf(jarak);
        Toast.makeText(getBaseContext(), "jarak : " + jaraknya +" km ",Toast.LENGTH_LONG).show();
    }

    private void gotoLocation() {
        etLat = findViewById(R.id.etLat);
        etLong = findViewById(R.id.etLong);
        etZoom = findViewById(R.id.etZoom);

        Double dbllat = Double.parseDouble(etLat.getText().toString());
        Double dbllng = Double.parseDouble(etLong.getText().toString());
        Float dblzoom = Float.parseFloat(etZoom.getText().toString());
        Toast.makeText(this,"Move to Lat:" +dbllat + " Long:" +dbllng, Toast.LENGTH_LONG).show();
        gotoPeta(dbllat, dbllng, dblzoom);

    }

    private void gotoPeta(Double lat, Double lng, float z) {
        LatLng Lokasibaru = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().position(Lokasibaru).title("Marker in "+lat +":" +lng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lokasibaru,z));
    };

    private void hideKeyboard(View v) {
        InputMethodManager a = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        a.hideSoftInputFromWindow(v.getWindowToken(),0);
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

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setMyLocationEnabled(true);
    }
}
