package com.ifsc.contaclick;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Planeta implements Serializable {
    String nome;
    Integer foto;
    //Bitmap photo;

    public Planeta(String nome, Integer foto) {
        this.nome = nome;
        this.foto = foto;
    }
}
