package com.selfimpr.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.selfimpr.StringUtil;

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

        ModuleStaticConfig.getInstance().put("runnable", new AsyncTaskRunnable("w"));
        ModuleStaticConfig.getInstance().put("wjc", "wjc");

        Log.e("wjc", ModuleStaticConfig.getInstance().get("wjc").toString());

        testSparseArray();

        List<String> array = StringUtil.split("入手渠道|转手原因|规格尺寸|新旧程度|使用感受", "\\|");
    }

    private void testSparseArray() {
        SparseArray<String> array = new SparseArray<>();
        for (int i = 0; i < 10; i++) {
            array.put(i, i + ":value");
//            array.append();
        }
        Log.e("wjc", array.toString());
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
