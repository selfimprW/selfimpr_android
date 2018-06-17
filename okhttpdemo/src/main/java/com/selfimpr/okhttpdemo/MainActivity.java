package com.selfimpr.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Request request = new Request.Builder()
                .url("http://square.github.io/okhttp/")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("wjc", call.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("wjc", response.body().string());
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syn();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void syn() throws IOException {
        Request request = new Request.Builder()
                .url("http://square.github.io/okhttp/")
                .build();
        Response response = client.newCall(request).execute();
        Log.e("wjc", "syn--->" + response.body().string());
    }
}
