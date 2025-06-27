package com.ifsc.contaclick;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity  {

    LocationManager lm;
    TextView tvLatitude, tvLongitude;
    Button bnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLatitude=findViewById(R.id.tvLatitude);
        tvLongitude=findViewById(R.id.tvLongitude);
        bnt=findViewById(R.id.button);

        lm=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        bnt.setOnClickListener(v->getLocalizcao());
    }

    public void getLocalizcao() {

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) && (
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)

        ) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    1);
            return;
        }

        Location location;
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        if (location!=null){
            tvLatitude.setText(  Double.toString( location.getLatitude()  ) );
            tvLongitude.setText(  Double.toString( location.getLongitude()  ) );
        } else{
            tvLatitude.setText(  "erro" );
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ( requestCode==1){
            getLocalizcao();
        }
    }
}