package com.selfimpr.storagedemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person person = new Person();
        person.setName("wangjiacheng");

        File file = new File(getCacheDir(), "aaaa.txt");

        write(file);

        String value = read(file);
        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
    }


    private void write(File file) {
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            outStream.write("wangjiacheng".getBytes());
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String read(File file) {
        StringBuilder sb = new StringBuilder();
        //打开文件输入流
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] temp = new byte[1024];
            int len = 0;
            //读取文件内容:
            while ((len = inputStream.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
            //关闭输入流
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

}
