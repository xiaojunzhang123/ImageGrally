package com.zxj.imagegrally.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.zxj.imagegrally.ImageDetailFragment;
import com.zxj.imagegrally.R;
import com.zxj.imagegrally.view.HackyViewPager;

public class ImagePagerActivity extends FragmentActivity {

    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";

    private HackyViewPager mPager;
    private int pagerPosition;
    private TextView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);

        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        String[] urls = getIntent().getStringArrayExtra(EXTRA_IMAGE_URLS);
        mPager = (HackyViewPager) findViewById(R.id.pager);

        ImagePagerAdapter imagePagerAdapter=new ImagePagerAdapter(getSupportFragmentManager(),urls);

        mPager.setAdapter(imagePagerAdapter);

        indicator = (TextView) findViewById(R.id.indicator);

        String text=getString(R.string.viewpager_indicator,1,mPager.getAdapter().getCount());
        indicator.setText(text);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                String index=getString(R.string.viewpager_indicator,
                        position+1,mPager.getAdapter().getCount());
                indicator.setText(index);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if(savedInstanceState !=null){

            pagerPosition=savedInstanceState.getInt(STATE_POSITION);
        }

        mPager.setCurrentItem(pagerPosition);



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    private class  ImagePagerAdapter extends FragmentStatePagerAdapter{

        public String[] fileList;

        public ImagePagerAdapter(FragmentManager fm, String[] fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public Fragment getItem(int position) {
            String url=fileList[position];
            return ImageDetailFragment.newInstance(url);
        }

        @Override
        public int getCount() {
             return fileList == null ? 0 : fileList.length;

        }
    }
}
