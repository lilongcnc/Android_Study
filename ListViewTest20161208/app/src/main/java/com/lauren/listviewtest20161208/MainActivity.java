package com.lauren.listviewtest20161208;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lauren.listviewtest20161208.adapter.NewsViewAdapter;
import com.lauren.listviewtest20161208.model.InfoBean;
import com.lauren.listviewtest20161208.model.NewsTempData;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{

    private Context mContext;
    private NewsTempData mNewsTempData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext =this;

        //--- 按钮点击部分
        Button btn_add = (Button)findViewById(R.id.btn_add);
        Button btn_del = (Button)findViewById(R.id.btn_del);
        Button btn_modify = (Button)findViewById(R.id.btn_modify);

        btn_add.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_modify.setOnClickListener(this);


        //--- 找到控件
        ListView lv_tiger1 = (ListView) findViewById(R.id.lv_tiger1);
        //创建adapter设置给listviev
        mNewsTempData = new NewsTempData(mContext,lv_tiger1);
        NewsViewAdapter newsViewAdapter = new NewsViewAdapter(mContext, mNewsTempData.getAllNews());
        lv_tiger1.setAdapter(newsViewAdapter);
        lv_tiger1.setOnItemClickListener(this);//增加点击监听

    }

    //listView的item点击事件
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


    //按钮的item点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                mNewsTempData.addDataToDBAndRefreshView();
                break;
            case R.id.btn_del:
                mNewsTempData.dealDataToDBAndRefreshView("标题2");
                break;
            case R.id.btn_modify:
                mNewsTempData.modifyDataToDBAndRefreshView("标题3");
                break;
            default:
            break;
}
    }
}
