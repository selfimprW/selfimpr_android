package com.selfimpr.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
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

    public void startService(View view) {
        Intent intent = new Intent(MainActivity.this, SelfimprService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(MainActivity.this, SelfimprService.class);
        stopService(intent);
    }

    public void bindService(View view) {
        Intent intent = new Intent(MainActivity.this, SelfimprService.class);
        bindService(intent, serviceConnection, 0);
    }

    /**
     * 多次调用unbindService，会出现此异常：
     * java.lang.IllegalArgumentException: Service not registered: com.selfimpr.servicedemo.MainActivity$1@7e75b38
     */
    public void unbindService(View view) {
        unbindService(serviceConnection);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("SelfimprService", "onServiceConnected--->ComponentName:" + name);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("SelfimprService", "onServiceDisconnected--->ComponentName:" + name);
        }
    };
}
