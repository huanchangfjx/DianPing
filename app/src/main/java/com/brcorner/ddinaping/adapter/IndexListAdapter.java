package com.brcorner.ddinaping.adapter;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.model.ItemBean;

public class IndexListAdapter extends ArrayAdapter<ItemBean>{
	
	private int resourceId;
	
	public IndexListAdapter(Context context, int resource,
			List<ItemBean> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		ItemBean itemBean = getItem(position);
		ViewHolder viewHolder;
		if(convertView == null)
		{
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.itemImg_image = (ImageView) view.findViewById(R.id.itemImg_image);
			viewHolder.itemName_text = (TextView) view.findViewById(R.id.itemName_text);
			viewHolder.itemContent_text = (TextView) view.findViewById(R.id.itemContent_text);
			viewHolder.itemPrice_text = (TextView) view.findViewById(R.id.itemPrice_text);
			viewHolder.selledNum_text = (TextView) view.findViewById(R.id.selledNum_text);
			view.setTag(viewHolder);
		}
		else
		{
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.itemImg_image.setImageResource(itemBean.getItemImgId());
		viewHolder.itemName_text.setText(itemBean.getItemName());
		viewHolder.itemContent_text.setText(itemBean.getItemContent());
		viewHolder.itemPrice_text.setText(itemBean.getItemPrice());
		viewHolder.selledNum_text.setText(itemBean.getSelledNum());
		return view;
	}
	
	class ViewHolder{
		ImageView itemImg_image;
		TextView itemName_text;
		TextView itemContent_text;
		TextView itemPrice_text;
		TextView selledNum_text;
	}
}
