package com.selfimpr.edittextdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/6/14 下午3:47<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */

public class EditTextWithScrollView1 extends EditText {
    public EditTextWithScrollView1(Context context) {
        super(context);
    }

    public EditTextWithScrollView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.isFocused() && this.getParent() != null) {
            this.getParent().requestDisallowInterceptTouchEvent(true);
        }

        if (!this.isFocused() && this.getParent() != null) {
            this.getParent().requestDisallowInterceptTouchEvent(false);
        }

        return super.dispatchTouchEvent(event);
    }
}