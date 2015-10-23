package com.brcorner.ddinaping.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.fragment.GroupFragment;
import com.brcorner.ddinaping.fragment.IndexFragment;
import com.brcorner.ddinaping.fragment.MyFragment;
import com.brcorner.ddinaping.fragment.SearchFragment;


public class MainActivity extends FragmentActivity {

    //fragment 首页 团购 发现 我的
    private IndexFragment indexFragment;
    private GroupFragment groupFragment;
    private SearchFragment searchFragment;
    private MyFragment myFragment;

    private FragmentManager fragmentManager;

    private ImageView index_image,group_image,search_image,my_image;
    private TextView index_text,group_text,search_text,my_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();
        fragmentManager = getSupportFragmentManager();
        setTabSelection(R.id.index_rl);
    }

    private void initViews()
    {
        index_image = (ImageView) findViewById(R.id.index_image);
        group_image = (ImageView) findViewById(R.id.group_image);
        search_image = (ImageView) findViewById(R.id.search_image);
        my_image = (ImageView) findViewById(R.id.my_image);

        index_text = (TextView) findViewById(R.id.index_text);
        group_text = (TextView) findViewById(R.id.group_text);
        search_text = (TextView) findViewById(R.id.search_text);
        my_text = (TextView) findViewById(R.id.my_text);

    }

    public void doClick(View view)
    {
        switch (view.getId()) {
            case R.id.index_rl:
                setTabSelection(R.id.index_rl);
                break;
            case R.id.group_rl:
                setTabSelection(R.id.group_rl);
                break;
            case R.id.search_rl:
                setTabSelection(R.id.search_rl);
                break;
            case R.id.my_rl:
                setTabSelection(R.id.my_rl);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int layoutId)
    {
        clearTabSelection();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (layoutId) {
            case R.id.index_rl:
                index_image.setImageResource(R.drawable.main_index_home_pressed);
                index_text.setTextColor(Color.parseColor("#ff780f"));
                if(indexFragment == null)
                {
                    indexFragment = new IndexFragment();
                    fragmentTransaction.add(R.id.content_fl, indexFragment);
                }
                else
                {
                    fragmentTransaction.show(indexFragment);
                }
                break;
            case R.id.group_rl:
                group_image.setImageResource(R.drawable.main_index_tuan_pressed);
                group_text.setTextColor(Color.parseColor("#ff780f"));
                if(groupFragment == null)
                {
                    groupFragment = new GroupFragment();
                    fragmentTransaction.add(R.id.content_fl, groupFragment);
                }
                else
                {
                    fragmentTransaction.show(groupFragment);
                }
                break;
            case R.id.search_rl:
                search_image.setImageResource(R.drawable.main_index_search_pressed);
                search_text.setTextColor(Color.parseColor("#ff780f"));
                if(searchFragment == null)
                {
                    searchFragment = new SearchFragment();
                    fragmentTransaction.add(R.id.content_fl, searchFragment);
                }
                else
                {
                    fragmentTransaction.show(searchFragment);
                }
                break;
            case R.id.my_rl:
                my_image.setImageResource(R.drawable.main_index_my_pressed);
                my_text.setTextColor(Color.parseColor("#ff780f"));
                if(myFragment == null)
                {
                    myFragment = new MyFragment();
                    fragmentTransaction.add(R.id.content_fl, myFragment);
                }
                else
                {
                    fragmentTransaction.show(myFragment);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    private void clearTabSelection()
    {
        index_text.setTextColor(Color.parseColor("#82858b"));
        index_image.setImageResource(R.drawable.main_index_home_normal);
        group_text.setTextColor(Color.parseColor("#82858b"));
        group_image.setImageResource(R.drawable.main_index_tuan_normal);
        search_text.setTextColor(Color.parseColor("#82858b"));
        search_image.setImageResource(R.drawable.main_index_search_normal);
        my_text.setTextColor(Color.parseColor("#82858b"));
        my_image.setImageResource(R.drawable.main_index_my_normal);
    }

    private void hideFragment(FragmentTransaction fragmentTransaction)
    {
        if(indexFragment != null)
        {
            fragmentTransaction.hide(indexFragment);
        }
        if(groupFragment != null)
        {
            fragmentTransaction.hide(groupFragment);
        }
        if(searchFragment != null)
        {
            fragmentTransaction.hide(searchFragment);
        }
        if(myFragment != null)
        {
            fragmentTransaction.hide(myFragment);
        }
    }
}
