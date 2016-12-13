package com.lauren.watchwebhtmlcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Extension;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn_watchCode = (Button) findViewById(R.id.btn_watchCode);
        btn_watchCode.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        //退下键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        try {
            EditText input_index = (EditText)findViewById(R.id.et_inPutUrl);
            final String url_str =  input_index.getText().toString().trim();
            Log.e("元澳门1",url_str);

            if (TextUtils.isEmpty(url_str)){
                Toast.makeText(MainActivity.this,"url不能为空",Toast.LENGTH_SHORT).show();
                return;
            }


            System.out.println("oclick方法线程："+Thread.currentThread().getName());

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        System.out.println("oclick方法runnable线程："+Thread.currentThread().getName());

                        //1.创建一个Url对象
                        URL url = new URL(url_str);
                        //2.获取一个URLCollection对象
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        //3.为UrlConnection对象设置一些请求的参数,请求方式，连接的超时时间
                        connection.setRequestMethod("GET");//
                        connection.setReadTimeout(1000 * 10);

                        //4.在获取url请求的数据前需要判断响应码，200 ：成功,206:访问部分数据成功   300：跳转或重定向  400：错误 500：服务器异常
                        int code = connection.getResponseCode();
                        Log.e("元澳门", code + "");
//                        Thread.sleep(3*1000);

                        if (code == 200) {
                            //5.获取有效数据，并将获取的流数据解析成String
                            String htmlCode_str = ResultUtils.streamToString(connection.getInputStream());
                            Log.e("元澳门", htmlCode_str);
                            TextView tv_showCode = (TextView) findViewById(R.id.tv_showCode);

                            tv_showCode.setText(htmlCode_str); //说好的在主线程中更新UI呢????
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
