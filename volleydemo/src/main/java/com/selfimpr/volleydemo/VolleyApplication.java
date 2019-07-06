package com.selfimpr.volleydemo;

import android.app.Application;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2019-07-06 14:43<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class VolleyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyManager.get().init(this);
    }
}
