package com.ifsc.contaclick;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.*;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView txtCoordenadas;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCoordenadas = findViewById(R.id.txtCoordenadas);
        Button btnGetLocation = findViewById(R.id.btnGetLocation);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //configura a solicitação de localização
        locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
                .setWaitForAccurateLocation(true)

                .build();

        //configura o callback para receber as atualizações de localização  ou objeto LocationResult
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    txtCoordenadas.setText("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
                } else {
                    txtCoordenadas.setText("Localização não disponível");
                }
            }
        };

        //configra o botão para obter a localização
        btnGetLocation.setOnClickListener(v -> getLocation());
    }

    public void getLocation() {
        //checa se a permissão de localização foi concedida usando o ActivityCompat do AndroidX
        //getPackageManager().checkPermission(permission, getPackageName()); equivalente apos api 23
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Permissão de localização negada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}