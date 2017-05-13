package lilongcnc.com.smartbeijing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //设置侧边栏
        setBehindContentView(R.layout.left_sildermenu);
        SlidingMenu slidingMenu = getSlidingMenu();
        //设置右侧边栏
        slidingMenu.setSecondaryMenu(R.layout.left_sildermenu);
//        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT); //设置右边的view

        //设置全屏触摸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //设置侧边栏宽度
        slidingMenu.setBehindOffset(200); //设置屏幕预留宽度


    }
}
