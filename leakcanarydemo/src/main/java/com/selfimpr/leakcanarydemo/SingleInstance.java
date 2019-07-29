package com.selfimpr.leakcanarydemo;

import android.support.v4.app.FragmentActivity;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2019-07-29 21:52<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class SingleInstance {
    public static final SingleInstance get() {
        return Holder.INSTANCE;
    }

    private FragmentActivity activity;

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    private interface Holder {
        SingleInstance INSTANCE = new SingleInstance();
    }
}
