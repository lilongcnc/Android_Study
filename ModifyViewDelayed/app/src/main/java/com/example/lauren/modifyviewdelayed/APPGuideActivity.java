package com.example.lauren.modifyviewdelayed;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class APPGuideActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appguide);


        mContext = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //由引导页面跳转到应用主页面
                Intent intent = new Intent(mContext,MainActivity.class);
                startActivity(intent);
            }
        },1000*3);
    }
}
