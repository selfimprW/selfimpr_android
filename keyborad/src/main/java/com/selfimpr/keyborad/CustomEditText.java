package com.selfimpr.keyborad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/6/22 上午10:50<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class CustomEditText extends EditText {
    public CustomEditText(Context context) {
        this(context, null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean showContextMenu() {
        return false;
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        return true;
    }

//    @Override
//    public boolean onTextContextMenuItem(int id) {
//        if (id == android.R.id.paste){
//            Toast.makeText(getContext(),"禁止粘贴",Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onTextContextMenuItem(id);
//    }

}
