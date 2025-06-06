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
            insereNota(editText.toString().trim());
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                db.delete("notas","id=?",new String [] {Integer.toString()});
                Toast.makeText(getApplicationContext(),Integer.toString(i),Toast.LENGTH_LONG).show();
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
            int columnid =cursor.getColumnIndex("id");
            int columnTxt =cursor.getColumnIndex("txt");
            //notas.add(cursor.getString(columnTxt).toString());
            int id=cursor.getInt(columnTxt);
            String txt=cursor.getString(columnTxt).toString();
            notas.add (new Nota(id,txt));
            cursor.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                notas
                );
        listView.setAdapter(adapter);
    }
}