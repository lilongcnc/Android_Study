package lilongcnc.com.smartbeijing;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterViewAnimator;

/**
 * Created by Lauren on 2017/4/9.
 */

public class GuideActivity extends Activity {

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gudie);


        mViewPager = (ViewPager) findViewById(R.id.vp_guide);




    }



    private class GuideAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }
}
