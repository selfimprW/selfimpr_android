package com.selfimpr.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

//https://blog.csdn.net/javazejian/article/details/52709857
public class MainActivity extends AppCompatActivity {

    private SelfimprService selfimprService;

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

        /**
         * 在操作者在连接一个服务成功时被调用
         * @param name ComponentName是一个封装了组件(Activity, Service, BroadcastReceiver,
         *            or ContentProvider)信息的类，如包名，组件描述等信息，较少使用该参数。
         * @param service service便是服务端返回的IBinder实现类对象，通过该对象我们便可以调用获取SelfimprService实例对象，进而调用服务端的公共方法。
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("SelfimprService", "onServiceConnected--->ComponentName:" + name);
            SelfimprService.LocalBinder binder = (SelfimprService.LocalBinder) service;
            selfimprService = binder.getService();
        }

        /**
         * Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。注意:当客户端取消绑定时，系统“绝对不会”调用该方法。
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("SelfimprService", "onServiceDisconnected--->ComponentName:" + name);
            selfimprService = null;
        }
    };
}
