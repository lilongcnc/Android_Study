package com.lauren.listviewtest20161208.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lauren.listviewtest20161208.model.InfoBean;

import java.util.ArrayList;

/**
 * Created by Lauren on 16/12/9.
 */

public class NewsViewAdapter extends BaseAdapter{

    private  ArrayList<InfoBean> arrayList;
    public NewsViewAdapter(ArrayList<InfoBean> list){
        this.arrayList = list;
    }


    @Override
    public int getCount() {
        return arrayList.size();
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



        return null;
    }
}
