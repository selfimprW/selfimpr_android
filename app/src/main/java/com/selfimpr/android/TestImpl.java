package com.selfimpr.android;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/7/18 下午5:56<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class TestImpl implements ITestInterface, ITestInterfaceB {
    @Override
    public void publish(String a, int aa) {
        ITestInterfaceB.super.publish(a, aa);
    }

    @Override
    public void publish(String a) {

    }
}
