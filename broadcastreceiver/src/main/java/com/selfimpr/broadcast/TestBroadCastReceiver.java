package com.selfimpr.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/7/10 下午2:44<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class TestBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("myaction".equals(action)) {
            Log.e("wjc 消息1：" + intent.getStringExtra("data"), "线程： " + Thread.currentThread().getName());
        }
        if ("myaction1".equals(action)) {
            Log.e("wjc 消息2：" + intent.getStringExtra("data"), "线程： " + Thread.currentThread().getName());
        }
    }
}
