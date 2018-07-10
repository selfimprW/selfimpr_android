package com.selfimpr.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    private LocalBroadcastManager localBroadcastManager;
    private InnerBroadCastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册广播接收器
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastReceiver = new InnerBroadCastReceiver();
        intentFilter = new IntentFilter("myaction");
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程发送广播
                Intent intent = new Intent("myaction");
                intent.putExtra("data", "子线程发过来的消息");
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //取消注册广播,防止内存泄漏
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    public void sendBroadCastReceiver(View view) {
        Log.e("wjc", "--------------start----------------->");

        //在主线程发送广播
//        for (int i = 0; i < 100; i++) {
        Intent intent = new Intent("myaction");
        intent.putExtra("data", "主线程发过来的消息:");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
//        }
        Log.e("wjc", "---------------end---------------->");

    }


    public class InnerBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("myaction".equals(action)) {
                Log.e("wjc 消息1：" + intent.getStringExtra("data"), "线程： " + Thread.currentThread().getName());
            }
            if ("myaction1".equals(action)) {
                Log.e("wjc 消息2：" + intent.getStringExtra("data"), "线程： " + Thread.currentThread().getName());
            }
            dealReceive();
        }
    }

    private void dealReceive() {
        count++;
        Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
    }
}
