package com.selfimpr;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/6/13 下午2:02<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class PhoneUtil {
    public static String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    public static boolean checkPhone(String phone) {
        return RegexUtil.checkContent(phone, PHONE_NUMBER_REG);
    }
}
