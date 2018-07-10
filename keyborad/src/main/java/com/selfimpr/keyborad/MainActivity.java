package com.selfimpr.keyborad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = findViewById(R.id.edit);
        edit.setInputType(EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);

        BigDecimal big2 = new BigDecimal("0.2");
        BigDecimal big4 = new BigDecimal("0.4");
        BigDecimal result =   big2.add(big4);
        Log.e("wjc",String.valueOf(result));
    }
}
