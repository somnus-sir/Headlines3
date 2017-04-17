package com.whn.whn.headline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/17.
 */

public class HelpActivity extends AppCompatActivity {
    int[] arr = new int[]{R.mipmap.help1, R.mipmap.help2, R.mipmap.help3,R.mipmap.help4};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button btBack = (Button) findViewById(R.id.bt_help_back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ListView lvHelp = (ListView) findViewById(R.id.lv_help);
        lvHelp.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return arr.length;
            }

            @Override
            public Object getItem(int i) {
                return i;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = View.inflate(viewGroup.getContext(), R.layout.item_lv_help, null);
                }
                ImageView image = (ImageView) view.findViewById(R.id.iv_help_ic);
                image.setImageResource(arr[i]);
                return view;
            }
        });
    }


}
