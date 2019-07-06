package com.selfimpr.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.selfimpr.volleydemo.core.GsonRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void request(View view) {
        /*StringRequest stringRequest = new StringRequest("https://vipshop.github.io/vjtools/#/standard/chapter09", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                Log.d("StringRequest", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("StringRequest", error.getMessage(), error);
            }
        });
        stringRequest.setTag(this.getClass().getSimpleName());
        //设置缓存
        stringRequest.setShouldCache(true);
//        stringRequest.cancel();
        VolleyManager.get().add(stringRequest);*/

        GsonRequest<Object> gsonRequest = new GsonRequest<>(" http://gank.io/api/today", Object.class, null, new Response.Listener<Object>() {
            @Override
            public void onResponse(Object response) {
                Log.e("wjc", "onResponse-->" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("wjc", "onErrorResponse-->" + error);
            }
        });
        VolleyManager.get().add(gsonRequest);

        VolleyManager.get().getImageLoader().get("https://github.com/flutter-mx/flutter-osc/raw/master/screenshots/ios-theme-03.png",
                new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        ((ImageView) findViewById(R.id.image)).setImageBitmap(response.getBitmap());
                        Log.e("wjc", response + "," + isImmediate);
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("wjc", error.toString());
                    }
                }, 500, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
