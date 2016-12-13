package com.lauren.listviewtest20161208.model;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


import com.lauren.listviewtest20161208.adapter.NewsViewAdapter;
import com.lauren.listviewtest20161208.dao.NewsBeanDBUtils;


public class NewsTempData {

	private Context mContext;
	private NewsBeanDBUtils mDBUtils;
	private ListView mListView;
	public NewsTempData(Context context,ListView listView){
		this.mContext = context;
		this.mListView = listView;
	}


	//封装新闻的假数据到list中返回
	public ArrayList<InfoBean> getAllNews() {

		ArrayList<InfoBean> arrayList = new ArrayList<InfoBean>();

		{
			InfoBean newsBean = new InfoBean();
			newsBean.title ="标题1";
			newsBean.desc= "称谢霆锋隐私权收到侵犯，将保留追究法律责任";
			newsBean.news_url= "http://www.sina.cn";
//			newsBean.icon = context.getResources().getDrawable(R.drawable.ic_launcher);//通过context对象将一个资源id转换成一个Drawable对象。
			arrayList.add(newsBean);


			InfoBean newsBean1 = new InfoBean();
			newsBean1.title ="标题2";
			newsBean1.desc= "身边的人都知道谢霆锋最爱王菲，二人早有复合迹象";
			newsBean1.news_url= "http://www.baidu.cn";
//			newsBean1.icon = context.getResources().getDrawable(R.drawable.icon);//通过context对象将一个资源id转换成一个Drawable对象。
			arrayList.add(newsBean1);


			InfoBean newsBean2 = new InfoBean();
			newsBean2.title ="标题3";
			newsBean2.desc= "74期平均薪资20000，其中有一个哥们超过10万，这些It精英都迎娶了白富美.";
			newsBean2.news_url= "http://www.itheima.com";
//			newsBean2.icon = context.getResources().getDrawable(R.drawable.icon2);//通过context对象将一个资源id转换成一个Drawable对象。
			arrayList.add(newsBean2);


			InfoBean newsBean4 = new InfoBean();
			newsBean4.title = "标题4";
			newsBean4.desc= "哈哈哈哈哈哈哈哈哈 .";
			newsBean4.news_url= "http://www.itheima.com";
			arrayList.add(newsBean4);

		}
		return arrayList;
	}


	//获取数据库数据
	public void queryDBAndRefreshView() {
		mDBUtils = new NewsBeanDBUtils(mContext);

		ArrayList<InfoBean> infoBeanList = mDBUtils.queryAll();
		//刷新表格显示
		NewsViewAdapter adapter = (NewsViewAdapter)mListView.getAdapter();
		adapter.arrayList = infoBeanList;
		adapter.notifyDataSetChanged();

		Toast.makeText(mContext, "正在更新数据", Toast.LENGTH_SHORT).show();
	}


	//增加数据
	public void addDataToDBAndRefreshView(){
		mDBUtils = new NewsBeanDBUtils(mContext);

		ArrayList<InfoBean> arrayList = null;

		if (mDBUtils.queryAll().size() == 0)
			arrayList = this.getAllNews();
		else 
			arrayList = mDBUtils.queryAll();

		Log.e("222222222",mDBUtils.queryAll().size()+"");

		for (InfoBean oneInfobean : arrayList) {
			mDBUtils.add(oneInfobean);
		}

		this.refreshData();
	}



	//删除数据
	public void dealDataToDBAndRefreshView(String title){
		mDBUtils = new NewsBeanDBUtils(mContext);

		mDBUtils.del(title);

		this.refreshData();
	}

	//删除数据
	public void modifyDataToDBAndRefreshView(String title){
		mDBUtils = new NewsBeanDBUtils(mContext);

		InfoBean infoBean = mDBUtils.query(title);
		mDBUtils.update(infoBean);

		this.refreshData();
	}


	//更新列表数据
	private void refreshData(){
		this.queryDBAndRefreshView();
	}

}
