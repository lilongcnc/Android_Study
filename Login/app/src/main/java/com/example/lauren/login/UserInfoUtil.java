package com.example.lauren.login;

import android.content.Context;
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
//            String saveFilePath = "/data/data/com.Lauren.Login/";
//            String path = context.getFilesDir().getPath();
//            File file = new File(path,"userInfo.txt");//创建 File
//            outPutStream = new FileOutputStream(file);
//            Log.e("保存地址",file.getPath());

            //通过context对象得到私有目录下一个文件写入流； name : 私有目录文件的名称    mode： 文件的操作模式， 私有，追加，全局读，全局写
            outPutStream = context.openFileOutput("userInfo.txt",Context.MODE_APPEND);
            outPutStream.write(userInfo.getBytes());
            outPutStream.close();

            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            try{
//            }catch (IOException e){
//            }lilobng
        }
        return false;
    }


    //获取用户名字
    public static Map<String,String> getSavedUserInfo(Context context){

        BufferedReader bReader = null;
        FileInputStream inPutStream = null;
        try{
//            String saveFilePath = "/data/data/com.Lauren.Login/";
//            String path = context.getFilesDir().getPath();
//            File file = new File(path,"userInfo.txt");
//            inPutStream = new FileInputStream(file);

            //通过context对象获取一个私有目录的文件读取流  /data/data/packagename/files/userinfoi.txt
            inPutStream= context.openFileInput("userInfo.txt");
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
