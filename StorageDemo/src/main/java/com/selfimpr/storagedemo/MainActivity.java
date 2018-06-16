package com.selfimpr.storagedemo;

import android.app.usage.ExternalStorageStats;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 一、是一个应用程序的私有目录，只有当前应用程序有权限访问读写，其他应用无权限访问。
 * 一些安全性要求比较高的数据存放在该目录，一般用来存放size比较小的数据。
 * <p>
 * 二、是一个外部存储目录，只用应用声明了<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>的一个权限，
 * 就可以访问读写sdcard目录；所以一般用来存放一些安全性不高的数据，文件size比较大的数据。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      File  file = new File(getExternalFilesDir(""),"sss");
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File sdCardDir = Environment.getExternalStorageDirectory();//获取sd卡目录
            File sdFile  = new File(sdCardDir, "wangjiacheng");
        }

        StorageUtil.writeString(file, "wangjiacheng");
        String value = StorageUtil.readString(file);
        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();


//        openFileInput()-内部实现->FileInputStream

        Person person = new Person();
        person.setName("wangjiacheng");
//        File fileObject = new File( "object1.txt");
        StorageUtil.readObject(this, "obj1ect.txt");

        StorageUtil.writeObject(this, "ob1ject.txt", person);

    }


}
