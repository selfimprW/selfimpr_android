package com.selfimpr.storagedemo;

import android.content.Context;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * description：  FileWriter/FileReader；
 * JsonReader；InputStreamReader；
 * BufferedInputStream|ByteArrayOutputStream|BufferedOutputStream
 * BufferedOutputStream|ByteArrayInputStream|BufferedInputStream<br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2018/6/16 23:01<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class StorageUtil {

    /**
     * @param context  context
     * @param fileName 文件名，不能是路径
     * @param obj      对象
     * @return true是存储成功
     */
    public static boolean writeObject(Context context, String fileName, Object obj) {
        boolean status = true;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            // MODE_APPEND：即向文件尾写入数据;
            // MODE_PRIVATE：即仅打开文件可写入数据;
            // MODE_WORLD_READABLE：所有程序均可读该文件数据;
            // MODE_WORLD_WRITABLE：即所有程序均可写入数据。
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    /**
     * @param context  context
     * @param fileName 文件名，不能是路径
     * @return 获取存储对象
     */
    public static Object readObject(Context context, String fileName) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object object = null;
        try {
            File streamFile = context.getFileStreamPath(fileName);
            if (streamFile != null && streamFile.exists() && streamFile.isFile() && streamFile.length() > 0) {
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);
                object = ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    public static boolean writeString(File file, String content) {
        if (TextUtils.isEmpty(content)) {
            return true;
        }
        boolean status = true;
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    public static String readString(File file) {
        StringBuilder sb = new StringBuilder();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] temp = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
        } catch (Exception e) {
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


    private String readAssectText(Context context) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = context.getAssets().open("readme.txt");
            outputStream = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputStream == null ? "" : outputStream.toString();

    }
}
