package com.brcorner.ddinaping.view.DianPingArea;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.application.MyApplication;
import com.brcorner.ddinaping.model.CityBean;
import com.brcorner.ddinaping.utils.DensityUtils;
import com.brcorner.ddinaping.utils.DeviceParamsUtils;

/**
 *选择城市右边字母bar
 */

public class SideBar extends View {
	private List<String> index;
	private SectionIndexer sectionIndexter = null;
	private ListView list;
	private float m_nItemHeight;
	private int textSize;
	private Context context;
	private List<CityBean> friendList;
    private int actionbarHeight;

	public SideBar(Context context) {
		super(context);
		this.context = context;

	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

	}
	
	
	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}
	
	public SideBar(Context context,ArrayList<CityBean> list,int actionbarHeight) {
		super(context);
		this.context = context;
//		this.friendList = list;
        this.actionbarHeight = actionbarHeight;
	}
	
	private void init() {
		index = new ArrayList<String>();
		textSize = DensityUtils.sp2px(context, 12);
		for (CityBean cityBean : friendList) {
			if(cityBean.getIndex() != null)
			{
				index.add(cityBean.getIndex());
			}
		}
		int indexSize = 1;
		if(index.size() != 0)
		{
			indexSize = index.size();		}
		m_nItemHeight = (DeviceParamsUtils.getScreenHeight() - 350)/indexSize;

	}
	
	public void setParams(List<CityBean> list,int actionbarHeight)
	{
		this.friendList = list;
        this.actionbarHeight = actionbarHeight;
		init();

	}

	

	public void setListView(ListView _list) {
		list = _list;
		sectionIndexter = (SectionIndexer) _list.getAdapter();
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {

		super.onTouchEvent(event);
		float i =  event.getY();
        float idx = i / m_nItemHeight;
		if (idx >= index.size()) {
			idx = index.size() - 1;
		} else if (idx < 0) {
			idx = 0;
		}
		if ((event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_MOVE) && index.size() > 0) {
            this.setBackgroundResource(R.drawable.mm_text_bg_trans);
			if (sectionIndexter == null) {
				sectionIndexter = (SectionIndexer) list.getAdapter();
			}
			int position = sectionIndexter.getPositionForSection(index.get((int)idx).charAt(0));
			if (position == -1) {
				return true;
			}
			list.setSelection(position);
		} else {
            this.setBackgroundColor(Color.parseColor("#00000000"));
		}
		return true;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(0xff595c61);
		paint.setTextSize(textSize);
		paint.setTextAlign(Paint.Align.CENTER);
		float widthCenter = getMeasuredWidth() / 2;
		for (int i = 0; i < index.size(); i++) {
			canvas.drawText(index.get(i), widthCenter, m_nItemHeight
					+ (i * m_nItemHeight), paint);
		}
		super.onDraw(canvas);
	}
}
