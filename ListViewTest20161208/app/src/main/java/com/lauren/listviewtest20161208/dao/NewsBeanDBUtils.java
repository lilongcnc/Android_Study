package com.lauren.listviewtest20161208.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.lauren.listviewtest20161208.model.InfoBean;
import com.lauren.listviewtest20161208.model.NewsTempData;

import java.util.ArrayList;


/**
 * Created by Lauren on 16/12/6.
 */

public class NewsBeanDBUtils {

    private MySQliteOpenHelper mySqliteOpenHelper;
    public NewsBeanDBUtils(Context context){
        //创建一个帮助类对象
        mySqliteOpenHelper = new MySQliteOpenHelper(context);
    }

    public boolean add(InfoBean bean){

        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase 	db = mySqliteOpenHelper.getReadableDatabase();


        ContentValues values = new ContentValues();//是用map封装的对象，用来存放值
        values.put("pic_url", bean.pic_url);
        values.put("title", bean.title);
        values.put("desc", bean.desc);
        values.put("news_url", bean.news_url);

        //table: 表名 , nullColumnHack：可以为空，标示添加一个空行, values:数据一行的值 , 返回值：代表添加这个新行的Id ，-1代表添加失败
        long result = db.insert("info", null, values);//底层是在拼装sql语句

        //关闭数据库对象
        db.close();

        if(result != -1){//-1代表添加失败
            return true;
        }else{
            return false;
        }
    }

    public int del(String title){

        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        //table ：表名, whereClause: 删除条件, whereArgs：条件的占位符的参数 ; 返回值：成功删除多少行
        int result = db.delete("info", "title = ?", new String[]{title});
        //关闭数据库对象
        db.close();

        return result;

    }
    public int update(InfoBean bean){

        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        ContentValues values = new ContentValues();//是用map封装的对象，用来存放值
        values.put("title", "modify了数据");
        values.put("desc", "modify了数据描述");

        //table:表名, values：更新的值, whereClause:更新的条件, whereArgs：更新条件的占位符的值,返回值：成功修改多少行
        int result = db.update("info", values, "title = ? and desc = ?", new String[]{bean.title,bean.desc});
        //关闭数据库对象
        db.close();
        return result;

    }

    public InfoBean query(String title){

        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        //table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件, selectionArgs：条件占位符的参数值,
        //groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
        Cursor cursor = db.query("info", new String[]{"pic_url","title","desc","news_url"}, "title = ?", new String[]{title}, null, null, "title desc");

        InfoBean infoBean = new InfoBean();
        //解析Cursor中的数据
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                String temp_pic_url = cursor.getString(0);
                String temp_title = cursor.getString(1);
                String temp_desc = cursor.getString(2);
                String temp_news_url = cursor.getString(3);
                System.out.println("pic_url:"+temp_pic_url+";title:"+temp_title+";desc:"+temp_desc+"news_url"+temp_news_url);

                infoBean.pic_url = temp_pic_url;
                infoBean.title = temp_title;
                infoBean.desc = temp_desc;
                infoBean.news_url = temp_news_url;


            }
            cursor.close();//关闭结果集

        }
        //关闭数据库对象
        db.close();
        return infoBean;
    }



    public ArrayList<InfoBean> queryAll(){

        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        //table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件, selectionArgs：条件占位符的参数值,
        //groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
        Cursor cursor = db.query("info", new String[]{"pic_url","title","desc","news_url"}, null, null, null, null, null);

        ArrayList<InfoBean> infoArrayList = new ArrayList<InfoBean>();

        //解析Cursor中的数据
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                String temp_pic_url = cursor.getString(0);
                String temp_title = cursor.getString(1);
                String temp_desc = cursor.getString(2);
                String temp_news_url = cursor.getString(3);

                InfoBean oneInfo = new InfoBean();
                oneInfo.pic_url = temp_pic_url;
                oneInfo.title = temp_title;
                oneInfo.desc = temp_desc;
                oneInfo.news_url = temp_news_url;

                infoArrayList.add(oneInfo);
            }
            cursor.close();//关闭结果集

        }
        //关闭数据库对象
        db.close();
        return infoArrayList;
    }


}
