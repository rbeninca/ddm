package com.ifsc.contaclick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    DAOPlaneta daoPlaneta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.listview);


//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                nomes);
        daoPlaneta= new DAOPlaneta();
        PlanetaAdapter planetaAdapter = new PlanetaAdapter( this,
                                            R.layout.item_lista,
                                              daoPlaneta.listplanetas);
        lv.setAdapter(planetaAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),ActivityB.class);
                //Setando objeto serializado no bundle
                intent.putExtra("planeta",daoPlaneta.listplanetas.get(position));

                startActivity(intent);

            }
        });




    }


}