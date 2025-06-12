package com.ifsc.contaclick;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    PackageManager pm;
    ArrayList<ApplicationInfo> listaAplicativos;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        // Inicializa o PackageManager de Context
        pm = this.getPackageManager();
        // Inicializa a lista de aplicativos
        List<ApplicationInfo> applicationInfoList=pm.getInstalledApplications(PackageManager.GET_META_DATA);
        applicationInfoList.forEach(applicationInfo -> {
            Log.d("APP",applicationInfo.toString());
        });
        //Cria um adapter para a lista de aplicativos nos campos da view app_item.xml
        listaAplicativos = new ArrayList<>();
        AppAdapter appAdapter = new AppAdapter(this, applicationInfoList);

        // Inicializa a ListView
        listView.setAdapter(appAdapter);

    }


}