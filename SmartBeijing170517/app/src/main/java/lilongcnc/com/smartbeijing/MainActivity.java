package lilongcnc.com.smartbeijing;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import lilongcnc.com.smartbeijing.fragment.ContentFragemt;
import lilongcnc.com.smartbeijing.fragment.LeftMenuFragment;

public class MainActivity extends SlidingFragmentActivity {


    private static final  String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final  String TAG_CONTENT = "TAG_CONTENT";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        //设置侧边栏
        setBehindContentView(R.layout.activity_left_sildermenu);
        SlidingMenu slidingMenu = getSlidingMenu();
        //设置右侧边栏
//        slidingMenu.setSecondaryMenu(R.layout.activity_left_sildermenu);
//        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT); //设置右边的view
        //设置全屏触摸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置侧边栏宽度
        slidingMenu.setBehindOffset(200); //设置屏幕预留宽度


        //初始化 fragment
        initFragment();
    }

    /**
     * 初始化 fragment
     * */
    private  void initFragment() {
        //初始化管理者
        FragmentManager fm = getSupportFragmentManager();
        //开始执行事务
        FragmentTransaction transaction = fm.beginTransaction();
        //开始替换本次逻辑中的帧布局
        //参数说明: 1-> 被替换的布局 2-> 替换的布局 3->替换的自定义标记,方便之后取得,至于为什么要取得??
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(),TAG_LEFT_MENU);
        transaction.replace(R.id.fl_main,new ContentFragemt(),TAG_CONTENT);

        //提交事务
        transaction.commit();

        //根据标记来找到事务
//        fm.findFragmentByTag(TAG_LEFT_MENU)

    }
}
