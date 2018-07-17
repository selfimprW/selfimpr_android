package com.selfimpr;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/7/17 上午11:51<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class StringUtil {
    /**
     * split String to List.
     *
     * split("abc", null)       =   {"abc"}
     * split("abc|def", "\|")    =   {"abc", "def"}
     */
    @Nullable
    public static List<String> split(String raw, String separator) {
        if (null == raw) {
            return null;
        } else if (null == separator) {
            List<String> list = new ArrayList<>();
            list.add(raw);
            return list;
        } else {
            String[] result = raw.split(separator);
            return new ArrayList<>(Arrays.asList(result));
        }
    }

}
