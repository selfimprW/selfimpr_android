package com.selfimpr.android;

import android.util.Log;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/5/23 下午2:15<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class AsyncTaskRunnable implements Runnable {
    private String name;

    public AsyncTaskRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Log.e("wjc", "threadName" + Thread.currentThread().getName() + ",AsyncTaskRunnable-->" + name + "，interrupted:" + Thread.interrupted());
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("wjc", "threadName" + Thread.currentThread().getName() + ",AsyncTaskRunnable-->" + name + "，interrupted:" + Thread.interrupted());
    }
}
