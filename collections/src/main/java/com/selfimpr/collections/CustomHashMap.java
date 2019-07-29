package com.selfimpr.collections;

import java.util.Objects;

/**
 * description：简易版hashmap   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2019-07-28 18:01<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class CustomHashMap {

    CustomEntry[] table = new CustomEntry[990];
    int size = 0;

    public void put(Object key, Object value) {
        CustomEntry entry = new CustomEntry(key, value);
        for (int i = 0; i < size; i++) {
            if (Objects.equals(table[i].key, key)) {
                table[i].value = value;
                return;
            }
        }
        table[size++] = entry;
    }

    public Object get(Object key) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(table[i].key, key)) {
                return table[i].value;
            }
        }
        return null;
    }

    public boolean containsKey(Object key) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(table[i].key, key)) {
                return true;
            }
        }
        return false;
    }


    class CustomEntry {
        Object key;
        Object value;

        public CustomEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
