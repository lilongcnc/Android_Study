package lilongcnc.com.smartbeijing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import lilongcnc.com.smartbeijing.utils.PrefUtils;


/**
 * Created by Lauren on 2017/4/8.
 */

public class SplashActivity extends Activity {

    private RelativeLayout rlRoot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);

        //旋转动画
        RotateAnimation animRoatate = new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        animRoatate.setDuration(2000);
        animRoatate.setFillAfter(true); //保持动画结束时候的状态

        //缩放动画
        ScaleAnimation animScale = new ScaleAnimation(0,1,0,1,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f
        );
        animScale.setDuration(2000);
        animScale.setFillAfter(true);

        //渐变动画
        AlphaAnimation animAplha = new AlphaAnimation(0,1);
        animAplha.setDuration(2000);
        animScale.setFillAfter(true);

        //动画集合
        AnimationSet animSet = new AnimationSet(true);
        animSet.addAnimation(animRoatate);
        animSet.addAnimation(animScale);
        animSet.addAnimation(animAplha);



        //执行动画
        rlRoot.startAnimation(animSet);


        //监听动画执行的状态
        animSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Boolean flag = PrefUtils.getBoolean(getApplicationContext(),"is_first_enter",true);
                Intent intent;
                if (flag)
                {
                    //动画结束后,进入新手引导页
                    intent = new Intent(getApplicationContext(),GuideActivity.class);
                    startActivity(intent);
                    finish(); //跳转后结束当前页面
                }
                else
                {
                    //直接进入主页面
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish(); //跳转后结束当前页面

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
