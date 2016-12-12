package com.lauren.listviewtest20161208;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lauren.listviewtest20161208.adapter.NewsViewAdapter;
import com.lauren.listviewtest20161208.model.InfoBean;
import com.lauren.listviewtest20161208.model.NewsTempData;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext =this;

        //



        //1.找到控件
        ListView lv_tiger1 = (ListView) findViewById(R.id.lv_tiger1);
        //创建adapter设置给listviev
        NewsViewAdapter newsViewAdapter = new NewsViewAdapter(mContext, NewsTempData.getAllNews(mContext));
        lv_tiger1.setAdapter(newsViewAdapter);
        lv_tiger1.setOnItemClickListener(this);//增加点击监听

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        InfoBean infoBean = (InfoBean) adapterView.getItemAtPosition(i);
        Log.i("跳转到连接","跳转到连接");
        String url = infoBean.news_url;

        //跳转到浏览器
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
