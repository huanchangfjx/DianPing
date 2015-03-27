package com.brcorner.ddinaping.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.activity.AreaActivity;
import com.brcorner.ddinaping.activity.ItemActivity;
import com.brcorner.ddinaping.activity.MessageActivity;
import com.brcorner.ddinaping.activity.SearchActivity;
import com.brcorner.ddinaping.adapter.ItemListAdapter;
import com.brcorner.ddinaping.application.MyApplication;
import com.brcorner.ddinaping.model.ItemBean;
import com.brcorner.ddinaping.model.DateConstant;
import com.brcorner.ddinaping.utils.DensityUtils;
import com.brcorner.ddinaping.utils.LogUtils;
import com.brcorner.ddinaping.view.DianPingPullRefresh.PullToRefreshBase;
import com.brcorner.ddinaping.view.DianPingPullRefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    //    private PullToRefreshView pullToRefreshView;
//    private ScrollView scroll;
    private TextView area_text;
    private LinearLayout search_ll;
    private ImageView message_image;

    private ViewPager srollbtn_viewpager;
    private List<View> viewList;
    private LinearLayout dian_ll;
    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;

    private MyApplication myApplication;


    private View mMainView;
    private PullToRefreshListView pullToRefreshListView;
    private ListView listView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndexFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
        LogUtils.v("IndexFragment",fragment.toString());

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public IndexFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.v("IndexFragment","onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public void refreshArea(String area)
    {
        if (area_text != null) {
            area_text.setText(area);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.v("IndexFragment","onCreateView");
        myApplication = (MyApplication) this.getActivity().getApplication();
        // Inflate the layout for this fragment
        mMainView = inflater.inflate(R.layout.section_indexfragment_list, (ViewGroup) getActivity().findViewById(R.id.pager), false);
        pullToRefreshListView = (PullToRefreshListView) mMainView.findViewById(R.id.pull_refresh_list);
        pullToRefreshListView.setVisibility(View.GONE);
        listView = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(r).start();
            }
        });
        area_text = (TextView) mMainView.findViewById(R.id.area_text);
        search_ll = (LinearLayout) mMainView.findViewById(R.id.search_ll);
        message_image = (ImageView) mMainView.findViewById(R.id.message_image);
        area_text.setOnClickListener(this);
        search_ll.setOnClickListener(this);
        message_image.setOnClickListener(this);
        return mMainView;

    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(1);

        }
    };

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    pullToRefreshListView.onRefreshComplete();
                    break;
            }
        }
    };

    private Boolean isShowHead = false;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        LogUtils.v("IndexFragment","onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        listView.setDividerHeight(0);
        if (!isShowHead) {
            listView.addHeaderView(getHeadView(savedInstanceState));
            List<ItemBean> goodsList = DateConstant.getGoodsList();
            ItemListAdapter adapter = new ItemListAdapter(this.getActivity(), R.layout.item_good, goodsList);
//        listView = (ListView) messageLayout.findViewById(R.id.list_view);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), ItemActivity.class);
                    getActivity().startActivity(intent);
                }
            });
            isShowHead = true;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.v("IndexFragment","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.v("IndexFragment","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.v("IndexFragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.v("IndexFragment","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.v("IndexFragment","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.v("IndexFragment","onDestroy");
    }

    public View getHeadView(Bundle savedInstanceState) {
        View messageLayout = ((LayoutInflater) getActivity().getLayoutInflater()).inflate(R.layout.fragment_index, null);

        srollbtn_viewpager = (ViewPager) messageLayout.findViewById(R.id.srollbtn_viewpager);
        dian_ll = (LinearLayout) messageLayout.findViewById(R.id.dian_ll);
        viewList = new ArrayList<View>();
        //点点
        tips = new ImageView[3];


        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(
                    this.getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dp2px(
                    this.getActivity(), 6), DensityUtils
                    .dp2px(this.getActivity(), 6));
            params.setMargins(DensityUtils
                    .dp2px(this.getActivity(), 5), DensityUtils
                    .dp2px(this.getActivity(), 6), DensityUtils
                    .dp2px(this.getActivity(), 5), DensityUtils
                    .dp2px(this.getActivity(), 6));
            imageView.setLayoutParams(params);
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            dian_ll.addView(imageView);
        }
        LayoutInflater lf = getLayoutInflater(savedInstanceState).from(
                IndexFragment.this.getActivity());
        //设置btnlayout
        LinearLayout item_mainbtns1 = (LinearLayout) lf.inflate(
                R.layout.item_mainbtns1, null);
        LinearLayout item_mainbtns2 = (LinearLayout) lf.inflate(
                R.layout.item_mainbtns2, null);
        LinearLayout item_mainbtns3 = (LinearLayout) lf.inflate(
                R.layout.item_mainbtns3, null);

        viewList.add(item_mainbtns1);
        viewList.add(item_mainbtns2);
        viewList.add(item_mainbtns3);

        // 设置Adapter
        srollbtn_viewpager.setAdapter(new MyAdapter());
        // 设置监听，主要是设置点点的背景
        srollbtn_viewpager.setOnPageChangeListener(IndexFragment.this);
        // 设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        srollbtn_viewpager.setCurrentItem(0);


        if (!pullToRefreshListView.isShown()) {
            pullToRefreshListView.setVisibility(View.VISIBLE);
        }
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

                    ((ViewPager) container).addView(view, position
                            % viewList.size());
                } catch (Exception e) {
                    // handler something
                }
                return view;
            }
            return null;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % viewList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        LogUtils.v("IndexFragment","onAttach");
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        LogUtils.v("IndexFragment","onDetach");
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.area_text:
                intent = new Intent(this.getActivity(), AreaActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.search_ll:
                intent = new Intent(this.getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.message_image:
                intent = new Intent(this.getActivity(), MessageActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }


}
