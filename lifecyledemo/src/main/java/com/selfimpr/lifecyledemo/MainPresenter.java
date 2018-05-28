package com.selfimpr.lifecyledemo;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/5/28 上午11:58<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class MainPresenter implements IPresenter {
    @Override
    public void onCreate() {
        Log.e("wjc", "onCreate");
    }

    @Override
    public void onStart() {
        Log.e("wjc", "onStart");
    }

    @Override
    public void onResume() {
        Log.e("wjc", "onResume");
    }

    @Override
    public void onPause() {
        Log.e("wjc", "onPause");
    }

    @Override
    public void onStop() {
        Log.e("wjc", "onStop");
    }

    @Override
    public void onDestroy() {
        Log.e("wjc", "onDestroy");
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        Log.e("wjc", "onLifecycleChanged:" + owner.getLifecycle() + ",event:" + event);
    }
}
