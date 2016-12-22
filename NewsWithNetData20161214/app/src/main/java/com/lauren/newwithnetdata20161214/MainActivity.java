package com.lauren.newwithnetdata20161214;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lauren.newwithnetdata20161214.adapter.NewsListViewAdapter;
import com.lauren.newwithnetdata20161214.model.NewsUtils;
import com.lauren.newwithnetdata20161214.model.RecommondInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private Context mContext;
    private ListView lsit_listView;



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ArrayList<RecommondInfo> recommondList = (ArrayList<RecommondInfo>) msg.obj;

            if (recommondList.size() > 0 && recommondList != null)
            {
                //设置listView显示
                Log.e("主线程中更新网络请求 ", recommondList.size()+"" );
                lsit_listView.setAdapter(new NewsListViewAdapter(mContext, recommondList));
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;


        //创建listView
        lsit_listView = (ListView) findViewById(R.id.lsit_listView);


        //先从数据库中读取数据
        ArrayList<RecommondInfo> dbArrayList = NewsUtils.getAllNewsForDatabase(mContext);

        if (dbArrayList != null && dbArrayList.size()>0)
        {
            Log.e("第一次进入APP从数据库中获取到数据 ", dbArrayList.size()+"" );

            NewsListViewAdapter newsAdapter = new NewsListViewAdapter(mContext,dbArrayList);
            lsit_listView.setAdapter(newsAdapter);

//            lsit_listView .setAdapter(new NewsListViewAdapter(mContext,dbArrayList));
        }


        //请求网络数据
        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList<RecommondInfo> recommondList  = NewsUtils.getAllNewsForNetWork(mContext);

                System.out.print("---------------------------------------------------");
                Log.e("run ", recommondList.size()+"" );
                //传递数据主线程更新
                Message message = new Message();
                message.obj = recommondList;
                handler.sendMessage(message);
            }
        }).start(); //记住要启动线程啊



        //设置listview条目的点击事件
        lsit_listView.setOnItemClickListener(this);
    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("http://www.lilongcnc.cc"));
//        startActivity(intent);

    }
}
