package com.selfimpr.okhttpdemo;

import android.os.Looper;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final File cacheDir = new File(getApplication().getCacheDir(), "HttpResponseCache");
        final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
        client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new LoggingInterceptor())
                .readTimeout(20, TimeUnit.SECONDS) // 读数据超时
                .writeTimeout(20, TimeUnit.SECONDS) // 写数据超时
                .connectTimeout(15, TimeUnit.SECONDS) // 连接超时
                .cache(new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE)).build();

    }

    /**
     * 异步请求
     *
     * @param view
     */
    public void asyncRequest(View view) {
        Request request = new Request.Builder()
                .url("https://api.apiopen.top/getJoke?page=1&count=2&type=video")
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            //todo 回调调用是在子线程执行
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("wjc", "asyRequest#onFailure-->" + e.toString() + call.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                Log.e("wjc", "asyRequest#onResponse-->" + response.body().string());
            }
        });
    }


    /**
     * 同步请求
     * 主线程直接调用会有异常：android.os.NetworkOnMainThreadException
     *
     * @throws IOException
     */
    public void syncRequest(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url("https://api.apiopen.top/getJoke?page=1&count=2&type=video")
                            .build();
                    Response response = client.newCall(request).execute();
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                    Looper.loop();
//        Log.e("wjc", "synRequest2--->" + response.body().string());
//        Log.e("wjc", "synRequest1--->" + new String(response.body().bytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
