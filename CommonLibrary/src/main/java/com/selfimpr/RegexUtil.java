package com.selfimpr;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/6/13 下午2:05<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class RegexUtil {
    /**
     * @param content    需要校验的内容
     * @param checkRegex 正则表达式
     * @return true 满足条件
     */
    public static boolean checkContent(String content, String checkRegex) {
        return !(!TextUtils.isEmpty(checkRegex) && !TextUtils.isEmpty(content)) || Pattern.matches(checkRegex, content);
    }
}
