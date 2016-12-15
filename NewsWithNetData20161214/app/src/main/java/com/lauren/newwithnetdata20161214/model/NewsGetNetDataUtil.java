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

public class NewsGetNetDataUtil {

    public static ArrayList<RecommondInfo> requestAndGetRecommondInfoArrayList(Context mContext){

        ArrayList<RecommondInfo> arrayList = new ArrayList<RecommondInfo>();

        try {
            String url_str = "http://106.3.228.106:1800/JoinCustomer.ashx?action=getpromotioncustomer&Latitude=40.062614&UserAccount=13601163181&BusinessAreaID=BA011411270001&Province=北京市&Longitude=116.305634&version=2.0&City=北京市";
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

                JSONObject rootJSONObject = new JSONObject(result_str);

                String requestFlag = rootJSONObject.getString("flag");
                if (requestFlag.equals("1")){
                    //请求结果正确
                    JSONArray jsonArray = rootJSONObject.getJSONArray("list");


                    for (int index = 0;index < jsonArray.length();index++){

                        JSONObject tempJSONObject = (JSONObject) jsonArray.get(index);

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

                    //***保存到数据库
                    new InfoDaoUtils(mContext).del(); //保存时候先删除之前的数据
                    new InfoDaoUtils(mContext).add(arrayList);
                }else
                    Toast.makeText(mContext,"请求结果异常",Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(mContext,"网络异常,请求失败",Toast.LENGTH_SHORT).show();


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
