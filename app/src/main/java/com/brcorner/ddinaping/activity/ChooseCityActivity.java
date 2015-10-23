package com.brcorner.ddinaping.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
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

public class ChooseCityActivity extends Activity {

	private RelativeLayout head_rl;
	private RadioButton all_rbtn, oversea_rbtn;
	private LinearLayout rgll;
	private ListView listView;
	private List<CityBean> list = new ArrayList();
	private SideBar indexBar;
	private CityListAdapter adapter;
	private FrameLayout list_fl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_area);
		initView();
	}

	public void initView() {
		indexBar = (SideBar) findViewById(R.id.sideBar);
		listView = (ListView) findViewById(R.id.invite_list);
		head_rl = (RelativeLayout) findViewById(R.id.head_rl);
		rgll = (LinearLayout) View.inflate(this, R.layout.section_radiogroup,
				null);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		rgll.setLayoutParams(params);
		head_rl.addView(rgll);
		RadioGroup rb_group = (RadioGroup) findViewById(R.id.select_rg);
		all_rbtn = (RadioButton) findViewById(R.id.all_rbtn);
		oversea_rbtn = (RadioButton) findViewById(R.id.oversea_rbtn);
		all_rbtn.setTextColor(Color.WHITE);
		oversea_rbtn.setTextColor(getResources().getColor(R.color.city_head_orange));
		rb_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == all_rbtn.getId()) {
					all_rbtn.setTextColor(Color.WHITE);
					oversea_rbtn.setTextColor(Color.parseColor("#ffff6d00"));
					list = DateConstant.getCitys();
					ChooseCityActivity.this.changeView();
				} else {
					all_rbtn.setTextColor(Color.parseColor("#ffff6d00"));
					oversea_rbtn.setTextColor(Color.WHITE);
					list = DateConstant.getForeignCitys();
					ChooseCityActivity.this.changeView();
				}

			}
		});
		listView.setDivider(getResources().getDrawable(
				R.drawable.com_facebook_list_divider));
		listView.setDividerHeight(2);
		list = DateConstant.getCitys();
		View messageLayout = ((LayoutInflater)getLayoutInflater()).inflate(R.layout.section_city_hot, null);

		listView.addHeaderView(messageLayout);

		this.changeView();
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int myP = position - 1;
				if(myP >= 0)
				{
					String str = list.get(myP).getCityName();
					if(str != null && str.length() > 0)
					{
						Intent intent = new Intent();
						intent.putExtra("data_return", str);
						setResult(RESULT_OK,intent);
						finish();
					}
				}
				
			}
		});
		indexBar.setListView(listView);

		ViewTreeObserver vto = indexBar.getViewTreeObserver();     
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {   
            @Override     
            public void onGlobalLayout() {   
            	indexBar.getViewTreeObserver().removeGlobalOnLayoutListener(this);   
            	int indexBarHeight = indexBar.getHeight();  
            	indexBar.setParams(list,indexBarHeight);
        		
            }     
        });
		

		
	}

	
	public void changeView() {
		getListData();

		if (list.size() == 0) {
			listView.setVisibility(View.INVISIBLE);
		} else {
			listView.setVisibility(View.VISIBLE);
			if (adapter == null) {
				adapter = new CityListAdapter(this,R.layout.item_city ,list,this);
			} else {
				adapter.notifyDataSetChanged();
			}
			listView.setAdapter(adapter);
			
		}
		
	}

	private void getListData() {
		List<CityBean> orderEnlist = SortUtils.change2EnOrderList(list);// ��ȡ����ĸ�����б�
		boolean isAdd = false;

		// ��������
		String _firstchar = null;
		int dataNum = orderEnlist.size();
		for (int i = 0; i < dataNum; i++) {
			String startStr = String.valueOf(orderEnlist.get(i).getCityName()
					.charAt(0));
			char startChar = orderEnlist.get(i).getCityName().charAt(0);
			int x = (int) startChar;
			// �������ĸASC���ڴ�Сд��Χ��
			if ((x > 64 && x < 91) || (x > 96 && x < 123)) {
				if (!startStr.equalsIgnoreCase(_firstchar)) {// �ж��Ƿ���ӹ�
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

		// ��������
		SortUtils.change2EnOrderList(list);
	}

	
	public void initData() {
		// TODO Auto-generated method stub

	}
	
	public void doBack(View view)
	{
		this.finish();
	}


}
