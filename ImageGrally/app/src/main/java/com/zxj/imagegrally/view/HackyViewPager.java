package com.zxj.imagegrally.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by apple on 16/9/13.
 */
public class HackyViewPager extends ViewPager {

    private static final String TAG = "HackyViewPager";

    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        try {
            return super.onInterceptHoverEvent(event);
        }catch (IllegalArgumentException e){

            Log.e(TAG,"hacky viewpager error1");
            return false;
        }catch (ArrayIndexOutOfBoundsException e){

            //不理会
            Log.e(TAG,"hacky viewpager error2");
            return false;

        }






    }
}
