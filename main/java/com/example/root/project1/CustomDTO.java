package com.example.root.project1;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class CustomDTO {
    private String appname;
    private String appdate;
    private Drawable appimage;
    private String apppackage;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getAppdate() {
        return appdate;
    }

    public void setAppdate(String appdate) {
        this.appdate = appdate;
    }

    public Drawable getAppimage() {
        return appimage;
    }

    public void setAppimage(Drawable appimage) {
        this.appimage = appimage;
    }

    public String getApppackage() {
        return apppackage;
    }

    public void setApppackage(String apppackage) {
        this.apppackage = apppackage;
    }

}