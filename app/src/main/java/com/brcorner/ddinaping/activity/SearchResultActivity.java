package com.brcorner.ddinaping.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.adapter.ItemListAdapter;
import com.brcorner.ddinaping.adapter.SearchMainAdapter;
import com.brcorner.ddinaping.adapter.SearchMoreAdapter;
import com.brcorner.ddinaping.model.DateConstant;
import com.brcorner.ddinaping.model.ItemBean;
import com.brcorner.ddinaping.view.DianPingSlidMenu.RangeSeekBar;
import com.brcorner.ddinaping.view.DianPingSlidMenu.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultActivity extends BaseActivity {

    private ListView mListView, mShoplist_toplist, mShoplist_threelist,
            mShoplist_onelist2, mShoplist_twolist2, mShoplist_onelist1,
            mShoplist_twolist1;
    private TextView mShoplist_title_textbtn1, mShoplist_title_textbtn2,
            mShoplist_title_textbtn3;//列表三个按钮
    private LinearLayout mShoplist_mainlist2,
            mShoplist_mainlist1;

    private SearchMoreAdapter topadapter = null;
    private SearchMoreAdapter threeadapter = null;
    private SearchMoreAdapter twoadapter1 = null;
    private SearchMainAdapter oneadapter1 = null;
    private SearchMoreAdapter twoadapter2 = null;
    private SearchMainAdapter oneadapter2 = null;
    private Button ListBottem = null;
    private int mStart = 1;
    private int mEnd = 5;
    private String url = null;
    private boolean flag = true;
    private boolean listBottemFlag = true;
    private boolean toplistview = false;
    private boolean threelistview = false;
    private boolean mainlistview1 = false;
    private boolean mainlistview2 = false;
    private List<Map<String, Object>> mainList1;
    private List<Map<String, Object>> mainList2;
    private RangeSeekBar rangeSeekBar;
    private SlidingMenu slidingMenu;


    @Override
    public void initView() {
        menu_imageview1 = (ImageView) findViewById(R.id.menu_imageview1);
        menu_imageview2 = (ImageView) findViewById(R.id.menu_imageview2);
        menu_imageview3 = (ImageView) findViewById(R.id.menu_imageview3);
        menu_imageview4 = (ImageView) findViewById(R.id.menu_imageview4);
        rangeSeekBar = (RangeSeekBar) findViewById(R.id.rangeSeekBar);
        slidingMenu = (SlidingMenu) findViewById(R.id.id_menu);
        rangeSeekBar.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {

            @Override
            public void onRangeChanged(float lowerRange, float upperRange) {
                ;
            }
        });
        mShoplist_title_textbtn1 = (TextView) findViewById(R.id.Shoplist_title_textbtn1);
        mShoplist_title_textbtn2 = (TextView) findViewById(R.id.Shoplist_title_textbtn2);
        mShoplist_title_textbtn3 = (TextView) findViewById(R.id.Shoplist_title_textbtn3);
        mShoplist_toplist = (ListView) findViewById(R.id.Shoplist_toplist);
        mShoplist_mainlist1 = (LinearLayout) findViewById(R.id.Shoplist_mainlist1);
        mShoplist_onelist1 = (ListView) findViewById(R.id.Shoplist_onelist1);
        mShoplist_twolist1 = (ListView) findViewById(R.id.Shoplist_twolist1);
        mShoplist_mainlist2 = (LinearLayout) findViewById(R.id.Shoplist_mainlist2);
        mShoplist_onelist2 = (ListView) findViewById(R.id.Shoplist_onelist2);
        mShoplist_twolist2 = (ListView) findViewById(R.id.Shoplist_twolist2);
        mShoplist_threelist = (ListView) findViewById(R.id.Shoplist_threelist);
        mListView = (ListView) findViewById(R.id.ShopListView);

        MyOnclickListener mOnclickListener = new MyOnclickListener();

        mShoplist_title_textbtn1.setOnClickListener(mOnclickListener);
        mShoplist_title_textbtn2.setOnClickListener(mOnclickListener);
        mShoplist_title_textbtn3.setOnClickListener(mOnclickListener);
        // -----------------------------------------------------------------
        initModel1();
        initModel2();
        oneadapter1 = new SearchMainAdapter(SearchResultActivity.this, mainList1, R.layout.shop_list1_item, false);
        oneadapter1.setSelectItem(0);
        oneadapter2 = new SearchMainAdapter(SearchResultActivity.this, mainList2, R.layout.shop_list1_item, true);
        oneadapter2.setSelectItem(0);
        topadapter = new SearchMoreAdapter(SearchResultActivity.this, DateConstant.SHOPLIST_TOPLIST, R.layout.shop_list2_item);
        threeadapter = new SearchMoreAdapter(SearchResultActivity.this, DateConstant.SHOPLIST_THREELIST, R.layout.shop_list2_item);
        mShoplist_toplist.setAdapter(topadapter);
        mShoplist_onelist1.setAdapter(oneadapter1);
        initAdapter1(DateConstant.SHOPLIST_PLACESTREET[0]);
        mShoplist_onelist2.setAdapter(oneadapter2);
        initAdapter2(DateConstant.MORELISTTXT[0]);
        mShoplist_threelist.setAdapter(threeadapter);
        TopListOnItemclick topListOnItemclick = new TopListOnItemclick();
        Onelistclick1 onelistclick1 = new Onelistclick1();
        Twolistclick1 twolistclick1 = new Twolistclick1();
        Onelistclick2 onelistclick2 = new Onelistclick2();
        Twolistclick2 twolistclick2 = new Twolistclick2();
        ThreeListOnItemclick threeListOnItemClick = new ThreeListOnItemclick();
        mShoplist_toplist.setOnItemClickListener(topListOnItemclick);
        mShoplist_onelist1.setOnItemClickListener(onelistclick1);
        mShoplist_twolist1.setOnItemClickListener(twolistclick1);
        mShoplist_onelist2.setOnItemClickListener(onelistclick2);
        mShoplist_twolist2.setOnItemClickListener(twolistclick2);
        mShoplist_threelist.setOnItemClickListener(threeListOnItemClick);
        // -----------------------------------------------------------------
        List<ItemBean> goodsList = DateConstant.getGoodsList();
        ItemListAdapter adapter = new ItemListAdapter(this,R.layout.item_good,goodsList);
        ListBottem = new Button(SearchResultActivity.this);
        ListBottem.setText("点击加载更多");
        ListBottem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag && listBottemFlag) {

                    listBottemFlag = false;
                }
            }
        });
        mListView.addFooterView(ListBottem, null, false);
        ListBottem.setVisibility(View.GONE);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new MainListOnItemClickListener());
        changeView();
    }

    @Override
    public void changeView() {

    }

    public void toggleMenu(View view)
    {
        slidingMenu.toggle();
    }

    private ImageView menu_imageview1,menu_imageview2,menu_imageview3,menu_imageview4;

    public void change_rl(View view)
    {
        clearMenu();
        switch (view.getId())
        {
            case R.id.menu_rl1:
                menu_imageview1.setVisibility(View.VISIBLE);
                break;
            case R.id.menu_rl2:
                menu_imageview2.setVisibility(View.VISIBLE);
                break;
            case R.id.menu_rl3:
                menu_imageview3.setVisibility(View.VISIBLE);
                break;
            case R.id.menu_rl4:
                menu_imageview4.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void clearMenu(){
        menu_imageview1.setVisibility(View.GONE);
        menu_imageview2.setVisibility(View.GONE);
        menu_imageview3.setVisibility(View.GONE);
        menu_imageview4.setVisibility(View.GONE);
    }



    public void closeMenu(View view)
    {
        slidingMenu.closeMenu();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_result);
        initView();
    }

    private class MyOnclickListener implements View.OnClickListener {
        public void onClick(View v) {
            int mID = v.getId();

            if (mID == R.id.Shoplist_title_textbtn3) {
                Drawable drawable = null;
                if (!threelistview) {
                    drawable = getResources().getDrawable(
                            R.drawable.ic_arrow_up_black);
                    mShoplist_threelist.setVisibility(View.VISIBLE);
                    threeadapter.notifyDataSetChanged();
                    threelistview = true;
                } else {
                    drawable = getResources().getDrawable(
                            R.drawable.ic_arrow_down_black);
                    mShoplist_threelist.setVisibility(View.GONE);
                    threelistview = false;
                }
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                mShoplist_title_textbtn3.setCompoundDrawables(null, null,
                        drawable, null);
            } else {
                Drawable drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                mShoplist_title_textbtn3.setCompoundDrawables(null, null,
                        drawable, null);
                mShoplist_threelist.setVisibility(View.GONE);
                threelistview = false;

            }
            if (mID == R.id.Shoplist_title_textbtn2) {
                Drawable drawable = null;
                if (!mainlistview2) {
                    drawable = getResources().getDrawable(
                            R.drawable.ic_arrow_up_black);
                    mShoplist_mainlist2.setVisibility(View.VISIBLE);
                    twoadapter2.notifyDataSetChanged();
                    mainlistview2 = true;
                } else {
                    drawable = getResources().getDrawable(
                            R.drawable.ic_arrow_down_black);
                    mShoplist_mainlist2.setVisibility(View.GONE);
                    mainlistview2 = false;
                }
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                mShoplist_title_textbtn2.setCompoundDrawables(null, null,
                        drawable, null);
            } else {
                Drawable drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                mShoplist_title_textbtn2.setCompoundDrawables(null, null,
                        drawable, null);
                mShoplist_mainlist2.setVisibility(View.GONE);
                mainlistview2 = false;
            }
            if (mID == R.id.Shoplist_title_textbtn1) {
                Drawable drawable = null;
                if (!mainlistview1) {
                    drawable = getResources().getDrawable(
                            R.drawable.ic_arrow_up_black);
                    mShoplist_mainlist1.setVisibility(View.VISIBLE);
                    twoadapter1.notifyDataSetChanged();
                    mainlistview1 = true;
                } else {
                    drawable = getResources().getDrawable(
                            R.drawable.ic_arrow_down_black);
                    mShoplist_mainlist1.setVisibility(View.GONE);
                    mainlistview1 = false;
                }
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                mShoplist_title_textbtn1.setCompoundDrawables(null, null,
                        drawable, null);
            } else {
                Drawable drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                mShoplist_title_textbtn1.setCompoundDrawables(null, null,
                        drawable, null);
                mShoplist_mainlist1.setVisibility(View.GONE);
                mainlistview1 = false;
            }
        }
    }



    private class MainListOnItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Intent intent = new Intent(SearchResultActivity.this,ItemActivity.class);
            startActivity(intent);
        }
    }

    private class TopListOnItemclick implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            topadapter.setSelectItem(arg2);
            mShoplist_toplist.setVisibility(View.GONE);
            toplistview = false;
        }
    }

    private class Onelistclick1 implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            initAdapter1(DateConstant.SHOPLIST_PLACESTREET[arg2]);
            oneadapter1.setSelectItem(arg2);
            oneadapter1.notifyDataSetChanged();
        }
    }

    private class Twolistclick1 implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            twoadapter1.setSelectItem(arg2);
            Drawable drawable = getResources().getDrawable(
                    R.drawable.ic_arrow_down_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            mShoplist_title_textbtn1.setCompoundDrawables(null, null, drawable,
                    null);
            int position = oneadapter1.getSelectItem();
            mShoplist_title_textbtn1
                    .setText(DateConstant.SHOPLIST_PLACESTREET[position][arg2]);
            mShoplist_mainlist1.setVisibility(View.GONE);
            mainlistview1 = false;
        }
    }

    private class Onelistclick2 implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            initAdapter2(DateConstant.MORELISTTXT[arg2]);
            oneadapter2.setSelectItem(arg2);
            oneadapter2.notifyDataSetChanged();
        }
    }

    private class Twolistclick2 implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            twoadapter2.setSelectItem(arg2);
            Drawable drawable = getResources().getDrawable(
                    R.drawable.ic_arrow_down_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            mShoplist_title_textbtn2.setCompoundDrawables(null, null, drawable,
                    null);
            int position = oneadapter2.getSelectItem();
            mShoplist_title_textbtn2.setText(DateConstant.MORELISTTXT[position][arg2]);
            mShoplist_mainlist2.setVisibility(View.GONE);
            mainlistview2 = false;
        }
    }

    private void initModel1() {
        mainList1 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < DateConstant.SHOPLIST_PLACE.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("txt", DateConstant.SHOPLIST_PLACE[i]);
            mainList1.add(map);
        }
    }

    private void initModel2() {
        mainList2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < DateConstant.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", DateConstant.LISTVIEWIMG[i]);
            map.put("txt", DateConstant.LISTVIEWTXT[i]);
            mainList2.add(map);
        }
    }

    private void initAdapter1(String[] array) {
        twoadapter1 = new SearchMoreAdapter(this, array, R.layout.shop_list2_item);
        mShoplist_twolist1.setAdapter(twoadapter1);
        twoadapter1.notifyDataSetChanged();
    }

    private void initAdapter2(String[] array) {
        twoadapter2 = new SearchMoreAdapter(this, array, R.layout.shop_list2_item);
        mShoplist_twolist2.setAdapter(twoadapter2);
        twoadapter2.notifyDataSetChanged();
    }

    private class ThreeListOnItemclick implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            threeadapter.setSelectItem(arg2);
            Drawable drawable = getResources().getDrawable(
                    R.drawable.ic_arrow_down_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            mShoplist_title_textbtn3.setCompoundDrawables(null, null, drawable,
                    null);
            mShoplist_title_textbtn3.setText(DateConstant.SHOPLIST_THREELIST[arg2]);
            mShoplist_threelist.setVisibility(View.GONE);
            threelistview = false;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (toplistview == true) {
                mShoplist_toplist.setVisibility(View.GONE);
                toplistview = false;
            } else if (threelistview == true) {

                Drawable drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                mShoplist_title_textbtn3.setCompoundDrawables(null, null,
                        drawable, null);
                mShoplist_threelist.setVisibility(View.GONE);
                threelistview = false;
            } else if (mainlistview1 == true) {
                Drawable drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                mShoplist_title_textbtn1.setCompoundDrawables(null, null,
                        drawable, null);
                mShoplist_mainlist1.setVisibility(View.GONE);
                mainlistview1 = false;
            } else if (mainlistview2 == true) {
                Drawable drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                mShoplist_title_textbtn2.setCompoundDrawables(null, null,
                        drawable, null);
                mShoplist_mainlist2.setVisibility(View.GONE);
                mainlistview2 = false;
            } else {
                this.finish();
            }
        }
        return false;
    }

}
