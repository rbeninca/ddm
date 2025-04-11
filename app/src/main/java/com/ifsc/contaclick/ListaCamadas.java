package com.ifsc.contaclick;

import android.graphics.Paint;

import java.util.ArrayList;

public class ListaCamadas {
    /*Classe que define a lista de camadas
    * permitindo controlar a adição e exclusão de cadamas,
    * mandem uma camada como corrente */
    ArrayList<Camada> camadas;
    Camada camadaAtual;
    public ListaCamadas(){
        camadas=new ArrayList<Camada>();
        camadaAtual=new Camada();
        camadas.add(camadaAtual);
    }
    public void addCamada(){
        // Adiciona uma nova camada  com paint igual ao da camada anteriore a lista de camadas e define como corrente
        Camada camada=new Camada(camadaAtual.paint);
        camadaAtual=camada;
        camadas.add(camada);

    }

    public  void removeCamada(Camada camada){
        // Remove uma camada da lista de camadas e define a camada anterior como corrente
        camadas.remove(camada);
        if(camadas.size()>0){
            camadaAtual=camadas.get(camadas.size()-1);
        }else{
            camadaAtual=new Camada();
        }
    }
    public  void limpaCamadas(){
        // remove todas as camadas da lista de camadas e define uma nova camada como corrente
        Paint paint=new Paint(camadaAtual.paint);
        camadas.removeAll(camadas);
        camadaAtual=new Camada(paint);
        camadas.add(camadaAtual);
    }


}
