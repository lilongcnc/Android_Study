package com.lauren.newwithnetdata20161214.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lauren.newwithnetdata20161214.StreamToStringUtils;
import com.lauren.newwithnetdata20161214.dao.InfoDaoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Lauren on 16/12/15.
 */

public class NewsUtils {


    public static String url_str = "http://106.3.228.106:1800/JoinCustomer.ashx?action=getpromotioncustomer&Latitude=40.062614&UserAccount=13601163181&BusinessAreaID=BA011411270001&Province=北京市&Longitude=116.305634&version=2.0&City=北京市";


    public static ArrayList<RecommondInfo> getAllNewsForNetWork(Context mContext){

        ArrayList<RecommondInfo> arrayList = new ArrayList<RecommondInfo>();

        try {
            URL url = new URL(url_str);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置请求方法和超时事件
            connection.setConnectTimeout(1000*10);
            connection.setRequestMethod("GET");

            /**
             action = getpromotioncustomer;
             Latitude = 40.062614;
             UserAccount = 13601163181;
             BusinessAreaID = BA011411270001;
             Province = 北京市;
             Longitude = 116.305634;
             version = 2.0;
             City = 北京市;
             * */

            //获取请求的事件
            int code = connection.getResponseCode();
            if (code == 200){
                //请求成功
                InputStream inputStream = connection.getInputStream();
                String result_str = StreamToStringUtils.streamToString(inputStream);
                Log.e("xxxxxxxx",result_str);

                //**** 下边解析服务器数据部分
                JSONObject root_JSONObject = new JSONObject(result_str);
                //返回数据成功标识
                String requestFlag = root_JSONObject.getString("flag");

                if (requestFlag.equals("1")){

                    //请求结果正确
                    JSONArray jsonArray = root_JSONObject.getJSONArray("list");
                    Log.e("xxxxxxxx",jsonArray.length()+"");

                    for (int index = 0;index < jsonArray.length();index++){
                        Log.e("for循环",index+"");

                        JSONObject tempJSONObject = jsonArray.getJSONObject(index);

                        RecommondInfo recommondInfo = new RecommondInfo();

                        recommondInfo.Logo = tempJSONObject.getString("FacePhoto");
                        recommondInfo.CustomerName = tempJSONObject.getString("CustomerName");
                        recommondInfo.Intro = tempJSONObject.getString("Intro");
                        recommondInfo.TypeName = tempJSONObject.getString("TypeName");
                        recommondInfo.Distance = tempJSONObject.getString("Distance");
                        recommondInfo.Discount = tempJSONObject.getString("Discount");
                        recommondInfo.Coupon = tempJSONObject.getString("Coupon");
                        recommondInfo.ReturnCredit = tempJSONObject.getString("ReturnCredit");
                        recommondInfo.SoloCreditPercent = tempJSONObject.getString("SoloCreditPercent");
                        recommondInfo.CustomerID = tempJSONObject.getString("CustomerID");

                        Log.e("xxxx",recommondInfo.CustomerName);

                        arrayList.add(recommondInfo);
                    }


                    //***  保存到数据库
                    //保存时候先删除之前的数据,然后再将新的数据存储到数据库中
                    new InfoDaoUtils(mContext).del();
                    new InfoDaoUtils(mContext).add(arrayList);

                    Log.e("getAllNewsForNetWork", "getAllNewsForNetWork: 网络请求完成");

                }else
                    Log.e("getAllNewsForNetWork", "getAllNewsForNetWork: 网络请求异常");

            }
            else
                Log.e("getAllNewsForNetWork", "getAllNewsForNetWork: 网络请求失败");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }


    //从数据库中获取上次缓存的新闻数据做listview的展示
    public  static ArrayList<RecommondInfo> getAllNewsForDatabase(Context context) {
        return new InfoDaoUtils(context).query();
    }

}
