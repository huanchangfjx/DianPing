package com.brcorner.ddinaping.view.DianPingPullRefresh;


import com.brcorner.ddinaping.R;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class LoadingLayout extends FrameLayout {

	static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;

//	private final ImageView headerImage;
//	private final ProgressBar headerProgress;
//	private final TextView headerText;
//	private final ImageView heaserRefreshImage;
//
//	private String pullLabel;
//	private String refreshingLabel;
//	private String releaseLabel;

    /**
     * 下拉刷新笑脸
     */
    private EatProgressBar progress;
    /**
     * 下拉完成动画图片
     */
    private ImageView load;
//    /**
//     * 下拉头的高度
//     */
//    private int hideHeaderHeight;

//	private final Animation rotateAnimation, resetRotateAnimation;
    private View header;
//    /**
//     * 下拉头的布局参数
//     */
//    private MarginLayoutParams headerLayoutParams;
    /**
     * 下拉头部回滚的速度
     */
    public static final int SCROLL_SPEED = -20;
    private boolean loadOnce = false;
	public LoadingLayout(Context context, final int mode, String releaseLabel, String pullLabel, String refreshingLabel) {
		super(context);
//        header = LayoutInflater.from(context).inflate(R.layout.section_pull_to_refresh_header,
//                null, true);
        if (!loadOnce) {
            header = LayoutInflater.from(context).inflate(R.layout.section_pull_to_refresh_header, this);
//		headerText = (TextView) header.findViewById(R.id.pull_to_refresh_text);
//		headerImage = (ImageView) header.findViewById(R.id.pull_to_refresh_image);
//		headerProgress = (ProgressBar) header.findViewById(R.id.pull_to_refresh_progress);
//		heaserRefreshImage=(ImageView) header.findViewById(R.id.pull_to_refreshing_image);
//        hideHeaderHeight = -header.getHeight();
            progress = (EatProgressBar) header.findViewById(R.id.progress);

            progress.setMax(1500);// 设置进度条最大值
//        headerLayoutParams = (MarginLayoutParams) header.getLayoutParams();
//        headerLayoutParams.topMargin = hideHeaderHeight;

            load = (ImageView) header.findViewById(R.id.load);
            AnimationDrawable aniDrawable = (AnimationDrawable) load.getDrawable();
            aniDrawable.start();
            loadOnce = true;
        }
//		final Interpolator interpolator = new LinearInterpolator();
//		rotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//		        0.5f);
//		rotateAnimation.setInterpolator(interpolator);
//		rotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
//		rotateAnimation.setFillAfter(true);
//
//		resetRotateAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f,
//		        Animation.RELATIVE_TO_SELF, 0.5f);
//		resetRotateAnimation.setInterpolator(interpolator);
//		resetRotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
//		resetRotateAnimation.setFillAfter(true);

//		this.releaseLabel = releaseLabel;
//		this.pullLabel = pullLabel;
//		this.refreshingLabel = refreshingLabel;

//		switch (mode) {
//			case PullToRefreshBase.MODE_PULL_UP_TO_REFRESH:
//				headerImage.setImageResource(R.drawable.pulltorefresh_up_arrow);
//				break;
//			case PullToRefreshBase.MODE_PULL_DOWN_TO_REFRESH:
//			default:
//				headerImage.setImageResource(R.drawable.pulltorefresh_down_arrow);
//				break;
//		}
	}

    public void doSetProgress(int intprogress)
    {
        progress.setProgress(intprogress);
    }

    /**
     * 复位状态
     */
	public void reset() {
//		headerText.setText(pullLabel);
//		headerImage.setVisibility(View.INVISIBLE);
//		heaserRefreshImage.clearAnimation();
//		heaserRefreshImage.setVisibility(View.GONE);
        progress.setVisibility(INVISIBLE);
        load.setVisibility(INVISIBLE);
	}

    /**
     * 释放刷新
     */
	public void releaseToRefresh() {
//		headerText.setText(releaseLabel);
//		headerImage.clearAnimation();
//		headerImage.startAnimation(rotateAnimation);
//		heaserRefreshImage.setVisibility(View.GONE);
        progress.setVisibility(VISIBLE);
        load.setVisibility(GONE);

    }

	public void setPullLabel(String pullLabel) {
//		this.pullLabel = pullLabel;
	}

    /**
     *正在刷新阶段
     */
	public void refreshing(){
//		headerText.setText(refreshingLabel);
//		headerImage.clearAnimation();
//		headerImage.setVisibility(View.INVISIBLE);
//		headerProgress.setVisibility(View.GONE);
//		Animation mRotateAnimation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//		mRotateAnimation.setInterpolator(new LinearInterpolator());
//		mRotateAnimation.setDuration(1200);
//		mRotateAnimation.setRepeatCount(Animation.INFINITE);
//		mRotateAnimation.setRepeatMode(Animation.RESTART);
//		heaserRefreshImage.setVisibility(View.VISIBLE);
//		heaserRefreshImage.startAnimation(mRotateAnimation);
        load.setVisibility(View.VISIBLE);
        progress.setVisibility(GONE);
	}

	public void setRefreshingLabel(String refreshingLabel) {
//		this.refreshingLabel = refreshingLabel;
	}

	public void setReleaseLabel(String releaseLabel) {
//		this.releaseLabel = releaseLabel;
	}

    /**
     * 下拉状态
     */
	public void pullToRefresh() {
//		headerText.setText(pullLabel);
//		heaserRefreshImage.setVisibility(View.GONE);
//		headerImage.clearAnimation();
//		headerImage.startAnimation(resetRotateAnimation);
        progress.setVisibility(VISIBLE);
        load.setVisibility(GONE);
    }

	public void setTextColor(int color) {
//		headerText.setTextColor(color);
	}







}
