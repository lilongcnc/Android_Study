package com.lauren.watchwebhtmlcode;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Lauren on 16/12/13.
 */

public class ResultUtils {


    public static String streamToString(InputStream in){

        String result = "";

        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;

            while ((length = in.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
                outputStream.flush();
            }


            result = outputStream.toString(); //字节转成字符串

            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        return result;
    }






}
