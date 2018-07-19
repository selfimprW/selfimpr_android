package com.selfimpr.modulecollections;

import android.support.v4.util.SimpleArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * https://blog.csdn.net/woblog/article/details/10369287
 * https://blog.csdn.net/wangzhongshun/article/details/78286031
 * https://blog.csdn.net/ztchun/article/details/52017290
 * <p>
 * http://www.cnblogs.com/fuck1/p/5322302.html
 * https://blog.csdn.net/whb20081815/article/details/70291412
 */
public class MainActivity extends AppCompatActivity {

    //todo android中
    private SparseArray<String> sparseArray;
    private SparseIntArray sparseIntArray;
    private SimpleArrayMap<String, String> simpleArrayMap;
    private android.support.v4.util.ArrayMap<String, String> arrayMap1;

    //todo java中
    private List<String> list;
    private ArrayList<String> arrayList;
    private LinkedList<String> linkedList;
    private Map<String, String> map;
    private HashMap<String, String> hashMap;
    private ArrayMap<String, String> arrayMap;
    private ConcurrentHashMap<String, String> concurrentHashMap;
    private LinkedHashMap<String, String> linkedHashMap;
    private Hashtable<String, String> hashtable;
    private Set<String> set;
    private HashSet<String> hashSet;
    private Vector vector;
    private Stack stack;
    private TreeMap<String, String> treeMap;
    private TreeSet<String> treeSet;

    private ConcurrentSkipListMap<String, String> concurrentSkipListMap;
    private ConcurrentSkipListSet<String> concurrentSkipListSet;
    private ConcurrentMap<String, String> concurrentMap;
    private ConcurrentNavigableMap<String, String> concurrentNavigableMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
