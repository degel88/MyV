package me.jingyuan.myv.model.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * desc：me.jingyuan.myv.model.bean
 *
 * @author：degel on 2019-07-22 17:04
 */
public class AppInfo implements Serializable {

    private String appClass;
    private Drawable appIcon;
    private String appLable;
    private String appPackage;

    public String getAppClass() {
        return appClass;
    }

    public void setAppClass(String appClass) {
        this.appClass = appClass;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppLable() {
        return appLable;
    }

    public void setAppLable(String appLable) {
        this.appLable = appLable;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }
}
