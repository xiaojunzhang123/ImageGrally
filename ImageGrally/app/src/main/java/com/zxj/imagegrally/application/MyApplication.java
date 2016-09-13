package com.zxj.imagegrally.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zxj.imagegrally.R;

/**
 * Created by apple on 16/9/13.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DisplayImageOptions displayImageOptions=new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.empty_photo)
                .showImageOnFail(R.mipmap.empty_photo)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();

        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(displayImageOptions)
                .discCacheSize(50 * 1024 *1024)
                .discCacheFileCount(100)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(configuration);

    }
}
