package com.brcorner.ddinaping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.model.ItemBean;

import java.util.List;

/**
 * Created by Auser on 2015/3/13.
 */
public class ItemListAdapter extends ArrayAdapter<ItemBean>{

    private int resourceId;

    public ItemListAdapter(Context context, int resource,List<ItemBean> objects) {
        super(context, resource,objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemBean itemBean = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.itemimg_imageview = (ImageView) view.findViewById(R.id.itemimg_imageview);
            viewHolder.itemname_textview = (TextView) view.findViewById(R.id.itemname_textview);
            viewHolder.itemcontent_textview = (TextView) view.findViewById(R.id.itemcontent_textview);
            viewHolder.itemprice_textview = (TextView) view.findViewById(R.id.itemprice_textview);
            viewHolder.sellednum_textview = (TextView) view.findViewById(R.id.sellednum_textview);
            view.setTag(viewHolder);
        }else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.itemimg_imageview.setImageResource(itemBean.getItemImgId());
        viewHolder.itemname_textview.setText(itemBean.getItemName());
        viewHolder.itemcontent_textview.setText(itemBean.getItemContent());
        viewHolder.itemprice_textview.setText(itemBean.getItemPrice());
        viewHolder.sellednum_textview.setText(itemBean.getSelledNum());
        return view;
    }

    class ViewHolder{
        ImageView itemimg_imageview;
        TextView itemname_textview;
        TextView itemcontent_textview;
        TextView itemprice_textview;
        TextView sellednum_textview;
    }
}
