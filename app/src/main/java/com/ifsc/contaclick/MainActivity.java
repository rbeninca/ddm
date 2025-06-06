package com.ifsc.contaclick;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button buttonInsere;
    EditText editText;
    ListView listView;
    ArrayList<Nota> notas = new ArrayList<Nota>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Associar view a variaves locais
        buttonInsere=findViewById(R.id.buttonInsere);
        editText=findViewById(R.id.edText);
        listView=findViewById(R.id.listView);

        db=openOrCreateDatabase("minhasnotinhas",MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT , txt TEXT)");
        //Handler tratamento de evento
        buttonInsere.setOnClickListener(v->{
            insereNota(editText.getText().toString());
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Nota n =(Nota)adapterView.getItemAtPosition(i);
                db.delete("notas","id=?",new String [] {Integer.toString(n.id)});
                Toast.makeText(getApplicationContext(),Integer.toString(n.id),Toast.LENGTH_LONG).show();
                carregaNota();
                return false;
            }
        });
        carregaNota();
    }
    public String insereNota(String txt){
        ContentValues cv = new ContentValues();
        cv.put("txt",txt);
        db.insert("notas",null, cv);
        carregaNota();
        return "Nota inserida";
    }
    public void carregaNota(){
        Cursor cursor=db.rawQuery("SELECT * FROM notas ",null);
        cursor.moveToFirst();
        notas.clear();
        while(!cursor.isAfterLast()){
            //Recuperando indice colunas dados
            int columnid =cursor.getColumnIndex("id");
            int columnTxt =cursor.getColumnIndex("txt");
            //Recuperando os dados
            int id=cursor.getInt(columnid);
            String txt=cursor.getString(columnTxt);
            notas.add (new Nota(id,txt));
            cursor.moveToNext();
        }

       AdapterNota adapter =new AdapterNota(getApplicationContext(),
               android.R.layout.simple_list_item_1,
               notas);
        listView.setAdapter(adapter);
    }
}


/*
versão do android WWWW
Android Studio Meerkat Feature Drop | 2024.3.2
Build #AI-243.25659.59.2432.13423653, built on April 29, 2025
Runtime version: 21.0.6+-13368085-b895.109 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Toolkit: sun.awt.windows.WToolkit
Windows 11.0
GC: G1 Young Generation, G1 Concurrent GC, G1 Old Generation
Memory: 2048M
Cores: 20
Registry:
  ide.experimental.ui=true

 */