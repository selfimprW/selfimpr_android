package com.selfimpr.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService service;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.content);

    }

    public void startAsyncTask(View view) {
        if (service == null || service.isShutdown()) {
            service = Executors.newFixedThreadPool(3);
            content.setText("创建线程池");
        }
        for (int i = 0; i < 15; i++) {
            service.execute(new AsyncTaskRunnable("clickImageView:" + i));
        }
    }

    public void shutdownAsyncTask(View view) {
        service.shutdown();
        content.setText("shutdown:" + service.isShutdown() + "," + service.isTerminated());
    }

    public void shutdownNowAsyncTask(View view) {
        List<Runnable> runnables = service.shutdownNow();
        content.setText("shutdownNow:" + service.isShutdown() + "," + service.isTerminated() + ".runnables" + runnables.size());
    }
}
