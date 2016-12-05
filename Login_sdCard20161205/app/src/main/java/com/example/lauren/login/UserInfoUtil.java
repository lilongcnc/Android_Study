package com.example.lauren.login;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lauren on 16/12/4.
 */

public class UserInfoUtil {

    public static Boolean saveUserInfo (Context context, String name, String passWord){

        FileOutputStream outPutStream = null;

        try{
            String userInfo = name + "##"+ passWord;//不要用正则表达符号,比如**

            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path,"userInfo.txt");//创建 File

            Log.e("保存地址",file.getPath());
            outPutStream = new FileOutputStream(file);
            outPutStream.write(userInfo.getBytes());
            outPutStream.close();

            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        }
        return false;
    }


    //获取用户名字
    public static Map<String,String> getSavedUserInfo(Context context){

        BufferedReader bReader = null;
        FileInputStream inPutStream = null;
        try{
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path,"userInfo.txt");

            inPutStream = new FileInputStream(file);

            bReader = new BufferedReader(new InputStreamReader(inPutStream));

            String oneLine = bReader.readLine();
            String[] split = oneLine.split("##");

            HashMap<String,String> hashMap = new HashMap<String, String>();
            hashMap.put("userName",split[0]);
            hashMap.put("userPW",split[1]);

            inPutStream.close();
            bReader.close();
            return hashMap;

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

        return null;
    }

}
