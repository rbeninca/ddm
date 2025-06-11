package com.ifsc.contaclick;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PackageManager pm;
    ArrayList<Aplicativo> listaAplicativos;
    Button btnAll, btnLancaveis;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        btnAll = findViewById(R.id.btnAllApps);
        btnLancaveis = findViewById(R.id.btnAllLancaveis);
        // Inicializa as listas de aplicativos
        listaAplicativos = new ArrayList<>();

        pm = getPackageManager();

        //informa o objeto de tratamento de eventos de toque os botões
        btnAll.setOnClickListener(v->{atualizaListagemListView(listaLancaveis());});
        btnLancaveis.setOnClickListener(v->{atualizaListagemListView(listaTodosApps());});

        //Configura  o tratamento de evento para lançar apps da listagem
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Aplicativo aplicativo = (Aplicativo) adapterView.getItemAtPosition(position);
            Intent launchIntent = pm.getLaunchIntentForPackage(aplicativo.packageName);
            if (launchIntent != null) {
                startActivity(launchIntent);
            }
        });

    }
    public  ArrayList<Aplicativo> listaTodosApps() {
        listaAplicativos.clear(); // Limpa a lista antes de adicionar novos aplicativos

        //Recupera a lista de aplicativos instalados inclundo os aplicativos do sistema, bibliotecas e aplicativos de terceiros...
        for (ApplicationInfo app : pm.getInstalledApplications(PackageManager.GET_META_DATA)) {
            if ((app.flags & android.content.pm.ApplicationInfo.FLAG_SYSTEM) == 0) {
                Aplicativo aplicativo = new Aplicativo();
                aplicativo.nome = app.loadLabel(pm).toString();
                aplicativo.resolveInfo = pm.resolveActivity(new Intent(Intent.ACTION_MAIN).setPackage(app.packageName), PackageManager.MATCH_DEFAULT_ONLY);
                aplicativo.packageName = app.packageName;
                aplicativo.icone = app.loadIcon(pm);
                listaAplicativos.add(aplicativo);
            }
        }
        return listaAplicativos;
    }
    public ArrayList<Aplicativo> listaLancaveis() {
        listaAplicativos.clear(); // Limpa a lista antes de adicionar novos aplicativos
        //recupera os aplicativos que podem ser lançados
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appsInfo=(ArrayList<ResolveInfo>) pm.queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : appsInfo) {
            Aplicativo aplicativo = new Aplicativo();
            aplicativo.nome = resolveInfo.loadLabel(pm).toString();
            aplicativo.resolveInfo = resolveInfo;
            aplicativo.packageName = resolveInfo.activityInfo.packageName;
            aplicativo.icone = resolveInfo.loadIcon(pm);
            listaAplicativos.add(aplicativo);
        }
        return listaAplicativos;

    }
    public void atualizaListagemListView(ArrayList<Aplicativo> listapps) {
        AppAdapter adapter = new AppAdapter(this, R.layout.app_item, listapps);
        listView.setAdapter(adapter);
    }



}