package com.selfimpr.collections;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HashMapDemo.construction();

        HashMapDemo.transform();

        HashMapDemo.threadSafety();

        Log.e("wjc", String.valueOf((16 * 16 * 16 * 16 * 5) >>> 16));
    }
}
