package com.brcorner.ddinaping.activity;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.fragment.BroadCastFragment;
import com.brcorner.ddinaping.fragment.SysMessageFragment;
import com.brcorner.ddinaping.fragment.UserMessageFragment;
import com.brcorner.ddinaping.view.dianpingmessage.PagerSlidingTabStrip;


import java.lang.reflect.Field;

public class MessageActivity extends FragmentActivity implements UserMessageFragment.OnFragmentInteractionListener, SysMessageFragment.OnFragmentInteractionListener, BroadCastFragment.OnFragmentInteractionListener {

    private UserMessageFragment userMessageFragment;
    private SysMessageFragment sysMessageFragment;
    private BroadCastFragment broadCastFragment;
    private PagerSlidingTabStrip tabs;
    private DisplayMetrics dm;
    private TextView center_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
    }

    public void doBack(View view)
    {
        this.finish();
    }

    public void initView() {
        center_text = (TextView) findViewById(R.id.center_text);
        setOverflowShowingAlways();
        dm = getResources().getDisplayMetrics();
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabs.setViewPager(pager);
        setTabsValue();
        changeView();
    }


    public void changeView() {
        center_text.setVisibility(View.VISIBLE);
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(Color.parseColor("#FF6633"));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        tabs.setSelectedTextColor(Color.parseColor("#FF6633"));
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = { "用户消息", "系统消息", "广播" };

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (userMessageFragment == null) {
                        userMessageFragment = new UserMessageFragment();
                    }
                    return userMessageFragment;
                case 1:
                    if (sysMessageFragment == null) {
                        sysMessageFragment = new SysMessageFragment();
                    }
                    return sysMessageFragment;
                case 2:
                    if (broadCastFragment == null) {
                        broadCastFragment = new BroadCastFragment();
                    }
                    return broadCastFragment;
                default:
                    return null;
            }
        }

    }

    public void setTitle(int i)
    {
         final String[] titles = { "用户消息", "系统消息", "广播" };
        if(i == 0)
        {
            center_text.setText(titles[0]);
        }else if(i == 1)
        {
            center_text.setText(titles[1]);
        }else if(i == 2)
        {
            center_text.setText(titles[2]);
        }
    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
