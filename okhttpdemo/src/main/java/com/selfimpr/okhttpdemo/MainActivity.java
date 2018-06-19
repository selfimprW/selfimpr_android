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
                .url("http://square.github.io/okhttp/")
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            //todo 回调调用是在子线程执行
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("wjc", call.toString());

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e("wjc", response.body().string());
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
                .url("http://square.github.io/okhttp/")
                .build();
        Response response = client.newCall(request).execute();
        Log.e("wjc", "syn--->" + response.body().string());
    }
}