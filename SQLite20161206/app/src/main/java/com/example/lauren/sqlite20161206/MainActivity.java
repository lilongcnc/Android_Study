package com.example.lauren.sqlite20161206;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
  switch (view.getId()){
      case R.id.btn_add: {
          InfoDao infoDao = new InfoDao(mContext);

          InfoBean infoBean = new InfoBean();
          infoBean.name = "张三";
          infoBean.phone = "1340000000";
          infoDao.add(infoBean);

          InfoBean infoBean1 = new InfoBean();
          infoBean1.name = "李四";
          infoBean1.phone = "1300000000";
          infoDao.add(infoBean1);

          break;
      }
      case R.id.btn_del: {
          InfoDao infoDao = new InfoDao(mContext);
          infoDao.del("张三");

          break;
      }
      case R.id.btn_query: {
          InfoDao infoDao = new InfoDao(mContext);
          infoDao.query("李四");
          infoDao.query("张三");
          break;
      }
      case R.id.btn_modify: {
          InfoDao infoDao = new InfoDao(mContext);
          InfoBean infoBean1 = new InfoBean();
          infoBean1.name = "李四";
          infoBean1.phone = "1390000000";
          infoDao.update(infoBean1);
          break;
      }
      default:
          break;
  }
    }
}
