package lilongcnc.com.smartbeijing;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import lilongcnc.com.smartbeijing.utils.PrefUtils;

/**
 * Created by Lauren on 2017/4/9.
 */

public class GuideActivity extends Activity {

    //控件
    private ViewPager mViewPager;
    private LinearLayout llContainer;
    private  ImageView iconView_red;
    private Button btn_start;

    //数据
    private ArrayList<ImageView> mImageViewList;
    private int[] mImageIds = new int[] {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3}; //创建轮播图片资源数组
    private int mPointDis; //小红点的移动距离

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gudie);

        //获取控件
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        llContainer = (LinearLayout)findViewById(R.id.ll_container);
        iconView_red = (ImageView)findViewById(R.id.iv_red_point);
        btn_start = (Button)findViewById(R.id.btn_start);

        //初始化数据
        initData();

        //设置显示adpater
        mViewPager.setAdapter(new GuideAdapter());



        //监听ViewPager的滚动事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当页面滑动过程中的回调

                //更新小红点的距离
                //计算小红点当前的左边距
                int leftMargin= (int)(mPointDis*positionOffset) + position*mPointDis;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)iconView_red.getLayoutParams();
                //修改左边距
                params.leftMargin = leftMargin;

                //重新设置布局参数
                iconView_red.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImageViewList.size() -1) //最后一个页面显示按钮
                    btn_start.setVisibility(View.VISIBLE);
                else
                    btn_start.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //页面状态发生变化时候回调

            }
        });

        /*
        ● 无论是系统原生的控件还是自定义控件, 把控件真正画在界面上,需要measure(测量)->layout(确定位置)->draw 这三个方法执行完毕.
        其中layout方法执行完毕,就可以得到控件的位置了.
        ● 结合我们的项目, 我们需要计算出红点每次移动的距离.也就是两个灰点的y差值.
        =>  计算的方式,但是这个方式必须在OnCreate()执行完之后,和iOS的ViewWillAppearXX等差不多,控件得真正加载完,才能得到真实
          的值. 现在OnCreate方法里边执行,所以得不到正确的值

       */
        //计算两个圆点的距离
        //移动距离=第二个圆点left值 - 第一个圆点left值
        // measure->layout(确定位置)->draw(activity的onCreate方法执行结束之后才会走此流程)
        // mPointDis = llContainer.getChildAt(1).getLeft()
        // - llContainer.getChildAt(0).getLeft();
        // System.out.println("圆点距离:" + mPointDis);
        iconView_red.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除监听
                //Build.VERSION.SDK_INT<16
                iconView_red.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                // ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // layout方法执行结束的回调
                mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
                System.out.println("圆点距离:" + mPointDis);
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 PrefUtils.setBoolean(getApplicationContext(),"is_first_enter", false);

                 startActivity(new Intent(getApplicationContext(),MainActivity.class));
                 finish();
             }
        });



    }



    private void initData() {
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i< mImageIds.length; i++) {
            //添加VIiewPager图片
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(mImageIds[i]);
            //imageView.setImageResource(resID);
            mImageViewList.add(imageView);

            //添加小圆点
            ImageView pointIconView = new ImageView(this);
            pointIconView.setImageResource(R.drawable.shape_point_gray);

            //手码设置布局
            // 初始化布局参数, 宽高包裹内容,父控件是谁,就是谁声明的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            if (i > 0)
                params.leftMargin = 10;
            pointIconView.setLayoutParams(params);

            //添加控件
            llContainer.addView(pointIconView);
        }
    }

    private class GuideAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        //初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iconView = mImageViewList.get(position);
            container.addView(iconView);
            return  iconView;
        }


        //销毁item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
