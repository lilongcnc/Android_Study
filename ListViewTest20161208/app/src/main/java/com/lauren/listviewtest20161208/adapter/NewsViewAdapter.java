package com.lauren.listviewtest20161208.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lauren.listviewtest20161208.R;
import com.lauren.listviewtest20161208.model.InfoBean;

import java.util.ArrayList;

/**
 * Created by Lauren on 16/12/9.
 */

public class NewsViewAdapter extends BaseAdapter{

    public   ArrayList<InfoBean> arrayList;
    private  Context mContext;
    public NewsViewAdapter(Context context, ArrayList<InfoBean> list){

        this.arrayList = list;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = null;
        if (view != null) {
            itemView = view;
        }else{

            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
            itemView = View.inflate(mContext, R.layout.item_news_layout, null);//将一个布局文件转换成一个view对象

            //通过LayoutInflater将布局转换成view对象
//            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_news_layout, null);

            //通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
//            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            itemView = layoutInflater.inflate(R.layout.item_news_layout, null);

        }

        ImageView item_img_news_photo = (ImageView)itemView.findViewById(R.id.item_img_newsFacePhoto);
        TextView item_tv_title = (TextView)itemView.findViewById(R.id.item_tv_title);
        TextView item_tv_desc = (TextView)itemView.findViewById(R.id.item_tv_desc);

        InfoBean newsBean = arrayList.get(i);
//        item_img_news_photo.setImageDrawable();
        item_tv_title.setText(newsBean.title);
        item_tv_desc.setText(newsBean.desc);

        return itemView;
    }
}
