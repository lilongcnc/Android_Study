package com.lauren.newwithnetdata20161214;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lauren.newwithnetdata20161214.adapter.NewsListViewAdapter;
import com.lauren.newwithnetdata20161214.dao.InfoDaoUtils;
import com.lauren.newwithnetdata20161214.model.NewsGetNetDataUtil;
import com.lauren.newwithnetdata20161214.model.RecommondInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private Context mContext;
    private ListView lsit_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;


        //创建listView
        lsit_listView = (ListView) findViewById(R.id.lsit_listView);


        //先从数据库中读取数据
        ArrayList<RecommondInfo> dbArrayList = new InfoDaoUtils(mContext).query();
        if (dbArrayList != null && dbArrayList.size()>0)
        {
            lsit_listView .setAdapter(new NewsListViewAdapter(mContext,dbArrayList));
        }


        //请求网络数据
        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList<RecommondInfo> recommondList  = NewsGetNetDataUtil.getAllNewsForDatabase(mContext);

                //传递数据主线程更新
                Message message = new Message();
                message.obj = recommondList;
                handler.sendMessage(message);
            }
        }).start(); //记住要启动线程啊

        //设置listview条目的点击事件
        lsit_listView.setOnItemClickListener(this);
    }



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ArrayList<RecommondInfo> recommondList = (ArrayList<RecommondInfo>) msg.obj;

            if (recommondList.size() > 0 && recommondList != null)
            {
                //设置listView显示
                lsit_listView.setAdapter(new NewsListViewAdapter(mContext, recommondList));
            }
        }
    };


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.lilongcnc.cc"));
        startActivity(intent);

    }
}
