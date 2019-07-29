package com.selfimpr.leakcanarydemo;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //http://www.apkbus.com/blog-705730-60817.html
        StrictMode.VmPolicy vmPolicy = new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .detectLeakedClosableObjects()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .build();
        StrictMode.setVmPolicy(vmPolicy);
    }

    public void jumpSecondActivity(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
