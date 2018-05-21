package com.selfimpr.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //可重入锁测试
    public synchronized void test1() {
        test2();
        Log.e("test", "test1");
    }

    private synchronized void test2() {
        Log.e("test", "test2");
    }

    public void clickImageView(View view) {
        test1();
    }
}
