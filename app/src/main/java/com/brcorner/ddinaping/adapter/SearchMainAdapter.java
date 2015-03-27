package com.brcorner.ddinaping.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.brcorner.ddinaping.R;
import android.widget.TextView;

/**
 * 查找中的更多的界面中左边listview的适配器
 * @author 苦涩
 *</BR> </BR> By：苦涩 </BR> 联系作者：QQ 534429149
 */

public class SearchMainAdapter extends BaseAdapter {

    private Context ctx;
    private List<Map<String, Object>> list;
    private int position = 0;
    private boolean islodingimg = true;
    private int layout = R.layout.search_more_mainlist_item;

    public SearchMainAdapter(Context ctx, List<Map<String, Object>> list) {
        this.ctx = ctx;
        this.list = list;
    }

    public SearchMainAdapter(Context ctx, List<Map<String, Object>> list,
                             int layout, boolean islodingimg) {
        this.ctx = ctx;
        this.list = list;
        this.layout = layout;
        this.islodingimg = islodingimg;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    public long getItemId(int arg0) {
        return arg0;
    }

    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Holder hold;
        if (arg1 == null) {
            hold = new Holder();
            arg1 = View.inflate(ctx, layout, null);
            hold.txt = (TextView) arg1
                    .findViewById(R.id.Search_more_mainitem_txt);
            hold.img = (ImageView) arg1
                    .findViewById(R.id.Search_more_mainitem_img);
            hold.layout = (LinearLayout) arg1
                    .findViewById(R.id.Search_more_mainitem_layout);
            arg1.setTag(hold);
        } else {
            hold = (Holder) arg1.getTag();
        }
        if(islodingimg == true){
            hold.img.setImageResource(Integer.parseInt(list.get(arg0).get("img")
                    .toString()));
        }
        hold.txt.setText(list.get(arg0).get("txt").toString());
        hold.layout
                .setBackgroundResource(R.drawable.search_more_mainlistselect);
        if (arg0 == position) {
            hold.layout.setBackgroundResource(R.drawable.list_bkg_line_u);
        }
        return arg1;
    }

    public void setSelectItem(int i) {
        position = i;
    }

    public int getSelectItem() {
        return position;
    }

    private static class Holder {
        LinearLayout layout;
        ImageView img;
        TextView txt;
    }

}
