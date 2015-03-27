package com.brcorner.ddinaping.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.adapter.CityListAdapter;
import com.brcorner.ddinaping.model.CityBean;
import com.brcorner.ddinaping.model.DateConstant;
import com.brcorner.ddinaping.utils.SortUtils;
import com.brcorner.ddinaping.view.DianPingArea.SideBar;

import java.util.ArrayList;
import java.util.List;

/**
 * AreaActivity 地区
 */

public class AreaActivity extends BaseActivity {

    private RelativeLayout head_rl;
    private RadioButton all_rbtn, oversea_rbtn;
    private LinearLayout rgll;
    private ListView listView;
    private List list = new ArrayList<CityBean>();
    private SideBar indexBar;
    private CityListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_area);
        initView();
    }


    @Override
    public void initView() {
        indexBar = (SideBar) findViewById(R.id.sideBar);
        listView = (ListView) findViewById(R.id.invite_list);

        head_rl = (RelativeLayout) findViewById(R.id.head_rl);
        rgll = (LinearLayout) View.inflate(
                this,
                R.layout.section_radiogroup, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        rgll.setLayoutParams(params);
        head_rl.addView(rgll);
        RadioGroup rb_group = (RadioGroup) findViewById(R.id.select_rg);
        all_rbtn = (RadioButton) findViewById(R.id.all_rbtn);
        oversea_rbtn = (RadioButton) findViewById(R.id.oversea_rbtn);
        all_rbtn.setTextColor(Color.WHITE);
        oversea_rbtn.setTextColor(Color.rgb(99, 99, 99));
        rb_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == all_rbtn.getId()) {
                    all_rbtn.setTextColor(Color.WHITE);
                    oversea_rbtn.setTextColor(Color.parseColor("#ffff6d00"));
                    list = DateConstant.getCitys();
                    AreaActivity.this.changeView();
                } else {
                    all_rbtn.setTextColor(Color.parseColor("#ffff6d00"));
                    oversea_rbtn.setTextColor(Color.WHITE);
                    list = DateConstant.getForeignCitys();
                    AreaActivity.this.changeView();
                }

            }
        });
        listView.setDivider(getResources().getDrawable(
                R.drawable.com_facebook_list_divider));
        listView.setDividerHeight(2);
        indexBar.setParams(list, head_rl.getLayoutParams().height);
        indexBar.setListView(listView);
        list = DateConstant.getCitys();
        this.changeView();
    }

    @Override
    public void changeView() {
        getListData();


        if (list.size() == 0) {
            listView.setVisibility(View.INVISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            if(adapter == null)
            {
                adapter = new CityListAdapter(
                        this, list, listView);
            }
            else
            {
                adapter.notifyDataSetChanged();
            }
            listView.setAdapter(adapter);
        }
    }



    private void getListData() {
        List<CityBean> orderEnlist = SortUtils.change2EnOrderList(list);//获取首字母排序列表
        boolean isAdd = false;

        //增加索引
        String _firstchar = null;
        int dataNum = orderEnlist.size();
        for (int i = 0; i < dataNum; i++) {
            String startStr = String.valueOf(orderEnlist.get(i).getCityName().charAt(0));
            char startChar = orderEnlist.get(i).getCityName().charAt(0);
            int x = (int) startChar;
            // 如果首字母ASC码在大小写范围内
            if ((x > 64 && x < 91) || (x > 96 && x < 123)) {
                if (!startStr.equalsIgnoreCase(_firstchar)) {//判断是否添加过
                    _firstchar = startStr;
                    CityBean cityBean2 = new CityBean();
                    cityBean2.setIndex(_firstchar.toUpperCase());
                    list.add(cityBean2);
                }
            } else {
                if (!isAdd) {//
                    CityBean inBean2 = new CityBean();
                    inBean2.setIndex("#");
                    list.add(inBean2);
                    isAdd = true;
                }
            }
        }

        //重新排序
        SortUtils.change2EnOrderList(list);
    }


}
