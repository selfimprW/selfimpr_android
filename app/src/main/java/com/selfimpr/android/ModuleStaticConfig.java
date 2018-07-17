package com.selfimpr.android;

import java.util.HashMap;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/7/12 下午12:03<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class ModuleStaticConfig {
    private static final HashMap<String, Object> objectHashMap = new HashMap<>();

    public static ModuleStaticConfig getInstance() {
        return IStaticConfigHolder.instance;
    }

    public interface IStaticConfigHolder {
        ModuleStaticConfig instance = new ModuleStaticConfig();
    }

    public void put(String key, Object arg) {
        objectHashMap.put(key, arg);
    }

    public Object get(String key) {
        return objectHashMap.get(key);
    }

}
