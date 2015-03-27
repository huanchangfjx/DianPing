package com.brcorner.ddinaping.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.utils.CommonUtils;
import com.brcorner.ddinaping.utils.DeviceParamsUtils;
import com.brcorner.ddinaping.utils.ImageUtils;
import com.brcorner.ddinaping.view.DianPingDetail.StickyScrollView;

/**
 * 商品详情页面
 */
public class ItemActivity extends BaseActivity  implements StickyScrollView.OnScrollListener {

    /**
     * 自定义的MyScrollView
     */
    private StickyScrollView myScrollView;
    /**
     * 在MyScrollView里面的购买布局
     */
    private RelativeLayout mBuyLayout;
    /**
     * 位于顶部的购买布局
     */
    private RelativeLayout mTopBuyLayout;
    private ImageView head_imageview,item_content1,item_content2,item_content3;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_item);
        this.initView();
        myScrollView = (StickyScrollView) findViewById(R.id.scrollView);
        mBuyLayout = (RelativeLayout) findViewById(R.id.buy);
        mTopBuyLayout = (RelativeLayout) findViewById(R.id.top_buy_layout);

        myScrollView.setOnScrollListener(this);

        //当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(myScrollView.getScrollY());

                System.out.println(myScrollView.getScrollY());
            }
        });
    }




    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, mBuyLayout.getTop());
        mTopBuyLayout.layout(0, mBuyLayout2ParentTop, mTopBuyLayout.getWidth(), mBuyLayout2ParentTop + mTopBuyLayout.getHeight());
    }

    @Override
    public void initView() {
        head_imageview = (ImageView) findViewById(R.id.head_imageview);
        item_content1 = (ImageView) findViewById(R.id.item_content1);
        item_content2 = (ImageView) findViewById(R.id.item_content2);
        item_content3 = (ImageView) findViewById(R.id.item_content3);

        this.changeView();
    }

    @Override
    public void changeView() {
        Resources res = getResources();
        Bitmap headBitmap = BitmapFactory.decodeResource(res,R.drawable.item_img1);
        headBitmap = ImageUtils.zoomImgByWidth(headBitmap, DeviceParamsUtils.getScreenWidth());
        head_imageview.setImageBitmap(headBitmap);
        Bitmap contentBitmap = BitmapFactory.decodeResource(res,R.drawable.dianping_content);
        contentBitmap = ImageUtils.zoomImgByWidth(contentBitmap,DeviceParamsUtils.getScreenWidth());
        item_content1.setImageBitmap(contentBitmap);
        item_content2.setImageBitmap(contentBitmap);
        item_content3.setImageBitmap(contentBitmap);
    }
}
