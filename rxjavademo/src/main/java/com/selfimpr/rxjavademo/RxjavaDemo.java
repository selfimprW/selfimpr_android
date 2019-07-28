package com.selfimpr.rxjavademo;


import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class RxjavaDemo {

    public static void create() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.e("wjc", "call--" + Thread.currentThread().getName());
                subscriber.onNext("Hello" + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("Imooc" + System.currentTimeMillis());
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("wjc", Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("wjc", Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("wjc", Thread.currentThread().getName() + "--" + s);
                    }
                });
    }

    public static void defer() {
        Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("1");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String t) {

            }
        });
    }

}
