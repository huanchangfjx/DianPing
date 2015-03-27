package com.brcorner.ddinaping.adapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.brcorner.ddinaping.activity.MainActivity;
import com.brcorner.ddinaping.model.CityBean;
import com.brcorner.ddinaping.R;

public class CityListAdapter extends BaseAdapter implements SectionIndexer{
	private Activity activity;
	private List<CityBean> list;
	private LayoutInflater inflater;
	private int pos;
	private ListView listview;

	private Holder holder;
	public CityListAdapter(Activity activity,
                           List<CityBean> list, ListView listview) {
		this.activity = activity;
		this.list = list;
		inflater = LayoutInflater.from(activity);
		this.listview=listview;
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		holder = new Holder();
		pos=position;
		if (view == null) {
			view = inflater.inflate(R.layout.item_city, null);
			holder.cityname = (TextView) view.findViewById(R.id.cityname_textview);
            holder.subtitle = (TextView) view.findViewById(R.id.title_textview);
            holder.linearall=(LinearLayout) view.findViewById(R.id.linearall);
			view.setTag(holder);
		}else{
			holder = (Holder) view.getTag();
		}						
		if (list.get(position).getIndex() == null) {
			holder.subtitle.setVisibility(View.GONE);
			holder.cityname.setText(list.get(position).getCityName());
            holder.linearall.setTag(R.id.linearall,list.get(position).getCityName());
			holder.linearall.setVisibility(View.VISIBLE);
		} else {
			holder.subtitle.setVisibility(View.VISIBLE);
			holder.subtitle.setText(list.get(position).getIndex());
			holder.linearall.setVisibility(View.GONE);
		}

        holder.linearall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                String str = (String) v.getTag(R.id.linearall);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("areastr",str);
                activity.startActivity(intent);
                activity.finish();
            }
        });
		return view;
	}
	

	class Holder {
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
			if(strIndex == null)
			{
				continue;
			}
			else
			{
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
