package com.brcorner.ddinaping.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.model.CityBean;


public class CityListAdapter extends ArrayAdapter<CityBean> implements SectionIndexer {
	private int resourceId;
	private List<CityBean> list;
	private Activity activity;
	public CityListAdapter(Context context, int resource,
						   List<CityBean> objects,Activity activity) {
		super(context, resource, objects);
		resourceId = resource;
		list = objects;
		this.activity = activity;
	}



	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder viewHolder;
		final int mp = position;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();

			viewHolder.cityname = (TextView) view.findViewById(R.id.cityname_textview);
			viewHolder.subtitle = (TextView) view.findViewById(R.id.title_textview);
			viewHolder.linearall = (LinearLayout) view.findViewById(R.id.linearall);
			view.setTag(viewHolder);

		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}

		if (list.get(position).getIndex() == null) {
			viewHolder.subtitle.setVisibility(View.GONE);
			viewHolder.cityname.setText(list.get(position).getCityName());
			viewHolder.linearall.setVisibility(View.VISIBLE);
		} else {
			viewHolder.subtitle.setVisibility(View.VISIBLE);
			viewHolder.subtitle.setText(list.get(position).getIndex());
			viewHolder.linearall.setVisibility(View.GONE);
		}

		viewHolder.linearall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mp >= 0)
				{
					String str = list.get(mp).getCityName();
					if(str != null && str.length() > 0)
					{
						Intent intent = new Intent();
						intent.putExtra("data_return", str);
						activity.setResult(activity.RESULT_OK,intent);
						activity.finish();
					}
				}
			}
		});
		return view;
	}


	class ViewHolder {
		public TextView cityname;
		public TextView subtitle;
		public LinearLayout linearall;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPositionForSection(int sectionIndex) {
		// TODO Auto-generated method stub


		for (int i = 0; i < list.size(); i++) {
			String strIndex = list.get(i).getIndex();
			if (strIndex == null) {
				continue;
			} else {
				char firstChar = strIndex.charAt(0);
				if (firstChar == sectionIndex) {
					return i;
				}
			}

		}
		return -1;


	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
