package com.example.root.project1;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import android.content.Context;
import android.content.pm.*;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.*;

public class PermissionActivity extends AppCompatActivity {
    TextView text;
    TextView text1;
    public PermissionActivity() {

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        text = findViewById(R.id.textView_per);
        text1 = findViewById(R.id.textView_appname);
        Intent intent = getIntent();
        String packagename = intent.getStringExtra("packagename");
        String permission = getPermissionbyPackagename(packagename);
        String appname = intent.getStringExtra("appname");
        text1.setText(appname + "의 허가된 권한\n");
        if (permission.equals("")) {
            text.setText("허가된 권한이 없습니다.");
        } else {
            text.setText(permission);
        }
    }

    public String getPermissionbyPackagename(String packageName) {
        StringBuilder builder = new StringBuilder();
        try {
            PackageInfo packageinfo = getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            int counter = 1;
            for (int i = 0; i < packageinfo.requestedPermissions.length; i++) {
                String permission = packageinfo.requestedPermissions[i];
                if (permission.equals("android.permission.READ_LOGS")) {
                    builder.append("" + counter + ". 사용자 행동 수집\n\n");
                    counter++;
                } else if (permission.equals("android.permission.READ_CALENDAR")) {
                    builder.append("" + counter + ". 일정 조회\n\n");
                    counter++;
                } else if (permission.equals("com.google.android.c2dm.permission.RECEIVE")) {
                    builder.append("" + counter + ". 푸쉬 알림\n\n");
                    counter++;
                } else if (permission.equals("android.permission.INTERNET")) {
                    builder.append("" + counter + ". 인터넷\n\n");
                    counter++;
                } else if (permission.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    builder.append("" + counter + ". 위치 추적\n\n");
                    counter++;
                } else if (permission.equals("android.permission.READ_SMS")) {
                    builder.append("" + counter + ". 문자 조회\n\n");
                    counter++;
                } else if (permission.equals("android.permission.SEND_SMS")) {
                    builder.append("" + counter + ". 문자 발신\n\n");
                    counter++;
                } else if (permission.equals("android.permission.READ_PHONE_STATE")) {
                    builder.append("" + counter + ". 개인 정보 조회\n\n");
                    counter++;
                } else if (permission.equals("android.permission.READ_CONTACTS")) {
                    builder.append("" + counter + ". 연락처 조회\n\n");
                    counter++;
                } else if (permission.equals("android.permission.GET_ACCOUNTS")) {
                    builder.append("" + counter + ". 계정 접근\n\n");
                    counter++;
                } else if (permission.equals("android.permission.RECEIVE_BOOT_COMPLETED")) {
                    builder.append("" + counter + ". 부팅시 동작\n\n");
                    counter++;
                } else if (permission.equals("android.permission.WIFI_STATE")) {
                    builder.append("" + counter + ". 와이파이 확인\n\n");
                    counter++;
                } else if (permission.equals("android.permission.EXTERNAL_STORAGE")) {
                    builder.append("" + counter + ". 외장 메모리 사용\n\n");
                    counter++;
                } else if (permission.equals("android.permission.RECODER_AUDIO")) {
                    builder.append("" + counter + ". 녹음 사용\n\n");
                    counter++;
                } else if (permission.equals("android.permission.CAMERA")) {
                    builder.append("" + counter + ". 카메라 사용\n\n");
                    counter++;
                } else if (permission.equals("android.permission.DELETE_CACHE_FILES")) {
                    builder.append("" + counter + ". 캐시파일 제거\n\n");
                    counter++;
                } else if (permission.equals("android.permission.DELETE_PACKAGES")) {
                    builder.append("" + counter + ". 패키지 제거\n\n");
                    counter++;
                } else if (permission.equals("android.permission.DEVICE_POWER")) {
                    builder.append("" + counter + ". 전원상태에 대한 로우레벨 접근\n\n");
                    counter++;
                } else if (permission.equals("android.permission.GET_ACCOUNTS")) {
                    builder.append("" + counter + ". 계정 획득\n\n");
                    counter++;
                } else if (permission.equals("android.permission.MODIFY_AUDIO_SETTINGS")) {
                    builder.append("" + counter + ". 오디오 설정 편집\n\n");
                    counter++;
                } else if (permission.equals("android.permission.PHONE_STATE")) {
                    builder.append("" + counter + ". 전화 상태 편집\n\n");
                    counter++;
                } else if (permission.equals("android.permission.MOUNT_UNMOUNT_FILESYSTEMS")) {
                    builder.append("" + counter + ". 파일시스템 편집\n\n");
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
