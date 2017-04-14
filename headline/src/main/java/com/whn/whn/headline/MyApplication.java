package com.whn.whn.headline;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/4/13.
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }
    //返回
    public static Context getContextObject(){
        return context;
    }

}
