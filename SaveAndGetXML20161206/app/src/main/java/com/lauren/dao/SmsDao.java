package com.lauren.dao;

import com.lauren.bean.SmsBean;

import java.util.ArrayList;

/**
 * Created by Lauren on 16/12/6.
 */

public class SmsDao {


    public static ArrayList<SmsBean> getAllList() {
        ArrayList<SmsBean> allayList = new ArrayList<SmsBean>();

        SmsBean smsBean = new SmsBean();
        smsBean.num = "7";
        smsBean.msg = "我的狂怒你驾驭不住";
        smsBean.date = "2016-10-11";
        smsBean.id = 100;
        allayList.add(smsBean);

        SmsBean smsBean1 = new SmsBean();
        smsBean1.num = "2";
        smsBean1.msg = "这是一个大师陨落的时代";
        smsBean1.date = "2016-11-06";
        smsBean1.id = 101;
        allayList.add(smsBean1);

        SmsBean smsBean2 = new SmsBean();
        smsBean2.num = "7";
        smsBean2.msg = "浪荡一生爱抖腿,除却巫山不是人";
        smsBean2.date = "2016-12-01";
        smsBean2.id = 102;
        allayList.add(smsBean2);

        return allayList;
    }
}
