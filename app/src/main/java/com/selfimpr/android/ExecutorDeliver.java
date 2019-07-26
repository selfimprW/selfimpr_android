package com.selfimpr.android;

import android.os.Handler;
import android.util.Log;

import java.util.concurrent.Executor;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2019-07-14 15:07<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class ExecutorDeliver {

    private final Executor mResponsePoster;

    public ExecutorDeliver(Handler handler) {
        mResponsePoster =
                new Executor() {
                    @Override
                    public void execute(Runnable command) {
                        handler.post(command);
                        Log.e("wjc", "execute");
                    }
                };
    }

    public void post() {
        mResponsePoster.execute(new Runnable() {
            @Override
            public void run() {
                Log.e("wjc", "post");
            }
        });

    }
}
