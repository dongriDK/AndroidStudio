package com.example.root.project1;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class AppActivity extends AppCompatActivity {
    ListView listview;
    TextView text;
    ListViewAdapter adapter;
    String requestedPermissions[] = null;
    ArrayList<CustomDTO> DTO_arraylist = new ArrayList<CustomDTO>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        adapter = new ListViewAdapter(DTO_arraylist);
        listview = findViewById(R.id.listView_app);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomDTO DTO = (CustomDTO)parent.getItemAtPosition(position);
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:"+DTO.getApppackage()));
                startActivity(intent);
            }
        });

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        final List pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);

        String packageName = "";
//        String permissionlist = "";
        for (Object obj : pkgAppsList) {
            String test[] = null;
            requestedPermissions=null;
            ResolveInfo resolveInfo = (ResolveInfo) obj;
            PackageInfo packageInfo = null;
            try {
                packageInfo = getPackageManager().getPackageInfo(resolveInfo.activityInfo.packageName, PackageManager.GET_PERMISSIONS);
                requestedPermissions = packageInfo.requestedPermissions;
                packageName = packageInfo.packageName;
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                String date = dateformat.format(new Date(packageInfo.firstInstallTime));
                adapter.addItem(getPackageManager().getApplicationIcon(packageName), (String)getPackageManager().getApplicationLabel(getPackageManager().getApplicationInfo(packageName,PackageManager.GET_UNINSTALLED_PACKAGES)), date, packageName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        Comparator<CustomDTO> textAsc = new Comparator<CustomDTO>() {
            @Override
            public int compare(CustomDTO o1, CustomDTO o2) {
                return o1.getAppname().compareTo(o2.getAppname());
            }
        };

        Collections.sort(DTO_arraylist, textAsc);
        adapter.notifyDataSetChanged();
    }
}
