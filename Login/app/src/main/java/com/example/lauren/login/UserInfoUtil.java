package com.example.lauren.login;

/**
 * Created by Lauren on 16/12/4.
 */

public class UserInfoUtil {

    public Boolean saveUserInfo (String name,String passWord){
        Boolean isSaveSuccess;
        isSaveSuccess = ture;

        String userInfo = name + "##"+ passWord;//不要用正则表达符号,比如**


        return isSaveSuccess;
    }

}
