package com.ifsc.contaclick;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter<ApplicationInfo> {
    int mResouce;
    public AppAdapter(@NonNull Context context, int resource, @NonNull List<ApplicationInfo> objects) {
        super(context, resource, objects);
        mResouce=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView=inflater.inflate(mResouce,parent,false);

        ImageView imageView=convertView.findViewById(R.id.imageView);
        TextView tvnome=convertView.findViewById(R.id.textView);
        LinearLayout linearLayoutV =convertView.findViewById(R.id.linearLayoutV);

        ApplicationInfo applicationInfo=getItem(position);

        tvnome.setText(applicationInfo.loadLabel(getContext().getPackageManager()));
        imageView.setImageDrawable(applicationInfo.loadIcon(getContext().getPackageManager()));

        //applicationInfo.packageName
        TextView textView= new TextView(getContext());
        textView.setText(applicationInfo.packageName);
        linearLayoutV.addView(textView);

        //applicationInfo.flags
        textView= new TextView(getContext());

        textView.setText( Integer.toBinaryString(applicationInfo.flags));
        linearLayoutV.addView(textView);



        return convertView;
    }
}
