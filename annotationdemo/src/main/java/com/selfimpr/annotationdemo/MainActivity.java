package com.selfimpr.annotationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 元注解的作用就是负责注解其他注解。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
