package com.whn.whn.headline.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.whn.whn.headline.AgreementActivity;
import com.whn.whn.headline.DetailActivity;
import com.whn.whn.headline.HelpActivity;
import com.whn.whn.headline.MainActivity;
import com.whn.whn.headline.R;
import com.whn.whn.headline.factory.FragmentFactory;
import com.whn.whn.headline.http.CheckVersionUpdata;

import java.text.DecimalFormat;
import java.util.Random;




public class FragmentSetting extends Fragment {
    public String[] arr = new String[]{"字体大小", "清理缓存", "加载图片", "检查新版本", "使用帮助", "评价反馈"};
    public Activity activity;
    private boolean FLAG_TEXTSIZE = true;
    private boolean FLAG_IMAGELOAD = false;
    public static boolean FLAG_CACHECLEAN = true;
    private String[] sexArry = new String[]{"很好用，我给五星好评","一般般，还可以","很糟糕，我很不满意"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        Button btBack = (Button) view.findViewById(R.id.bt_fs_back);
        ListView lvSetting = (ListView) view.findViewById(R.id.lv_fs_set);
        Button btAgreement = (Button) view.findViewById(R.id.bt_fs_xieyi);


        //点击关闭侧拉
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawerlayout.closeDrawers();
            }
        });


        //点击打开协议
        btAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, AgreementActivity.class));
            }
        });


        lvSetting.setAdapter(new lvAdapter());
        //listview设置点击
        lvSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                settingMethod(i);

            }
        });

        return view;
    }

    /**
     * 设置功能实现的方法
     */
    private void settingMethod(int i) {
        switch (i) {
            case 0://字体大小,主界面的字体大小更改
                if (FLAG_TEXTSIZE) {
                    FragmentFactory.setTextSize(20, 14);
                    Toast.makeText(activity, "大号字体", Toast.LENGTH_SHORT).show();
                    FLAG_TEXTSIZE = false;
                } else {
                    FragmentFactory.setTextSize(16, 12);
                    Toast.makeText(activity, "普通字体", Toast.LENGTH_SHORT).show();
                    FLAG_TEXTSIZE = true;
                }
                break;

            case 1://清理缓存，随机设置
                Random random = new Random();
                double v = random.nextDouble() * 3;
                DecimalFormat df = new DecimalFormat("##0.00");//格式，前面加0
                String format = df.format(v);//格式化

                if (FLAG_CACHECLEAN) {
                    Toast.makeText(activity, "清理缓存" + format + "M", Toast.LENGTH_SHORT).show();
                    FLAG_CACHECLEAN = false;
                } else {
                    Toast.makeText(activity, "没有缓存", Toast.LENGTH_SHORT).show();
                    //关闭侧拉以后才有缓存
                }
                break;

            case 2://加载图片，控制图片加载,第一次传入false不加载
                if (FLAG_IMAGELOAD) {
                    FragmentFactory.controlImageVisible(FLAG_IMAGELOAD);
                    Toast.makeText(activity, "加载图片", Toast.LENGTH_SHORT).show();
                    DetailActivity.setImageLoad(FLAG_IMAGELOAD);
                    FLAG_IMAGELOAD = false;
                } else {
                    FragmentFactory.controlImageVisible(FLAG_IMAGELOAD);
                    Toast.makeText(activity, "不加载图片", Toast.LENGTH_SHORT).show();
                    DetailActivity.setImageLoad(FLAG_IMAGELOAD);
                    FLAG_IMAGELOAD = true;
                }
                break;

            case 3://检查新版本，没有新版本,请求tomcat服务器
                CheckVersionUpdata checkVersionUpdata = new CheckVersionUpdata(activity);
                checkVersionUpdata.checkVersion();
                break;

            case 4://使用帮助，提示，跳转一个页面，显示详细信息
                startActivity(new Intent(activity, HelpActivity.class));
                break;

            case 5://意见反馈
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);// 自定义对话框
                builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                        Toast.makeText(activity, "感谢您的评价", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
                    }
                });
                builder.show();// 让弹出框显示
                break;
        }


    }

    class lvAdapter extends BaseAdapter {
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
            if (view == null) {
                view = View.inflate(viewGroup.getContext(), R.layout.item_lv_setting, null);
            }
            TextView tv = (TextView) view.findViewById(R.id.tv_item_name);
            tv.setText(arr[i]);
            ImageView iv = (ImageView) view.findViewById(R.id.iv_itemlv_ic);
            return view;
        }
    }


}



