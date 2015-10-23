package com.brcorner.ddinaping.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.activity.ChooseCityActivity;
import com.brcorner.ddinaping.activity.ItemActivity;
import com.brcorner.ddinaping.activity.SearchResultActivity;
import com.brcorner.ddinaping.adapter.IndexListAdapter;
import com.brcorner.ddinaping.model.DateConstant;
import com.brcorner.ddinaping.model.ItemBean;
import com.brcorner.ddinaping.utils.DensityUtils;
import com.brcorner.ddinaping.view.DianPingPullRefresh.PullToRefreshView;

public class IndexFragment extends Fragment implements
        ViewPager.OnPageChangeListener, OnClickListener {

    private ListView index_listview;
    private ViewPager scroll_viewpager;
    private LinearLayout tips_ll;
    private List<View> viewList;
    private ImageView[] tips;
    private TextView city_text;

    private PullToRefreshView pullToRefreshView;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        pullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull);

        index_listview = pullToRefreshView.getListView();

        city_text = (TextView) view.findViewById(R.id.city_text);
        city_text.setOnClickListener(this);
        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pullToRefreshView.finishRefreshing();
            }
        }, 0);
        index_listview.setDividerHeight(2);
        index_listview.addHeaderView(getHeadView());
        final List<ItemBean> itemBeans = DateConstant.getGoodsList();
        IndexListAdapter indexListAdapter = new IndexListAdapter(
                this.getActivity(), R.layout.item_good, itemBeans);
        index_listview.setAdapter(indexListAdapter);
        index_listview
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intent = new Intent(getActivity(),
                                ItemActivity.class);
                        ItemBean itemBean = itemBeans.get(position - 1);
                        intent.putExtra("headRid", itemBean.getItemImgId());
                        getActivity().startActivity(intent);
                    }
                });
        return view;
    }

    private View getHeadView() {
        View messageLayout = ((LayoutInflater) getActivity()
                .getLayoutInflater()).inflate(R.layout.section_index_header,
                null);

        scroll_viewpager = (ViewPager) messageLayout
                .findViewById(R.id.scroll_viewpager);
        tips_ll = (LinearLayout) messageLayout.findViewById(R.id.tips_ll);
        viewList = new ArrayList<View>();
        tips = new ImageView[3];

        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this.getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtils.dp2px(this.getActivity(), 6),
                    DensityUtils.dp2px(this.getActivity(), 6));
            params.setMargins(DensityUtils.dp2px(this.getActivity(), 5),
                    DensityUtils.dp2px(this.getActivity(), 6),
                    DensityUtils.dp2px(this.getActivity(), 5),
                    DensityUtils.dp2px(this.getActivity(), 6));
            imageView.setLayoutParams(params);
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            tips_ll.addView(imageView);
        }
        LayoutInflater lf = getActivity().getLayoutInflater().from(
                IndexFragment.this.getActivity());
        // 设置btnlayout
        LinearLayout item_mainbtns1 = (LinearLayout) lf.inflate(
                R.layout.item_mainbtns1, null);
        LinearLayout item_mainbtns2 = (LinearLayout) lf.inflate(
                R.layout.item_mainbtns2, null);
        LinearLayout item_mainbtns3 = (LinearLayout) lf.inflate(
                R.layout.item_mainbtns3, null);
        LinearLayout goSlide = (LinearLayout) item_mainbtns1.findViewById(R.id.goSlide);
        goSlide.setOnClickListener(this);
        viewList.add(item_mainbtns1);
        viewList.add(item_mainbtns2);
        viewList.add(item_mainbtns3);

        // 设置Adapter
        scroll_viewpager.setAdapter(new MyAdapter());
        // 设置监听，主要是设置点点的背景
        scroll_viewpager.setOnPageChangeListener(IndexFragment.this);
        // 设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        scroll_viewpager.setCurrentItem(0);

//		scroll_viewpager.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//
//				switch (event.getAction()) {
//
//				case MotionEvent.ACTION_DOWN:
//					Log.v("setRawY", String.valueOf("ACTION_DOWN"));
//
//					float yDown = event.getRawY();
//					pullToRefreshView.setRawY(event,yDown);
//					break;
//
//				}
//
//				return false;
//
//			}
//			
//			
//
//		});
        return messageLayout;
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            // ((ViewPager)container).removeView(mImageViews[position %
            // mImageViews.length]);

        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(View container, int position) {
            if (viewList.size() > 0) {
                View view = viewList.get(position % viewList.size());
                try {

                    ((ViewPager) container).addView(view,
                            position % viewList.size());
                } catch (Exception e) {
                    // handler something
                }
                return view;
            }
            return null;
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        setImageBackground(position % viewList.size());

    }

    /**
     * 设置选中的tip的背景
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.city_text) {
            Intent intent = new Intent(this.getActivity(),
                    ChooseCityActivity.class);
            this.startActivityForResult(intent, 1);
        }else if(v.getId() == R.id.goSlide)
        {
            Intent intent = new Intent(this.getActivity(),
                    SearchResultActivity.class);
            this.startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case 1:
                if(resultCode == getActivity().RESULT_OK)
                {
                    String returnData = data.getStringExtra("data_return");
                    city_text.setText(returnData);
                }
                break;

            default:
                break;
        }
    }
}
