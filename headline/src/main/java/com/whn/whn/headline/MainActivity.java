package com.whn.whn.headline;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whn.whn.headline.factory.FragmentFactory;
import com.whn.whn.headline.fragment.FragmentLeft;
import com.whn.whn.headline.fragment.FragmentSetting;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 加载具体页面的item
 *
 */
public class MainActivity extends AppCompatActivity {
    public static final String[] typeNames = new String[]{"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
//    @Bind(R.id.drawerlayout)
    public static DrawerLayout drawerlayout;
    @Bind(R.id.ll_fragmnet_left)
    LinearLayout ll_fragment_left;
    PagerAdapter pagerAdapter;
    FragmentFactory ff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ff = new FragmentFactory();
        ButterKnife.bind(this);

        toolbar.setTitle("");
//        toolbar.setTitleTextColor(Color.WHITE);

        //设置Viewpagerff
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);
        //关联指针
        tabLayout.setupWithViewPager(vp);

        //左侧菜单
        setSupportActionBar(toolbar);
        // 参数：开启抽屉的activity、DrawerLayout的对象、toolbar按钮打开关闭的对象、描述open drawer、描述close drawer
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar,0, 0);
        // 添加抽屉按钮，通过点击按钮实现打开和关闭功能; 如果不想要抽屉按钮，只允许在侧边边界拉出侧边栏，可以不写此行代码
        mDrawerToggle.syncState();
        // 设置按钮的动画效果; 如果不想要打开关闭抽屉时的箭头动画效果，可以不写此行代码
        drawerlayout.setDrawerListener(mDrawerToggle);

        drawerlayout.closeDrawers();


        //监听侧拉
        drawerlayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                pagerAdapter.notifyDataSetChanged();//关闭侧拉，刷新列表
                FragmentSetting.FLAG_CACHECLEAN = true;//产生缓存
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });



        //左侧侧拉栏
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.ll_fragmnet_left,new FragmentSetting(),"left");
        ft.commit();


    }



    /**
     * Adapter
     */
    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //根据创建Fragment
        @Override
        public Fragment getItem(int position) {
            return ff.createFragment(position);
        }

        @Override
        public int getCount() {
            return typeNames.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return typeNames[position];
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            //判断侧拉菜单是开启还是关闭
            if (drawerlayout.isDrawerOpen(Gravity.LEFT)){
                drawerlayout.closeDrawer(Gravity.LEFT);
            }else{
                drawerlayout.openDrawer(Gravity.LEFT);
            }
        }
        return super.onOptionsItemSelected(item);
    }




}
