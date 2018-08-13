package com.selfimpr.android;

/**
 * description： https://blog.csdn.net/jia20003/article/details/53353580
 * 进入java目录，执行 javah -d ../jni com.selfimpr.android.HelloJNI命令，创建.h文件<br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/8/13 上午8:54<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class HelloJNI {

    public native static String greet();

    public native static int sum(int a, int b);
}
