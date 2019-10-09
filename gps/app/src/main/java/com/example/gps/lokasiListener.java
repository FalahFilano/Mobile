package com.example.gps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class lokasiListener implements LocationListener {
    private TextView txtLat, txtLong;
    @Override
    public void onLocationChanged(Location location) {
        txtLat =
        txtLong = findViewById(R.id.txtLong);
        txtLat.setText(String.valueOf(location.getLatitude()));
        txtLong.setText(String.valueOf(location.getLongitude()));
        Toast.makeText(, "", Toast.LENGTH_SHORT).show();
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
}
