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

        // Inicializa o PackageManager
        PackageManager pm = getPackageManager();
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN, null);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);

// Lista de apps visíveis no launcher
        List<ResolveInfo> launchables = pm.queryIntentActivities(launcherIntent, 0);

        ArrayList<ApplicationInfo> listaAplicativos = new ArrayList<>();

        for (ResolveInfo resolveInfo : launchables) {
            ApplicationInfo appInfo = resolveInfo.activityInfo.applicationInfo;
            if (appInfo != null && appInfo.enabled) {
                listaAplicativos.add(appInfo);
            }
        }

        // Adapter personalizado para mostrar nome e ícone dos apps
        AppAdapter appAdapter = new AppAdapter(this, listaAplicativos);
        listView.setAdapter(appAdapter);

        //Configura tratamento do clique na lista de aplicativos para abrir o aplicativo

        listView.setOnItemClickListener((parent, view, position, id) -> {
            ApplicationInfo appInfo = (ApplicationInfo) parent.getItemAtPosition(position);
            Intent launchIntent = pm.getLaunchIntentForPackage(appInfo.packageName);
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                Log.e("MainActivity", "Não foi possível abrir o aplicativo: " + appInfo.packageName);
            }
        });

    }


}