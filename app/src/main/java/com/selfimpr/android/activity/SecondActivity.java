package com.selfimpr.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/7/19 下午9:07<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class SecondActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);

    }
}
