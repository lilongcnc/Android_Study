package com.example.lauren.login;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_pw;
    private Button btn_login;
    private CheckBox cx_isSaveData;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;//记录上下文

        //获取响应的控件
        et_name = (EditText) findViewById(R.id.et_name);
        et_pw = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        cx_isSaveData = (CheckBox)findViewById(R.id.cb_saveData);

        //获取本地保存信息,设置界面的显示
        Map<String,String> savedUserInfoMap = UserInfoUtil.getSavedUserInfo(mContext);
        if (savedUserInfoMap != null) {
            et_name.setText(savedUserInfoMap.get("userName"));
            et_pw.setText(savedUserInfoMap.get("userPW"));
            cx_isSaveData.setChecked(true);
        }else
            cx_isSaveData.setChecked(false);



        //设置按钮的点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断用户是否输入账户和密码
                String name = et_name.getText().toString().trim();
                String password = et_pw.getText().toString().trim();
                //判断数据格式
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)){
                    Log.e("login data is run ", "登录为空");
                    Toast.makeText(mContext,"用户名密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }


                //检查SDK是否挂载(插上了)
                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                    Toast.makeText(mContext,"sdCard不存在或者未挂载",Toast.LENGTH_SHORT).show();
                    return;
                }


                //判断SDK的内存空间是否够存
                File sdcard_filedir = Environment.getDownloadCacheDirectory();//得到一个sccard的目录作为一个文件对象
                long usableSpace = sdcard_filedir.getUsableSpace();//获取文件目录对象的剩余空间

                if(usableSpace < 1024*1024*50){//判断是否<200M
                    //将long类型的文件大小格式化称为一个 M,G的字符串
                    String usableSpace_str = android.text.format.Formatter.formatFileSize(mContext,usableSpace);
                    Toast.makeText(mContext, "sdcard剩余空间不足,无法满足下载；剩余空间为："+usableSpace_str, Toast.LENGTH_SHORT).show();
                    return;
                }


                if (cx_isSaveData.isChecked() == true){
                    Boolean result = UserInfoUtil.saveUserInfo(mContext,name,password);

                    if (result)
                        //保存用户名和密码
                        Toast.makeText(mContext,"用户名密码保存成功",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(mContext,"用户名密码保存失败",Toast.LENGTH_SHORT).show();
                }

                //请求服务器

            }

        });




    }
}
