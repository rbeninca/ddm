package com.ifsc.contaclick;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView tvLatitude, tvLongitude, tvStatus;
    LocationManager locationManager;
    private MapView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(getPackageName());
        setContentView(R.layout.activity_main);
        tvLatitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongitude);
        tvStatus = findViewById(R.id.tvStatus);
        Button button= findViewById(R.id.button);
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);



        button.setOnClickListener(v -> {
           getLocalizacao();
        });
        locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);

    }


    @SuppressLint("MissingPermission")
    private void getLocalizacao() {
        if (checkAndGetPermissions()) {
          locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        } else {
            tvStatus.setText("Permissão Negada");
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
    private boolean firstLocationUpdate = true;

    private void showUserLocation(double latitude, double longitude) {
        GeoPoint userLocation = new GeoPoint(latitude, longitude);

        if (firstLocationUpdate) {
            map.getController().setCenter(userLocation);
            map.getController().setZoom(18.0);
            firstLocationUpdate = true;
        }

        Marker marker = new Marker(map);
        marker.setPosition(userLocation);
        marker.setTitle("Você está aqui");
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().clear();
        map.getOverlays().add(marker);

        map.invalidate();
    }
    public final android.location.LocationListener locationListener = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            DecimalFormat decimalFormat = new DecimalFormat("##.######");
            tvLatitude.setText("Latitude: " + decimalFormat.format(location.getLatitude()));
            tvLongitude.setText("Longitude: " + decimalFormat.format(location.getLongitude()));

            tvStatus.setText("Localização Atualizada");
            showUserLocation(location.getLatitude(), location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Implementar se necessário
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            // Implementar se necessário
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            // Implementar se necessário
        }
    };

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