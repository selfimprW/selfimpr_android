package com.selfimpr.okhttpdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new LoggingInterceptor())
                .readTimeout(5, TimeUnit.SECONDS)
                .cache(new Cache(getApplication().getCacheDir(), 24 * 1024 * 1024)).build();

        asyRequest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 异步请求
     */
    private void asyRequest() {
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
    private void synRequest() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.apiopen.top/getJoke?page=1&count=2&type=video")
                .build();
        Response response = client.newCall(request).execute();
//        Log.e("wjc", "synRequest2--->" + response.body().string());
//        Log.e("wjc", "synRequest1--->" + new String(response.body().bytes()));
    }
}
