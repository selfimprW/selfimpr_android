package com.selfimpr.okhttpdemo;

import android.util.Log;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2019/7/26 上午12:04<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        request.url();
        chain.connection();
        request.headers();
        Response response = chain.proceed(request);

        Log.e("wjc", "LoggingInterceptor#intercept-->" +   request.url());
        return response;
    }
}
