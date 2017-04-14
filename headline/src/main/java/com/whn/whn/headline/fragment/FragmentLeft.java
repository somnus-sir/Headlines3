package com.whn.whn.headline.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.whn.whn.headline.R;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/4/11.
 * 焦点问题，torbar任然可以点击
 * listview点击不够宽
 * 
 *
 *
 */

public class FragmentLeft extends Fragment {

    public Activity activity;

    public String[] arr = new String[]{"好友动态","我的话题","收藏","活动","商城","反馈","我要爆料"};
    public int[] ic = new int[]{R.mipmap.haoyoudongtai,R.mipmap.wodehuati,R.mipmap.shoucang,R.mipmap.huodong,R.mipmap.shangcheng,R.mipmap.fankui,R.mipmap.woyaobaoliao};


    public FragmentLeft(){

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        ListView lvfl = (ListView) view.findViewById(R.id.lv_fl);
        Button bt = (Button) view.findViewById(R.id.bt_fl_down);
        lvfl.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return arr.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view == null){
                    view = View.inflate(container.getContext(),R.layout.item_lv,null);
                }
                TextView tv = (TextView) view.findViewById(R.id.tv_item_name);
                tv.setText(arr[i]);
                ImageView iv = (ImageView) view.findViewById(R.id.iv_itemlv_ic);
                iv.setImageResource(ic[i]);
                return view;
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
