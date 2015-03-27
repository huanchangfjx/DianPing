package com.brcorner.ddinaping.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.fragment.AccountFragment;
import com.brcorner.ddinaping.fragment.DiscoverFragment;
import com.brcorner.ddinaping.fragment.GroupBuyFragment;
import com.brcorner.ddinaping.fragment.IndexFragment;
import com.brcorner.ddinaping.utils.LogUtils;

/**
 * 主类
 */

public class MainActivity extends FragmentActivity implements IndexFragment.OnFragmentInteractionListener,GroupBuyFragment.OnFragmentInteractionListener,AccountFragment.OnFragmentInteractionListener,DiscoverFragment.OnFragmentInteractionListener {

    private ImageView index_image, groupbuy_image, discover_image, account_image;
    private TextView index_text, groupbuy_text, discover_text, account_text;//四个底部标签
    private AccountFragment accountFragment;
    private DiscoverFragment discoverFragment;
    private GroupBuyFragment groupBuyFragment;
    private IndexFragment indexFragment;
    private FragmentManager fragmentManager;
    private int selectIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        this.initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(indexFragment != null)
        {
            String area =  this.getIntent().getStringExtra("areastr");
            if(area != null)
            {
                indexFragment.refreshArea(area);
            }
        }
    }

    public void initView() {
        fragmentManager = getSupportFragmentManager();
        selectIndex = this.getIntent().getIntExtra("intentType", 0);
        index_image = (ImageView) findViewById(R.id.index_image);
        groupbuy_image = (ImageView) findViewById(R.id.groupbuy_image);
        discover_image = (ImageView) findViewById(R.id.discover_image);
        account_image = (ImageView) findViewById(R.id.account_image);
        index_text = (TextView) findViewById(R.id.index_text);
        groupbuy_text = (TextView) findViewById(R.id.groupbuy_text);
        discover_text = (TextView) findViewById(R.id.discover_text);
        account_text = (TextView) findViewById(R.id.account_text);
        changeView();
    }

    public void changeView() {
        setTabSelection(selectIndex);
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0首页，1团购，2发现，3账户。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                index_image.setImageResource(R.drawable.main_index_home_pressed);
                index_text.setTextColor(Color.parseColor("#ff780f"));
                if (indexFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    indexFragment = IndexFragment.newInstance(null,null);
                    transaction.add(R.id.content, indexFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(indexFragment);
                }
                break;
            case 1:
                groupbuy_image.setImageResource(R.drawable.main_index_tuan_pressed);
                groupbuy_text.setTextColor(Color.parseColor("#ff780f"));
                if (groupBuyFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    groupBuyFragment = GroupBuyFragment.newInstance(null,null);
                    transaction.add(R.id.content, groupBuyFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(groupBuyFragment);
                }
                break;
            case 2:
                discover_image.setImageResource(R.drawable.main_index_search_pressed);
                discover_text.setTextColor(Color.parseColor("#ff780f"));
                if (discoverFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    discoverFragment = DiscoverFragment.newInstance(null,null);
                    transaction.add(R.id.content, discoverFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(discoverFragment);
                }
                break;
            case 3:
                account_image.setImageResource(R.drawable.main_index_my_pressed);
                account_text.setTextColor(Color.parseColor("#ff780f"));
                if (accountFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    accountFragment = AccountFragment.newInstance(null,null);
                    transaction.add(R.id.content, accountFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(accountFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        index_image.setImageResource(R.drawable.main_index_home_normal);
        index_text.setTextColor(Color.parseColor("#82858b"));
        groupbuy_image.setImageResource(R.drawable.main_index_tuan_normal);
        groupbuy_text.setTextColor(Color.parseColor("#82858b"));
        discover_image.setImageResource(R.drawable.main_index_search_normal);
        discover_text.setTextColor(Color.parseColor("#82858b"));
        account_image.setImageResource(R.drawable.main_index_my_normal);
        account_text.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (indexFragment != null) {
            transaction.hide(indexFragment);
        }
        if (groupBuyFragment != null) {
            transaction.hide(groupBuyFragment);
        }
        if (discoverFragment != null) {
            transaction.hide(discoverFragment);
        }
        if (accountFragment != null) {
            transaction.hide(accountFragment);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void intentSearchResultActivity(View view)
    {
        Intent intent = new Intent(this, SearchResultActivity.class);
        this.startActivity(intent);
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.index_layout:
                setTabSelection(0);
                break;
            case R.id.groupbuy_layout:
                setTabSelection(1);
                break;
            case R.id.discover_layout:
                setTabSelection(2);
                break;
            case R.id.account_layout:
                setTabSelection(3);
                break;
        }
    }
}
