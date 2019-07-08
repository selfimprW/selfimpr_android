package com.selfimpr.android.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * description：   <br/>
 * ===============================<br/>
 * creator：Jiacheng<br/>
 * create time：2019-07-08 12:12<br/>
 * ===============================<br/>
 * reasons for modification：  <br/>
 * Modifier：  <br/>
 * Modify time：  <br/>
 */
public class ViewPagerActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewPager viewPager = new ViewPager(this);
        setContentView(viewPager);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                super.instantiateItem(container, position);
                TextView textView = new TextView(container.getContext());
                textView.setGravity(Gravity.CENTER);
                textView.setText(String.valueOf(position));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                container.addView(textView);
                return textView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((View) object);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *
             * @param position Position index of the first page currently being displayed.Page position+1 will be visible if positionOffset is nonzero.
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("ViewPager", "-->onPageScrolled : position = " + position + " , positionOffset = " + positionOffset + " , positionOffsetPixels = " + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("ViewPager", "-->onPageSelected : position = " + position);
            }

            /**
             * Called when the scroll state changes. Useful for discovering when the user
             * begins dragging, when the pager is automatically settling to the current page,
             * or when it is fully stopped/idle.
             *
             * @param state The new scroll state.
             * @see ViewPager#SCROLL_STATE_IDLE
             * @see ViewPager#SCROLL_STATE_DRAGGING
             * @see ViewPager#SCROLL_STATE_SETTLING
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("ViewPager", "-->onPageScrollStateChanged : state = " + state);
            }
        });
    }
}
