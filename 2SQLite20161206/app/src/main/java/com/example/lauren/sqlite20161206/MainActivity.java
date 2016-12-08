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
}
