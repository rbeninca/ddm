package com.ifsc.contaclick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterNota extends ArrayAdapter<Nota> {

    int mResource;
    public AdapterNota(@NonNull Context context, int resource, @NonNull List<Nota> objects) {
        super(context, resource, objects);
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater= LayoutInflater.from(getContext());
        View v=layoutInflater.inflate(mResource,parent,false);

        Nota nota=getItem(position);
        TextView tv=v.findViewById(android.R.id.text1);
        tv.setText(nota.txt);
        return super.getView(position, convertView, parent);
    }
}
