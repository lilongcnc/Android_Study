package com.example.lauren.modifyviewdelayed;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //
                TextView tv_mainshow = (TextView) findViewById(R.id.tv_mainshow);
                tv_mainshow.setText("更新啦啊啊");
            }
        },1000*3);
    }
}
