package com.brcorner.ddinaping.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.adapter.IndexListAdapter;
import com.brcorner.ddinaping.adapter.SearchMainAdapter;
import com.brcorner.ddinaping.adapter.SearchMoreAdapter;
import com.brcorner.ddinaping.model.DateConstant;
import com.brcorner.ddinaping.model.ItemBean;
import com.brcorner.ddinaping.view.DianPingSlidMenu.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 寻找效果 包含左右滑动以及下拉菜单功能
 */
public class SearchResultActivity extends Activity{

    private ListView head_third_listview,
            head_first_right_listview, head_second_right_listview, head_first_left_listview,
            head_second_left_listview;//头部列表
    private ListView shop_listview;//商品列表
    private TextView shoplist_title_textview, shoplist_title_textview2, shoplist_title_textview3;//列表三个按钮
    private LinearLayout head_second_ll, head_first_ll;

    private SearchMoreAdapter firstRightAdapter,secondRightAdapter,thirdAdapter;
    private SearchMainAdapter firstLeftAdapter,secondLeftAdapter;

    private boolean threelistview = false;
    private boolean mainlistview1 = false;
    private boolean mainlistview2 = false;
    private List<Map<String, Object>> dataList1;
    private List<Map<String, Object>> dataList2;
    //    private RangeSeekBar rangeSeekBar;
    private SlidingMenu slidingMenu;


    public void initView() {
        menu_imageview1 = (ImageView) findViewById(R.id.menu_imageview1);
        menu_imageview2 = (ImageView) findViewById(R.id.menu_imageview2);
        menu_imageview3 = (ImageView) findViewById(R.id.menu_imageview3);
        menu_imageview4 = (ImageView) findViewById(R.id.menu_imageview4);
//        rangeSeekBar = (RangeSeekBar) findViewById(R.id.rangeSeekBar);
        slidingMenu = (SlidingMenu) findViewById(R.id.id_menu);
//        rangeSeekBar.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
//            @Override
//            public void onRangeChanged(float lowerRange, float upperRange) {
//                ;
//            }
//        });
        shoplist_title_textview = (TextView) findViewById(R.id.shoplist_title_textview);
        shoplist_title_textview2 = (TextView) findViewById(R.id.shoplist_title_textview2);
        shoplist_title_textview3 = (TextView) findViewById(R.id.shoplist_title_textview3);

        head_first_ll = (LinearLayout) findViewById(R.id.head_first_ll);
        head_second_ll = (LinearLayout) findViewById(R.id.head_second_ll);

        head_first_left_listview = (ListView) findViewById(R.id.head_first_left_listview);
        head_second_left_listview = (ListView) findViewById(R.id.head_second_left_listview);
        head_first_right_listview = (ListView) findViewById(R.id.head_first_right_listview);
        head_second_right_listview = (ListView) findViewById(R.id.head_second_right_listview);
        head_third_listview = (ListView) findViewById(R.id.head_third_listview);

        shop_listview = (ListView) findViewById(R.id.shop_listview);

        initDate();
        firstLeftAdapter = new SearchMainAdapter(SearchResultActivity.this, dataList1, R.layout.shop_list1_item, false);
        firstLeftAdapter.setSelectItem(0);
        secondLeftAdapter = new SearchMainAdapter(SearchResultActivity.this, dataList2, R.layout.shop_list1_item, true);
        secondLeftAdapter.setSelectItem(0);
        thirdAdapter = new SearchMoreAdapter(SearchResultActivity.this, DateConstant.SHOPLIST_THREELIST, R.layout.shop_list2_item);
        head_first_left_listview.setAdapter(firstLeftAdapter);
        head_second_left_listview.setAdapter(secondLeftAdapter);
        initFirstMoreAdapter(DateConstant.SHOPLIST_PLACESTREET[0]);
        initSecondMoreAdapter(DateConstant.MORELISTTXT[0]);
        head_third_listview.setAdapter(thirdAdapter);
        FirstLeftListViewItemClickListener firstleft = new FirstLeftListViewItemClickListener();
        SecondLeftListViewItemClickListener secondleft = new SecondLeftListViewItemClickListener();
        FirstRightListViewItemClickListener firstright = new FirstRightListViewItemClickListener();
        SecondRightListViewItemClickListener secondright = new SecondRightListViewItemClickListener();
        ThirdListViewItemClickListener threeListOnItemClick = new ThirdListViewItemClickListener();
        head_first_left_listview.setOnItemClickListener(firstleft);
        head_second_left_listview.setOnItemClickListener(secondleft);
        head_first_right_listview.setOnItemClickListener(firstright);
        head_second_right_listview.setOnItemClickListener(secondright);
        head_third_listview.setOnItemClickListener(threeListOnItemClick);
        // -----------------------------------------------------------------
        List<ItemBean> goodsList = DateConstant.getGoodsList();

        IndexListAdapter adapter = new IndexListAdapter(this, R.layout.item_good, goodsList);
        shop_listview.setAdapter(adapter);

        changeView();
    }

    public void changeView() {
        shop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchResultActivity.this, ItemActivity.class);
                startActivity(intent);
            }
        });
    }

    public void toggleMenu(View view) {
        slidingMenu.toggle();
    }

    private ImageView menu_imageview1, menu_imageview2, menu_imageview3, menu_imageview4;

    public void change_rl(View view) {
        clearMenu();
        switch (view.getId()) {
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

    public void clearMenu() {
        menu_imageview1.setVisibility(View.GONE);
        menu_imageview2.setVisibility(View.GONE);
        menu_imageview3.setVisibility(View.GONE);
        menu_imageview4.setVisibility(View.GONE);
    }

    public void closeMenu(View view) {
        slidingMenu.closeMenu();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_result);
        initView();
    }

    private class FirstLeftListViewItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            initFirstMoreAdapter(DateConstant.SHOPLIST_PLACESTREET[arg2]);
            firstLeftAdapter.setSelectItem(arg2);
            firstLeftAdapter.notifyDataSetChanged();
        }
    }

    private class SecondLeftListViewItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            initSecondMoreAdapter(DateConstant.MORELISTTXT[arg2]);
            secondLeftAdapter.setSelectItem(arg2);
            secondLeftAdapter.notifyDataSetChanged();
        }
    }

    private class FirstRightListViewItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            firstRightAdapter.setSelectItem(arg2);
            Drawable drawable = getResources().getDrawable(
                    R.drawable.ic_arrow_down_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            shoplist_title_textview.setCompoundDrawables(null, null, drawable,
                    null);
            int position = firstLeftAdapter.getSelectItem();
            shoplist_title_textview
                    .setText(DateConstant.SHOPLIST_PLACESTREET[position][arg2]);
            head_first_ll.setVisibility(View.GONE);
            mainlistview1 = false;
        }
    }

    private class SecondRightListViewItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            secondRightAdapter.setSelectItem(arg2);
            Drawable drawable = getResources().getDrawable(
                    R.drawable.ic_arrow_down_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            shoplist_title_textview2.setCompoundDrawables(null, null, drawable,
                    null);
            int position = secondLeftAdapter.getSelectItem();
            shoplist_title_textview2.setText(DateConstant.MORELISTTXT[position][arg2]);
            head_second_ll.setVisibility(View.GONE);
            mainlistview2 = false;
        }
    }

    private class ThirdListViewItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            thirdAdapter.setSelectItem(arg2);
            Drawable drawable = getResources().getDrawable(
                    R.drawable.ic_arrow_down_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            shoplist_title_textview3.setCompoundDrawables(null, null, drawable,
                    null);
            shoplist_title_textview3.setText(DateConstant.SHOPLIST_THREELIST[arg2]);
            head_third_listview.setVisibility(View.GONE);
            threelistview = false;
        }
    }

    private void initDate() {
        dataList1 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < DateConstant.SHOPLIST_PLACE.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("txt", DateConstant.SHOPLIST_PLACE[i]);
            dataList1.add(map);
        }
        dataList2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < DateConstant.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", DateConstant.LISTVIEWIMG[i]);
            map.put("txt", DateConstant.LISTVIEWTXT[i]);
            dataList2.add(map);
        }
    }

    private void initFirstMoreAdapter(String[] array) {
        firstRightAdapter = new SearchMoreAdapter(this, array, R.layout.shop_list2_item);
        head_first_right_listview.setAdapter(firstRightAdapter);
        firstRightAdapter.notifyDataSetChanged();
    }

    private void initSecondMoreAdapter(String[] array) {
        secondRightAdapter = new SearchMoreAdapter(this, array, R.layout.shop_list2_item);
        head_second_right_listview.setAdapter(secondRightAdapter);
        secondRightAdapter.notifyDataSetChanged();
    }

    public void doClick(View v) {
        int mID = v.getId();

        if (mID == R.id.shoplist_title_textview3) {
            Drawable drawable = null;
            if (!threelistview) {
                drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_up_black);
                head_third_listview.setVisibility(View.VISIBLE);
                thirdAdapter.notifyDataSetChanged();
                threelistview = true;
            } else {
                drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                head_third_listview.setVisibility(View.GONE);
                threelistview = false;
            }
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            shoplist_title_textview3.setCompoundDrawables(null, null,
                    drawable, null);
        } else {
            Drawable drawable = getResources().getDrawable(
                    R.drawable.ic_arrow_down_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            shoplist_title_textview3.setCompoundDrawables(null, null,
                    drawable, null);
            head_third_listview.setVisibility(View.GONE);
            threelistview = false;
        }

        if (mID == R.id.shoplist_title_textview2) {
            Drawable drawable = null;
            if (!mainlistview2) {
                drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_up_black);
                head_second_ll.setVisibility(View.VISIBLE);
                secondLeftAdapter.notifyDataSetChanged();
                mainlistview2 = true;
            } else {
                drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                head_second_ll.setVisibility(View.GONE);
                mainlistview2 = false;
            }
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            shoplist_title_textview2.setCompoundDrawables(null, null,
                    drawable, null);
        } else {
            Drawable drawable = getResources().getDrawable(
                    R.drawable.ic_arrow_down_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            shoplist_title_textview2.setCompoundDrawables(null, null,
                    drawable, null);
            head_second_ll.setVisibility(View.GONE);
            mainlistview2 = false;
        }

        if (mID == R.id.shoplist_title_textview) {
            Drawable drawable = null;
            if (!mainlistview1) {
                drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_up_black);
                head_first_ll.setVisibility(View.VISIBLE);
                firstLeftAdapter.notifyDataSetChanged();
                mainlistview1 = true;
            } else {
                drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                head_first_ll.setVisibility(View.GONE);
                mainlistview1 = false;
            }
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            shoplist_title_textview.setCompoundDrawables(null, null,
                    drawable, null);
        } else {
            Drawable drawable = getResources().getDrawable(
                    R.drawable.ic_arrow_down_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            shoplist_title_textview.setCompoundDrawables(null, null,
                    drawable, null);
            head_first_ll.setVisibility(View.GONE);
            mainlistview1 = false;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (threelistview == true) {

                Drawable drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                shoplist_title_textview3.setCompoundDrawables(null, null,
                        drawable, null);
                head_third_listview.setVisibility(View.GONE);
                threelistview = false;
            } else if (mainlistview1 == true) {
                Drawable drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                shoplist_title_textview.setCompoundDrawables(null, null,
                        drawable, null);
                head_first_ll.setVisibility(View.GONE);
                mainlistview1 = false;
            } else if (mainlistview2 == true) {
                Drawable drawable = getResources().getDrawable(
                        R.drawable.ic_arrow_down_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                shoplist_title_textview2.setCompoundDrawables(null, null,
                        drawable, null);
                head_second_ll.setVisibility(View.GONE);
                mainlistview2 = false;
            } else {
                this.finish();
            }
        }
        return false;
    }

    public void doBack(View view)
    {
        this.finish();
    }

}
