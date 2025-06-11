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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        btnAll.setOnClickListener(v->{atualizaListagemListView(listaTodosApps());});
        btnLancaveis.setOnClickListener(v->{atualizaListagemListView(listaTodosApps());});

        //Configura  o tratamento de evento para lançar apps da listagem
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Aplicativo aplicativo = (Aplicativo) adapterView.getItemAtPosition(position);
            //recupera uma intenção de lançamento do aplicativo selecionado  por meio do PackageManager
            Intent launchIntent = pm.getLaunchIntentForPackage(aplicativo.packageName);
            //Se a intenção de lançamento não for nula, inicia a atividade do aplicativo
            if (launchIntent != null) {
                startActivity(launchIntent);
            }
        });
        btnAll.performClick();
    }
    public  ArrayList<Aplicativo> listaTodosApps() {
        listaAplicativos.clear(); // Limpa a lista antes de adicionar novos aplicativos
        /* O método getInstalledApplications do PackageManager retorna uma lista de objetos ApplicationInfo
           que representam os aplicativos instalados no dispositivo. Cada ApplicationInfo contém informações sobre o aplicativo,
           como nome, ícone, pacote, etc.

           Mas podemos aplicar um filtro para obter apenas aplicativos específicos, como aplicativos de terceiros ou aplicativos do sistema.
        ria um filtro da lista de applicationInfor por meio do atributo ApplicationInfo.flags
           que pode ser usado para filtrar aplicativos do sistema, aplicativos de terceiros, bibliotecas, etc.
           O filtro GET_META_DATA e GET_SHARED_LIBRARY_FILES são usados para incluir metadados e bibliotecas compartilhadas.

           flags é um conjunto de bits que pode ser usado para filtrar aplicativos com base em suas características.
           Exemplo
              Constante                                 Int decimal       binário
              PackageManager.MATCH_ALL:                0x00000000        00000000 00000000 00000000 00000000
              PackageManager.GET_SHARED_LIBRARY_FILES: 0x00000020        00000000 00000000 00000000 00100000
              PackageManager.GET_META_DATA:            0x00000080        00000000 00000000 00000000 10000000
              PackageManager.GET_ACTIVITIES:           0x00000001        00000000 00000000 00000000 00000001
              PackageManager.GET_SERVICES:             0x00000004        00000000 00000000 00000000 00000100
              PackageManager.FLAG_SYSTEM:              0x00000001        00000000 00000000 00000000 00000001
              ...

              Portanto, o filtro combinado seria:
            app.Flag fosse                  0x00000001        00000000 00000000 00000000 00000001
            &
            (ApplicationInfo.FLAG_SYSTEM)   0x00000001        00000000 00000000 00000000 00000001
            =                               0x00000000        00000000 00000000 00000000 00000000
              Isso significa que estamos filtrando apenas aplicativos de terceiros, ou seja, aqueles que não são parte do sistema operacional.

            Exemplo se quiser incluir aplicativos do sistema, podemos usar:
            int flags = app.flags & (ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP);
            0x00000001 | 0x00000002 = 0x00000003
            Isso incluiria aplicativos do sistema e aplicativos atualizados do sistema.

              Se quisermos incluir todos os aplicativos, podemos usar:
            int flags = app.flags & (ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP | ApplicationInfo.FLAG_INSTALLED);
            0x00000001 | 0x00000002 | 0x00000004 = 0x00000007

         */

        //Recupera a lista de aplicativos instalados inclundo os aplicativos do sistema, bibliotecas e aplicativos de terceiros...


        Intent launcherIntent = new Intent(Intent.ACTION_MAIN, null);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> launchables = pm.queryIntentActivities(launcherIntent, PackageManager.MATCH_ALL);


        Set<String> pacoteLancavel = new HashSet<>();
        for (ResolveInfo info : launchables) {
            pacoteLancavel.add(info.activityInfo.packageName);
        }



        for (ApplicationInfo app : pm.getInstalledApplications(PackageManager.MATCH_ALL)) {
             //verifica se a aplicação tem uma activity que responde a  intenção a Intent.ACTION_MAIN
                // e se o pacote está na lista de pacotes lançávei
            if (!pacoteLancavel.contains(app.packageName)) continue;
                Aplicativo aplicativo = new Aplicativo();
                aplicativo.nome = app.loadLabel(pm).toString();
                aplicativo.resolveInfo = pm.resolveActivity(new Intent(Intent.ACTION_MAIN).setPackage(app.packageName), PackageManager.MATCH_DEFAULT_ONLY);
                aplicativo.packageName = app.packageName;
                aplicativo.icone = app.loadIcon(pm);
                listaAplicativos.add(aplicativo);

        }
        return listaAplicativos;
    }

    public void atualizaListagemListView(ArrayList<Aplicativo> listapps) {
        AppAdapter adapter = new AppAdapter(this, R.layout.app_item, listapps);
        listView.setAdapter(adapter);
    }



}