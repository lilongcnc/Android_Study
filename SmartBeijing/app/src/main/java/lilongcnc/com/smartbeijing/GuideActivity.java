package lilongcnc.com.smartbeijing;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Lauren on 2017/4/9.
 */

public class GuideActivity extends Activity {

    private ViewPager mViewPager;
    private ArrayList<ImageView> mImageViewList;
    private int[] mImageIds = new int[] {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3}; //创建轮播图片资源数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gudie);

        //获取控件
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);

        //初始化数据
        initData();


        //设置显示adpater
        mViewPager.setAdapter(new GuideAdapter());
    }



    private void initData() {
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i< mImageIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(mImageIds[i]);
            //imageView.setImageResource(resID);
            mImageViewList.add(imageView);

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
