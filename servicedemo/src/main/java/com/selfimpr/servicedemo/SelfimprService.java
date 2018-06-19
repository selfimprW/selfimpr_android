package com.selfimpr.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * description：  https://blog.csdn.net/javazejian/article/details/52709857 <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/6/19 下午3:01<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class SelfimprService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("SelfimprService", "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("SelfimprService", "onBind");
        return binder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.e("SelfimprService", "unbindService");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("SelfimprService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("SelfimprService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("SelfimprService", "onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("SelfimprService", "onDestroy");
    }

    private LocalBinder binder = new LocalBinder();

    /**
     * 创建Binder对象，返回给客户端即Activity使用，提供数据交换的接口
     */
    public class LocalBinder extends Binder {
        // 声明一个方法，getService。（提供给客户端调用）
        SelfimprService getService() {
            // 返回当前对象LocalService,这样我们就可在客户端端调用Service的公共方法了
            return SelfimprService.this;
        }
    }
}
