package com.selfimpr.keyborad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * 禁止EditText弹出复制黏贴
 * https://stackoverflow.com/questions/41673185/disable-edittext-context-menu
 */
public class SimpleEditText extends EditText {
    public SimpleEditText(Context context) {
        super(context);
        blockContextMenu();
    }

    public SimpleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        blockContextMenu();
    }

    public SimpleEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        blockContextMenu();
    }

    private void blockContextMenu() {
        this.setCustomSelectionActionModeCallback(new BlockedActionModeCallback());
        this.setLongClickable(false);
    }

    @Override
    public boolean isSuggestionsEnabled() {
        return false;
    }

    private class BlockedActionModeCallback implements ActionMode.Callback {

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // setInsertionDisabled when user touches the view
            this.setInsertionDisabled();
        }
        return super.onTouchEvent(event);
    }

    private void setInsertionDisabled() {
        try {
            Field editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Object editorObject = editorField.get(this);

            // if this view supports insertion handles
            Class editorClass = Class.forName("android.widget.Editor");
            Field mInsertionControllerEnabledField = editorClass.getDeclaredField("mInsertionControllerEnabled");
            mInsertionControllerEnabledField.setAccessible(true);
            mInsertionControllerEnabledField.set(editorObject, false);

            // if this view supports selection handles
            Field mSelectionControllerEnabledField = editorClass.getDeclaredField("mSelectionControllerEnabled");
            mSelectionControllerEnabledField.setAccessible(true);
            mSelectionControllerEnabledField.set(editorObject, false);
        } catch (Exception ignored) {
            // ignore exception here
        }
    }
}
