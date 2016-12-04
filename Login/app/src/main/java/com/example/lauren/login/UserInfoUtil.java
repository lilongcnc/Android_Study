package com.example.lauren.login;

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

    public static Boolean saveUserInfo (String name,String passWord){

        FileOutputStream outPutStream = null;

        try{
            String userInfo = name + "##"+ passWord;//不要用正则表达符号,比如**
            String saveFilePath = "data/data/com.Lauren.Login/";
            File file = new File(saveFilePath,"userInfo.txt");//创建 File
            outPutStream = new FileOutputStream(file);
            outPutStream.write(userInfo.getBytes());

            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                outPutStream.close();
            }catch (IOException e){
            }
        }
        return false;
    }


    //获取用户名字
    public static Map<String,String> getSavedUserInfo(){

        BufferedReader bReader = null;
        FileInputStream inPutStream = null;
        try{
            String saveFilePath = "/data/data/com.Lauren.Login/";
            File file = new File(saveFilePath,"userInfo.txt");
            inPutStream = new FileInputStream(file);

            bReader = new BufferedReader(new InputStreamReader(inPutStream));

            String oneLine = bReader.readLine();
            String[] split = oneLine.split("##");

            HashMap<String,String> hashMap = new HashMap<String, String>();
            hashMap.put("userName",split[0]);
            hashMap.put("userPW",split[1]);

            return hashMap;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                bReader.close();
                inPutStream.close();
            }catch (IOException e){}
        }

        return null;
    }

}
