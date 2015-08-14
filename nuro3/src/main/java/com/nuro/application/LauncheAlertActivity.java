package com.nuro.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by nuro on 8/13/15.
 */
public class LauncheAlertActivity extends NuroBaseActivity {

    ViewPager viewPager;
    ImageView[] imageViews = new ImageView[2];
    int[] drawables = new int[] {
            R.drawable.loading,
            R.drawable.warning
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_layout);
        viewPager = (ViewPager)findViewById(R.id.alert_viewpager);
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView(this);
            imageViews[i].setBackgroundResource(drawables[i]);

        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // TODO: 8/13/15

                if (position == 1) {
                    imageViews[position].postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            Intent intent = new Intent();
                            intent.setClass(LauncheAlertActivity.this, NaviActivity.class);
                            startActivity(intent);
                        }
                    }, 3000);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return imageViews.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViews[position]);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imageViews[position]);
//                if (position == 1) {
//                    imageViews[position].postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            finish();
//                            Intent intent = new Intent();
//                            intent.setClass(LauncheAlertActivity.this, NaviActivity.class);
//                            startActivity(intent);
//                        }
//                    }, 50000);
//                }
                return imageViews[position];
            }
        });
    }
}
