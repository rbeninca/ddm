package com.ifsc.contaclick;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppAdapter extends ArrayAdapter<ApplicationInfo> {

    public AppAdapter(MainActivity mainActivity, List<ApplicationInfo> aplicativos) {
        super(mainActivity, R.layout.app_item, aplicativos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView= inflater.inflate(R.layout.app_item, parent, false);
        LinearLayout linearLayoutData= convertView.findViewById(R.id.linearLayoutData);

        ApplicationInfo appinfo = getItem(position);
        HashMap<String, Linha> map = getMapAppPropsValues(appinfo);
        for (String key : map.keySet()) {

            LinearLayout linearLayoutHorizontal = new LinearLayout(getContext());
            linearLayoutHorizontal.setOrientation(LinearLayout.HORIZONTAL);

            TextView textView = new TextView(getContext());
            textView.setText(key+": ");
            textView.setTypeface(null, android.graphics.Typeface.BOLD);
            textView.setPadding(8, 8, 8, 8);

            linearLayoutHorizontal.addView(textView);
            Linha linha = map.get(key);
            linearLayoutHorizontal.addView(linha.view);
            linearLayoutData.addView(linearLayoutHorizontal,0);
        }


        return convertView;
    }

    public class Linha {
        public Linha(String key, Object value, View view) {
            this.key = key;
            this.value = value;
            this.view = view;
            if (value == null) {
                value = "N/A";
            }
            if (view!= null && view instanceof TextView) {
                ((TextView) view).setText(value.toString());
            } else if (view!= null &&  view instanceof ImageView) {
                ((ImageView) view).setImageDrawable((Drawable) value);
            }
        }
        public String key;
        public Object value;
        public View view;

    }
    public HashMap <String,Linha > getMapAppPropsValues(ApplicationInfo applicationInfo){
        HashMap<String, Linha> map = new HashMap<>();

        map.put("icon",new Linha("icon", applicationInfo.loadIcon(getContext().getPackageManager()), new ImageView(getContext())));
        map.put("nameRes", new Linha("nameRes", applicationInfo.loadLabel(getContext().getPackageManager()), new TextView(getContext())));
        map.put("labelRes", new Linha("labelRes", applicationInfo.labelRes, new TextView(getContext())));
        map.put("name", new Linha("name", applicationInfo.name, new TextView(getContext())));
        map.put("packageName", new Linha("packageName", applicationInfo.packageName, new TextView(getContext())));
        map.put("sourceDir",new Linha("sourceDir", applicationInfo.sourceDir, new TextView(getContext())));
        map.put("dataDir", new Linha("dataDir", applicationInfo.dataDir, new TextView(getContext())));
        map.put("uid", new Linha("uid", applicationInfo.uid, new TextView(getContext())));
        map.put("flags", new Linha("flags", applicationInfo.flags, new TextView(getContext())));
        map.put("targetSdkVersion", new Linha("targetSdkVersion", applicationInfo.targetSdkVersion, new TextView(getContext())));

        map.put("icon", new Linha("icon", applicationInfo.loadIcon(getContext().getPackageManager()), new ImageView(getContext())));
        map.put("className", new Linha("className", applicationInfo.className, new TextView(getContext())));
        map.put("processName", new Linha("processName", applicationInfo.processName, new TextView(getContext())));
        map.put("enabled", new Linha ("enabled", applicationInfo.enabled, new TextView(getContext())));
        map.put("flags",new Linha ("flags", String.format("%32s", Integer.toBinaryString(applicationInfo.flags)).replace(' ', '0'), new TextView(getContext())));
        map.put("descriptionRes", new Linha("descriptionRes", applicationInfo.descriptionRes, new TextView(getContext())));
        return map;
    }
    //Allflags for ApplicationInfo
    String[] flags = {
            String.valueOf(ApplicationInfo.FLAG_SYSTEM),
            String.valueOf(ApplicationInfo.FLAG_UPDATED_SYSTEM_APP),
            String.valueOf(ApplicationInfo.FLAG_EXTERNAL_STORAGE),
            String.valueOf(ApplicationInfo.FLAG_SUPPORTS_RTL),
            String.valueOf(ApplicationInfo.FLAG_ALLOW_BACKUP),
            String.valueOf(ApplicationInfo.FLAG_TEST_ONLY),
            String.valueOf(ApplicationInfo.FLAG_HAS_CODE),
            String.valueOf(ApplicationInfo.FLAG_PERSISTENT),
            String.valueOf(ApplicationInfo.FLAG_INSTALLED),
            String.valueOf(ApplicationInfo.FLAG_IS_GAME),

            String.valueOf(ApplicationInfo.FLAG_STOPPED),
            String.valueOf(ApplicationInfo.FLAG_VM_SAFE_MODE),
            String.valueOf(ApplicationInfo.FLAG_HARDWARE_ACCELERATED),
            String.valueOf(ApplicationInfo.FLAG_SUPPORTS_LARGE_SCREENS),
            String.valueOf(ApplicationInfo.FLAG_SUPPORTS_SMALL_SCREENS),
            String.valueOf(ApplicationInfo.FLAG_SUPPORTS_NORMAL_SCREENS),
            String.valueOf(ApplicationInfo.FLAG_SUPPORTS_XLARGE_SCREENS),
            String.valueOf(ApplicationInfo.FLAG_SUSPENDED),

    };

}
