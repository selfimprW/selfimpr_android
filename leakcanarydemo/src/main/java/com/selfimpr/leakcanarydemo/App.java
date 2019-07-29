package com.selfimpr.leakcanarydemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * description：https://github.com/square/leakcanary/tree/v1.6.3
 * https://github.com/android-cn/android-discuss/issues/254<br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2019-07-29 21:23<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
