package com.lauren.listviewtest20161208;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext =this;

        //1.找到控件
        ListView lv_tiger1 = (ListView) findViewById(R.id.lv_tiger1);
        ListView lv_tiger2 = (ListView) findViewById(R.id.lv_tiger2);
        ListView lv_tiger3 = (ListView) findViewById(R.id.lv_tiger3);
        //创建adapter设置给listviev
        MyListAdapter tigerAdapter = new MyListAdapter();
        lv_tiger1.setAdapter(tigerAdapter);
        lv_tiger2.setAdapter(tigerAdapter);
        lv_tiger3.setAdapter(tigerAdapter);
    }


    class MyListAdapter extends BaseAdapter{

        private Map<Integer,Integer> map = new HashMap<Integer,Integer>();


        @Override
        public int getCount() {
            return 500;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //基础
//            TextView textView = null;
//            if (view != null)
//            {
//                textView = (TextView) view;
//            }
//            else{
//                textView =  new TextView(mContext);
//
//            }
//            textView.setText("textView_i"+i);
//            textView.setTextSize(30);
//
//            map.put(textView.hashCode(),1);
//            System.out.println("创建了"+map.size()+"个TextView");

            //2.老虎机
            TextView textView = null;
            //复用convertView
            if(view != null){
                textView = (TextView) view;
            }else{
                textView = 	new TextView(mContext);
            }

            Random random = new Random();
            int number = random.nextInt(100);
            if(number <20){
                textView.setTextColor(Color.parseColor("#ff00ff"));//设置textview文字颜色
                textView.setText("桃");
            }else if(number < 40){
                textView.setTextColor(Color.YELLOW);//设置textview文字颜色
                textView.setText("杏");
            }else if(number <60){
                textView.setTextColor(Color.GREEN);//设置textview文字颜色
                textView.setText("梨");
            }else if(number <80){
                textView.setTextColor(Color.RED);//设置textview文字颜色
                textView.setText("枣");
            }else {
                textView.setTextColor(Color.parseColor("#666666"));//设置textview文字颜色
                textView.setText("瓜");
            }

            textView.setTextSize(58);
            return textView;

        }
    }
}
