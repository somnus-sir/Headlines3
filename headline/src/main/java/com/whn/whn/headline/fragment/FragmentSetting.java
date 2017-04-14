package com.whn.whn.headline.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.whn.whn.headline.DetailActivity;
import com.whn.whn.headline.MainActivity;
import com.whn.whn.headline.MyApplication;
import com.whn.whn.headline.R;
import com.whn.whn.headline.factory.FragmentFactory;

import java.text.DecimalFormat;
import java.util.Random;


/**
 *
 *
 */

public class FragmentSetting extends Fragment {
    public String[] arr = new String[]{"字体大小","清理缓存","加载图片","检查新版本","使用帮助","意见反馈"};
    public Context context;
    private boolean FLAG_TEXTSIZE = true;
    private boolean FLAG_IMAGELOAD = false;
    public static boolean FLAG_CACHECLEAN = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ListView lvSetting = (ListView) view.findViewById(R.id.lv_fs_set);
        lvSetting.setAdapter(new lvAdapter());

        //设置点击
        lvSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                settingMethod(i);

            }
        });

        return view;
    }

    /**
     * 设置实现的方法
     * @param i
     */
    private void settingMethod(int i) {
        switch (i){
            case 0://字体大小,主界面的字体大小更改
                if(FLAG_TEXTSIZE){
                    FragmentFactory.setTextSize(20,14);
                    Toast.makeText(context, "超大字体", Toast.LENGTH_SHORT).show();
                    FLAG_TEXTSIZE =false;
                }else{
                    FragmentFactory.setTextSize(16,12);
                    Toast.makeText(context, "普通字体", Toast.LENGTH_SHORT).show();
                    FLAG_TEXTSIZE =true;
                }
                break;
            case 1://清理缓存，随机设置
                Random random = new Random();
                double v = random.nextDouble()*3;
                DecimalFormat df = new DecimalFormat("##0.00");//格式，前面加0
                String format = df.format(v);//格式化

                if(FLAG_CACHECLEAN){
                    Toast.makeText(context, "清理缓存"+format+"M", Toast.LENGTH_SHORT).show();
                    FLAG_CACHECLEAN = false;
                }else{
                    Toast.makeText(context, "没有缓存", Toast.LENGTH_SHORT).show();
                    //关闭侧拉以后才有缓存
                }



                break;
            case 2://加载图片，控制图片加载,第一次传入false不加载
                if(FLAG_IMAGELOAD){
                    FragmentFactory.controlImageVisible(FLAG_IMAGELOAD);
                    Toast.makeText(context, "加载图片", Toast.LENGTH_SHORT).show();
                    DetailActivity.setImageLoad(FLAG_IMAGELOAD);
                    FLAG_IMAGELOAD = false;
                }else{
                    FragmentFactory.controlImageVisible(FLAG_IMAGELOAD);
                    Toast.makeText(context, "不加载图片", Toast.LENGTH_SHORT).show();
                    DetailActivity.setImageLoad(FLAG_IMAGELOAD);
                    FLAG_IMAGELOAD = true;
                }

                break;
            case 3://检查新版本，没有新版本,请求tomcat服务器
                Toast.makeText(context, "当前为最新版本", Toast.LENGTH_SHORT).show();

                break;
            case 4://使用帮助，提示，跳转一个页面，显示详细信息

                break;
            case 5://意见反馈，反馈

                break;
        }
    }

    class lvAdapter extends BaseAdapter{
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
                    view = View.inflate(viewGroup.getContext(),R.layout.item_lv_setting,null);
                }
                TextView tv = (TextView) view.findViewById(R.id.tv_item_name);
                tv.setText(arr[i]);
                ImageView iv = (ImageView) view.findViewById(R.id.iv_itemlv_ic);
                return view;
            }
        }
}
