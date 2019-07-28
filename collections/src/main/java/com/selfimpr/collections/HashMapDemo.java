package com.selfimpr.collections;

import android.util.Log;
import android.view.View;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2019-07-28 16:02<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class HashMapDemo {

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    public static void construction() {
        HashMap<String, String> hashMap = new HashMap<>();

        HashMap<String, String> hashMapWithParams = new HashMap<>(15, DEFAULT_LOAD_FACTOR);
        Log.e("wjc", "hashMapWithParams--" + hashMapWithParams.size());

        Map<String, String> syncMap = Collections.synchronizedMap(hashMapWithParams);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            hashMap.put(String.valueOf(i), String.valueOf(i));
        }
        Log.e("wjc", "hashMap--" + hashMap.size() + " , " + (System.currentTimeMillis() - start));
    }

    public static void transform() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "1");
        //1
        Set<Map.Entry<String, String>> entrySet = hashMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            Log.e("wjc", "key = " + entry.getKey() + " , value = " + entry.getValue());
        }

        //2
        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            entry.getKey();
            entry.getValue();
        }

        //3
        for (String key : hashMap.keySet()) {
            hashMap.get(key);
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void threadSafety() {
        final HashMap<String, String> map = new HashMap<>(2, 0.75f);
        map.put("5", "C");

        new Thread("Thread1") {
            public void run() {
                map.put("7", "B");
                Log.e("wjc", "map1 = " + map + "，" + hash("7"));
            }
        }.start();
        new Thread("Thread2") {
            public void run() {
                map.put("3", "A");
                Log.e("wjc", "map2 = " + map);
            }

        }.start();

    }
}
