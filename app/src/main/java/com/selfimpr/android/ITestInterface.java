package com.selfimpr.android;

import android.util.Log;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/7/18 下午5:30<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public interface ITestInterface {

    default void publish(String a, int aa) {
        publish(a);
        Log.e("wjc", "test");
    }

    void publish(String a);
}
