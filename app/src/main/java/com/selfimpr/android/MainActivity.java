package com.selfimpr.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.selfimpr.StringUtil;
import com.selfimpr.android.viewpager.ViewPagerActivity;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {

    private ExecutorService service;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.content);

        testSparseArray();

        List<String> array = StringUtil.split("入手渠道|转手原因|规格尺寸|新旧程度|使用感受", "\\|");
        Log.e("wjc", String.valueOf(array));

//        startActivity(new Intent(this, RecyclerViewActivity.class));

        ExecutorDeliver deliver = new ExecutorDeliver(new Handler(Looper.getMainLooper()));
        deliver.post();

        try {
            long start = System.currentTimeMillis();
            createCallable();
            Log.e("wjc", "createCallable = " + (System.currentTimeMillis() - start));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createCallable() throws ExecutionException, InterruptedException {
        MyCallable callable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        Log.e("wjc", futureTask.get() + "");
    }

    class MyCallable implements Callable<String> {

        /**
         * 如果此时异步任务还没完成会阻塞当前进程直到返回结果。
         *
         */
        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "callable return";
        }
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

    /**
     * 任何一个需要隐式启动的Activity都必须要有这项:<category android:name="android.intent.category.DEFAULT"/>
     */
    public void launchByIntentFilter(View view) {
        Intent intent = new Intent();
        intent.setAction("wangjiacheng");
        //不加下面这行也行，因为intent的这个属性默认值即系Intent.CATEGORY_DEFAULT
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }
}
