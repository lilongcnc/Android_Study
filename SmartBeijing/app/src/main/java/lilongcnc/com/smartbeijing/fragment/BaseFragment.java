package lilongcnc.com.smartbeijing.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lauren on 2017/5/15.
 */

public abstract class BaseFragment extends Fragment {

    /*
        OnCreate(): 在这个方法中可以拿到Fragment依赖的父Activity
        OnCreateView():  Fragment在里边初始化布局
        OnActivityCreated():  Fragment创建成功之后
    */

    public Activity mActivity; //BaseFragment就是卸载 MainActivity 中的,所以这里的 mActivity也就是MainActivity

    //在折个方法中获取到 Fragement 依赖的父 Acvivity
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取父 Activity->MainActivity
         mActivity = getActivity();

    }


    //在这个方法中,初始化fragment 的布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //初始化界面
        View view = initView();
        return view;
    }


    //表示当前 fragment依赖的父 Activity 中的 onCreate方法执行完了,也就是说 fragment 创建成功了
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化数据
        initData();
    }

    //初始化布局,必须子类实现
    public abstract View initView();

    //初始化数据,必须子类实现.
    public abstract void initData();

}

