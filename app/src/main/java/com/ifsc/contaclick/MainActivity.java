package com.ifsc.contaclick;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    PackageManager pm;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        pm=getPackageManager();
        List<ApplicationInfo> applicationInfos=pm.getInstalledApplications(PackageManager.MATCH_ALL);


        List<ApplicationInfo> appsFiltrados=new ArrayList<>();
        for (ApplicationInfo app: applicationInfos) {
            if ((app.flags & ApplicationInfo.FLAG_SYSTEM)==0){
                appsFiltrados.add(app);
            }
        }

        AppAdapter adapter =new AppAdapter(this,R.layout.item_app,appsFiltrados);
        listView.setAdapter(adapter);





    }
}