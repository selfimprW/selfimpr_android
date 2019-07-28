package com.selfimpr.rxjavademo;


import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxjavaCreateDemo {

    public static void doRxjava() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.e("wjc", "call--" + Thread.currentThread().getName());
                subscriber.onNext("Hello");
                subscriber.onNext("Imooc");
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
                Log.e("wjc", Thread.currentThread().getName());
            }
        });
    }

}
