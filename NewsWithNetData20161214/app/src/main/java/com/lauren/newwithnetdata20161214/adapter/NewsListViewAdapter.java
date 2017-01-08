package com.lauren.newwithnetdata20161214.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lauren.newwithnetdata20161214.R;
import com.lauren.newwithnetdata20161214.model.RecommondInfo;
import com.loopj.android.image.SmartImage;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * Created by Lauren on 16/12/15.
 */

public class NewsListViewAdapter extends BaseAdapter {


    public ArrayList<RecommondInfo> dataArrayList;
    private Context mContext;

    //通过构造方法接受要显示的新闻数据集合
    public NewsListViewAdapter(Context context, ArrayList<RecommondInfo> list){
        this.dataArrayList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return dataArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Log.e("apapter 进来了", "getView: 进来了");

        View itemView = null;
        if (itemView != null)
            itemView = view;
        else
        {
            itemView = View.inflate(mContext, R.layout.item_news_layout,null);
        }


        //2.获取view上的子控件对象
        SmartImageView imgv_logo = (SmartImageView) itemView.findViewById(R.id.imgv_logo);
        TextView tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        TextView tv_dec = (TextView) itemView.findViewById(R.id.tv_dec);
        TextView tv_dec2 = (TextView) itemView.findViewById(R.id.tv_dec2);
        TextView tv_credit = (TextView) itemView.findViewById(R.id.tv_credit);
        TextView tv_scolo = (TextView) itemView.findViewById(R.id.tv_scolo);

        //获取模型赋值数据
        RecommondInfo recommondInfo = dataArrayList.get(i);

        //赋值
        imgv_logo.setImageUrl(recommondInfo.Logo);
        tv_title.setText(recommondInfo.CustomerName);
        tv_dec.setText(recommondInfo.Intro);
        tv_dec2.setText(recommondInfo.TypeName);
        tv_credit.setText("返现金积分:"+recommondInfo.ReturnCredit+"%");
        tv_scolo.setText("返购物积分:"+recommondInfo.SoloCreditPercent+"%");

        return itemView;
    }
}
