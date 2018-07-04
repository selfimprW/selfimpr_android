package com.selfimpr.keyborad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * description：禁止粘贴复制   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/6/22 下午4:08<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class SimpleEditTextV1 extends EditText {
    public SimpleEditTextV1(Context context) {
        super(context);
    }

    public SimpleEditTextV1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleEditTextV1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            requestFocus();
            setSelection(getText().toString().length());
        }
        return true;
    }

//    @Override
//    public boolean onTextContextMenuItem(int id) {
//        if (id == android.R.id.paste || id == android.R.id.copy || id == android.R.id.cut) {
////            Toast.makeText(getContext(), "禁止粘贴", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onTextContextMenuItem(id);
//    }
}
