package com.ifsc.contaclick;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvLatitude, tvLongitude, tvStatus;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLatitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongitude);
        tvStatus = findViewById(R.id.tvStatus);
        Button button= findViewById(R.id.button);
        button.setOnClickListener(v -> {
           getLocalizacao();
        });
        locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    private void getLocalizacao() {
        if (checkAndGetPermissions()) {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                tvLatitude.setText("Latitude: " + location.getLatitude());
                tvLongitude.setText("Longitude: " + location.getLongitude());
            } else {
                tvLatitude.setText("Localização não disponível");
                tvLongitude.setText("Localização não disponível");
            }
        }
    }
    public boolean checkAndGetPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
            return false;
        } else {
            return true;
        }

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PackageManager.PERMISSION_GRANTED) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getLocalizacao();
//            } else {
//                tvLatitude.setText("Permissão Negada");
//                tvLongitude.setText("Permission Negada");
//            }
//        }
//
//    }
}