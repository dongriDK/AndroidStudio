package com.example.root.project1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    CustomDTO DTO;

    private ArrayList<CustomDTO> listviewitemlist = new ArrayList<CustomDTO>();

    public ListViewAdapter(ArrayList<CustomDTO> itemlist) {
        if(itemlist == null) {
            listviewitemlist = new ArrayList<CustomDTO>();
        } else {
            listviewitemlist = itemlist;
        }
    }

    @Override
    public int getCount() {
        return listviewitemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return listviewitemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewGroup parent1 = parent;
//        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_layout, parent, false);
        }

        TextView text_name = convertView.findViewById(R.id.listView_pername);
        TextView text_date = convertView.findViewById(R.id.listView_appdate);
        ImageView image_app = convertView.findViewById(R.id.listView_appimage);
        ImageView image = convertView.findViewById(R.id.imageView2);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupup = new PopupMenu(context, v);
                popupup.getMenuInflater().inflate(R.menu.menu, popupup.getMenu());
                popupup.show();
                popupup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        CustomDTO DTO1 = listviewitemlist.get(position);
                        PackageManager pm = context.getPackageManager();
                        switch (item.getItemId()) {
                            case R.id.item_execute:
                                Intent intent = pm.getLaunchIntentForPackage(DTO1.getApppackage());
                                if(intent != null) {
                                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                    break;
                                }
                            case R.id.item_delete:
                                Intent intent1 = new Intent(Intent.ACTION_DELETE);
                                intent1.setData(Uri.fromParts("package", DTO1.getApppackage(), null));
                                intent1.setComponent(new ComponentName("com.android.packageinstaller", "com.android.packageinstaller.UninstallerActivity"));
                                context.startActivity(intent1);
                                break;
                            case R.id.item_permission:
                                Intent intent2 = new Intent(context.getApplicationContext(), PermissionActivity.class);
                                intent2.putExtra("packagename", DTO1.getApppackage());
                                intent2.putExtra("appname",DTO1.getAppname());
                                context.startActivity(intent2);
//                                Toast.makeText(context,"permission : " + DTO1.getApppermission(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
            }
        });
        DTO = listviewitemlist.get(position);
        text_name.setText(DTO.getAppname());
        text_date.setText(DTO.getAppdate());
        image_app.setImageDrawable(DTO.getAppimage());
        return convertView;
    }

    public void addItem(Drawable app_image, String app_name, String app_date, String app_package) {
        CustomDTO DTO = new CustomDTO();
        DTO.setAppimage(app_image);
        DTO.setAppname(app_name);
        DTO.setAppdate(app_date);
        DTO.setApppackage(app_package);

        listviewitemlist.add(DTO);
    }
}
