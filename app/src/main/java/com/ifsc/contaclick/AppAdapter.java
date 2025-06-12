package com.ifsc.contaclick;

import android.content.pm.ApplicationInfo;
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
        ImageView appIcon = convertView.findViewById(R.id.imageView);
        LinearLayout linearLayoutData= convertView.findViewById(R.id.linearLayoutData);

        ApplicationInfo appinfo = getItem(position);
        HashMap<String, Object> map = getMapAppPropsValues(appinfo);
        for (String key : map.keySet()) {

            LinearLayout linearLayoutHorizontal = new LinearLayout(getContext());
            linearLayoutHorizontal.setOrientation(LinearLayout.HORIZONTAL);

            TextView textView = new TextView(getContext());
            textView.setText(key+": ");
            //Set bold textView.setTypeface(null, Typeface.BOLD);
            textView.setTypeface(null, android.graphics.Typeface.BOLD);
            View viewValue ;
            // value map is integer or string viewValue = new TextView(getContext());  If the value is an icon new ImageView(getContext());
            if (map.get(key) instanceof Integer) {
                viewValue = new ImageView(getContext());
                ((ImageView) viewValue).setImageResource((Integer) map.get(key));
            } else {
                viewValue = new TextView(getContext());
                ((TextView) viewValue).setText(map.get(key).toString());
            }
            switch (key) {
                case "labelRes":
                    ((TextView) viewValue).setText(getContext().getString(appinfo.labelRes));
                    break;
                case "name":
                    ((TextView) viewValue).setText(appinfo.loadLabel(getContext().getPackageManager()).toString());
                    break;
                case "packageName":
                    ((TextView) viewValue).setText(appinfo.packageName);
                    break;
                case "sourceDir":
                    ((TextView) viewValue).setText(appinfo.sourceDir);
                    break;
                case "dataDir":
                    ((TextView) viewValue).setText(appinfo.dataDir);
                    break;
                case "uid":
                    ((TextView) viewValue).setText(String.valueOf(appinfo.uid));
                    break;
                case "flags":
                    ((TextView) viewValue).setText(String.valueOf(appinfo.flags));
                    break;
                case "targetSdkVersion":
                    ((TextView) viewValue).setText(String.valueOf(appinfo.targetSdkVersion));
                    break;
                case "versionName":
                    ((TextView) viewValue).setText(appinfo.loadLabel(getContext().getPackageManager()).toString());
                    break;
                case "icon":
                    ((ImageView)viewValue).setImageResource(appinfo.icon);

                    appIcon.setImageResource((Integer) map.get(key));
                    continue; // Skip adding this key to the layout
                default:
                    ((TextView) viewValue).setText(map.get(key)==null?"": map.get(key).toString());
            }

            linearLayoutHorizontal.addView(textView);
            linearLayoutHorizontal.addView(viewValue);
            linearLayoutData.addView(linearLayoutHorizontal,0);
        }


        return convertView;
    }

    public HashMap <String, Object> getMapAppPropsValues(ApplicationInfo applicationInfo){
        HashMap<String, Object> map = new HashMap<>();

        map.put("icon", applicationInfo.icon);
        map.put("labelRes", String.valueOf(applicationInfo.labelRes));
        map.put("name", applicationInfo.loadLabel(getContext().getPackageManager()).toString());
        map.put("packageName", applicationInfo.packageName);
        map.put("sourceDir", applicationInfo.sourceDir);
        map.put("dataDir", applicationInfo.dataDir);
        map.put("uid", String.valueOf(applicationInfo.uid));
        map.put("flags", String.valueOf(applicationInfo.flags));
        map.put("targetSdkVersion", String.valueOf(applicationInfo.targetSdkVersion));
        map.put("versionName", applicationInfo.loadLabel(getContext().getPackageManager()).toString());
        map.put("icon", String.valueOf(applicationInfo.icon));
        map.put("className", applicationInfo.className);
        map.put("taskAffinity", applicationInfo.taskAffinity);
        map.put("processName", applicationInfo.processName);
        map.put("descriptionRes", String.valueOf(applicationInfo.descriptionRes));
        map.put("enabled", String.valueOf(applicationInfo.enabled));
        map.put("flags", String.valueOf(applicationInfo.flags));
        //flags string binary
        map.put("flagsBinary", String.format("%32s", Integer.toBinaryString(applicationInfo.flags)).replace(' ', '0'));



        return map;
    }
}
