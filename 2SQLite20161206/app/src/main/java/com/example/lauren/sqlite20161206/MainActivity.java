package com.example.lauren.sqlite20161206;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lauren.bao.InfoBean;
import com.example.lauren.bean.InfoDao;
import com.example.lauren.sqlite20161206.database.MySQliteOpenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private  MySQliteOpenHelper mySQliteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        mySQliteOpenHelper = new MySQliteOpenHelper(mContext);
        SQLiteDatabase db = mySQliteOpenHelper.getReadableDatabase();


        Button btn_add = (Button)findViewById(R.id.btn_add);
        Button btn_del = (Button)findViewById(R.id.btn_del);
        Button btn_query = (Button)findViewById(R.id.btn_query);
        Button btn_modify = (Button)findViewById(R.id.btn_modify);



        btn_add.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_modify.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        InfoDao infoDao = new InfoDao(mContext);//创建一个dao对象做增删改查

        switch (view.getId()) {
            case R.id.btn_add:

                InfoBean bean = new InfoBean();
                bean.name = "张三";
                bean.phone ="110";
                boolean result = infoDao.add(bean);
                if(result){
                    Toast.makeText(mContext, "添加成功", 0).show();
                }else{
                    Toast.makeText(mContext, "添加失败", 0).show();
                }


                break;

            case R.id.btn_del:

                int del = infoDao.del("张三");
                Toast.makeText(mContext, "成功删除"+del+"行", 0).show();
                break;

            case R.id.btn_modify:

                InfoBean bean2 = new InfoBean();
                bean2.name = "张三";
                bean2.phone ="119";
                int update = infoDao.update(bean2);
                Toast.makeText(mContext, "成功修改"+update+"行", 0).show();
                break;


            case R.id.btn_query:
                infoDao.query("张三");
                break;

            default:
                break;
        }
    }
//        //1.创建一个帮助类的对象
//        BankOpenHelper bankOpenHelper = new BankOpenHelper(this);
//        //2.调用数据库帮助类对象的getReadableDatabase创建数据库，初始化表数据，获取一个SqliteDatabase对象去做转账（sql语句）
//        SQLiteDatabase db = bankOpenHelper.getReadableDatabase();
//        //3.转账,将李四的钱减200，张三加200
//        db.beginTransaction();//开启一个数据库事务
//        try {
//            db.execSQL("update account set money= money-200 where name=?",new String[]{"李四"});
//            int i = 100/0;//模拟一个异常
//            db.execSQL("update account set money= money+200 where name=?",new String[]{"张三"});
//
//            db.setTransactionSuccessful();//标记事务中的sql语句全部成功执行
//        } finally {
//            db.endTransaction();//判断事务的标记是否成功，如果不成功，回滚错误之前执行的sql语句
//        }



}
