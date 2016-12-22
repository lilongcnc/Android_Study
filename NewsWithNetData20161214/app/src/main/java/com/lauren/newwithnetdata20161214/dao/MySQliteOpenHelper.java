package com.lauren.newwithnetdata20161214.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lauren on 16/12/6.
 */

public class MySQliteOpenHelper  extends SQLiteOpenHelper{

    public MySQliteOpenHelper(Context context) {

        //context :上下文   ， name：数据库文件的名称    factory：用来创建cursor对象，默认为null
        //version:数据库的版本号，从1开始，如果发生改变，onUpgrade方法将会调用,4.0之后只能升不能将
        super(context, "tb_homeRecommondInfo.db", null,1);
    }

    //oncreate方法是数据库第一次创建的时候会被调用;  特别适合做表结构的初始化,需要执行sql语句；SQLiteDatabase db可以用来执行sql语句
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("xxxxxxxx","xxxxxxxxxxxx");
        System.out.println(db);

//        db.execSQL("create table news (_id integer  ,title varchar(200),des varchar(300),icon_url varchar(200),news_url varchar(200)," +
//                " type integer , time varchar(100),comment integer)");

        //通过SQLiteDatabase执行一个创建表的sql语句
//        db.execSQL("create table info (_id integer," +
//                "logo varchar(200)," +
//                "CustomerName varchar(200)," +
//                "Intro varchar(200)," +
//                "TypeName varchar(200)," +
//                "ReturnCredit varchar(200)," +
//                "SoloCreditPercent varchar(200))");


        db.execSQL("create table info (_id integer," +
                "logo text not null," +
                "CustomerName text not null," +
                "Intro text not null," +
                "TypeName text not null," +
                "ReturnCredit text not null," +
                "SoloCreditPercent text not null)");
    }

    //onUpgrade数据库版本号发生改变时才会执行； 特别适合做表结构的修改
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
