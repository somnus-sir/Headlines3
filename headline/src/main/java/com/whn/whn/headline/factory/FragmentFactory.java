package com.whn.whn.headline.factory;


import android.support.v4.app.Fragment;

import com.whn.whn.headline.fragment.AllFragment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by whn on 2016/12/3.
 */

public class FragmentFactory {
    public static final String[] types = new String[]{"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};
    public static HashMap<Integer, Fragment> hashMap = new HashMap<>();
    public static int textSize = 16;//初始大小
    private static int textSizeAuthorTime = 12;
    private static boolean ifImageLoad = true;

    public  Fragment createFragment(int position) {
        Fragment fragment = null;
        fragment = hashMap.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
                case 1:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
                case 2:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
                case 3:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
                case 4:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
                case 5:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
                case 6:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
                case 7:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
                case 8:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
                case 9:
                    fragment = new AllFragment(types[position],textSize,textSizeAuthorTime,ifImageLoad);
                    break;
            }
            hashMap.put(position, fragment);
        }
        return fragment;
    }


    /**
     * 设置字体大小
     */
    public static void setTextSize(int i,int j){
        textSize = i;
        textSizeAuthorTime = j;
        Iterator it=hashMap.keySet().iterator();
        while(it.hasNext())
        {
            AllFragment fragment = (AllFragment) hashMap.get(it.next());
            fragment.setTextSize(i,j);
        }
    }

    /**
     * 控制图片是否可见
     */
    public static void controlImageVisible(boolean imageload){
        ifImageLoad = imageload;
        Iterator it2=hashMap.keySet().iterator();
        while(it2.hasNext())
        {
            AllFragment fragment = (AllFragment) hashMap.get(it2.next());
            fragment.ControlImageViewLoad(imageload);
        }
    }

}
