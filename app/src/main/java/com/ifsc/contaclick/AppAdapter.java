package com.ifsc.contaclick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter<Aplicativo> {
    int mResouce;
    public AppAdapter(@NonNull Context context, int resource, @NonNull List<Aplicativo> objects) {
        super(context, resource, objects);
        mResouce = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView= inflater.inflate(mResouce, parent, false);


        Aplicativo aplicativo = getItem(position);

        if (aplicativo != null) {
            // Aqui você pode configurar os elementos da view com os dados do aplicativo
            // Por exemplo, definir o nome e o ícone do aplicativo em TextView e ImageView
             TextView nomeTextView = convertView.findViewById(R.id.tvappName);
             ImageView iconeImageView = convertView.findViewById(R.id.ivicon);
             nomeTextView.setText(aplicativo.nome);
             iconeImageView.setImageDrawable(aplicativo.icone);


        }
        return convertView;
    }
}
