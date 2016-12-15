package com.lauren.newwithnetdata20161214.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lauren.newwithnetdata20161214.model.RecommondInfo;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Lauren on 16/12/6.
 */

public class InfoDaoUtils {

    private MySQliteOpenHelper mySqliteOpenHelper;
    public InfoDaoUtils(Context context){
        //创建一个帮助类对象
        mySqliteOpenHelper = new MySQliteOpenHelper(context);

    }

    public void add(ArrayList<RecommondInfo> arraylist){

        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase 	db = mySqliteOpenHelper.getReadableDatabase();

        //循环保存
        ArrayList<RecommondInfo> arrayList = new ArrayList<RecommondInfo>();

        for (int index = 0;index < arrayList. size();index++){

            RecommondInfo recommondInfo = arrayList.get(index);

            ContentValues values = new ContentValues();//是用map封装的对象，用来存放值
            values.put("Logo", recommondInfo.Logo);
            values.put("CustomerName", recommondInfo.CustomerName);
            values.put("Intro", recommondInfo.Intro);
            values.put("TypeName", recommondInfo.TypeName);
            values.put("ReturnCredit", recommondInfo.ReturnCredit);
            values.put("SoloCreditPercent", recommondInfo.SoloCreditPercent);

            //table: 表名 , nullColumnHack：可以为空，标示添加一个空行, values:数据一行的值 , 返回值：代表添加这个新行的Id ，-1代表添加失败
            db.insert("info", null, values);//底层是在拼装sql语句

        }

        //关闭数据库对象
        db.close();
    }

    public int del(){

        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        //table ：表名, whereClause: 删除条件, whereArgs：条件的占位符的参数 ; 返回值：成功删除多少行
        int result = db.delete("info", null,null );
        //关闭数据库对象
        db.close();
        return result;
    }


    public ArrayList<RecommondInfo> query(){

        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        //table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件, selectionArgs：条件占位符的参数值,
        //groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
        Cursor cursor = db.query("info", null, null, null, null, null, null);

        ArrayList<RecommondInfo> arrayList = new ArrayList<RecommondInfo>();
        //解析Cursor中的数据
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                int id = cursor.getInt(0);
                String Logo = cursor.getString(0);
                String CustomerName = cursor.getString(1);
                String Intro = cursor.getString(2);
                String TypeName = cursor.getString(3);
                String ReturnCredit = cursor.getString(4);
                String SoloCreditPercent = cursor.getString(5);



                System.out.println("_id:"+id
                        +";Logo:"+Logo
                        +";CustomerName:"+CustomerName
                        +";Intro:"+Intro
                        +";TypeName:"+TypeName
                        +";ReturnCredit:"+ReturnCredit
                        +";SoloCreditPercent:"+SoloCreditPercent);


                RecommondInfo recommondInfo = new RecommondInfo();
                recommondInfo.Logo = Logo;
                recommondInfo.CustomerName = CustomerName;
                recommondInfo.Intro = Intro;
                recommondInfo.TypeName = TypeName;
                recommondInfo.ReturnCredit = ReturnCredit;
                recommondInfo.SoloCreditPercent = SoloCreditPercent;

                arrayList.add(recommondInfo);

            }
            cursor.close();//关闭结果集

        }
        //关闭数据库对象
        db.close();

        return  arrayList;
    }

}
