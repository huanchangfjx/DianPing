package com.brcorner.ddinaping.view.DianPingSlidMenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.utils.CommonUtils;
import com.brcorner.ddinaping.utils.DeviceParamsUtils;
import com.nineoldandroids.view.ViewHelper;


public class SlidingMenu extends HorizontalScrollView
{
	/**
	 * 屏幕宽度
	 */
	private int mScreenWidth;
	/**
	 * dp
	 */
	private int mMenuLeftPadding;
	/**
	 * 菜单的宽度
	 */
	private int mMenuWidth;
	private int mHalfMenuWidth;

	private boolean isOpen;

	private boolean once;

	private ViewGroup mMenu;
	private ViewGroup mContent;

	public SlidingMenu(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);

	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		mScreenWidth = DeviceParamsUtils.getScreenWidth();

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			switch (attr)
			{
			case R.styleable.SlidingMenu_leftPadding:
				// 默认50
				mMenuLeftPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50f,
								getResources().getDisplayMetrics()));// 默认为10DP
				break;
			}
		}
		a.recycle();
	}

	public SlidingMenu(Context context)
	{
		this(context, null, 0);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		/**
		 * 显示的设置一个宽度
		 */
		if (!once)
		{
			MyLinearLayout wrapper = (MyLinearLayout) getChildAt(0);
			mMenu = (ViewGroup) wrapper.getChildAt(1);
			mContent = (ViewGroup) wrapper.getChildAt(0);

			mMenuWidth = mScreenWidth - mMenuLeftPadding;
			mHalfMenuWidth = mMenuWidth / 2;
			mMenu.getLayoutParams().width = mMenuWidth;
			mContent.getLayoutParams().width = mScreenWidth;

		}
//        if(isToggle)
//        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        }
//        else
//        {
//            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                    MeasureSpec.AT_MOST);
//            super.onMeasure(expandSpec, heightMeasureSpec);
//        }
//        isToggle = false;


	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		if (changed)
		{
			// 将菜单隐藏
			this.scrollTo(0, 0);
			once = true;
		}
	}

//    @Override
//
////    public boolean dispatchTouchEvent(MotionEvent ev) {
////
//////        //下面这句话是关键
//////
//////        if (ev.getAction()==MotionEvent.ACTION_MOVE) {
//////            return true;
//////        }
//////
//////        return super.dispatchTouchEvent(ev);//也有所不同哦
////
////    }

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
//        int action = ev.getAction();
//		switch (action)
//		{
//		// Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
//		case MotionEvent.ACTION_UP:
//			int scrollX = getScrollX();
//            int scrollY = getScrollY();
//			if (scrollX > scrollY)
//			{
//                return true;
//			}
//
//		}
		int action = ev.getAction();
		switch (action)
		{
		// Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			if (scrollX > mHalfMenuWidth)
			{
				this.smoothScrollTo(mMenuWidth, 0);//是把屏幕设置到这里
				isOpen = true;
			} else
			{
				this.smoothScrollTo(0, 0);
				isOpen = false;
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 打开菜单
	 */
	public void openMenu()
	{
		if (isOpen)
			return;
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen = true;
	}

	/**
	 * 关闭菜单
	 */
	public void closeMenu()
	{
		if (isOpen)
		{
			this.smoothScrollTo(0, 0);
			isOpen = false;
		}
	}

//    private boolean isToggle;
	/**
	 * 切换菜单状态
	 */
	public void toggle()
	{
//        isToggle = true;
		if (isOpen)
		{
			closeMenu();
		} else
		{
			openMenu();
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		//l初始值0
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l * 1.0f / mMenuWidth;
		float leftScale = 1 - 0.3f * scale;
//		float rightScale = 0.8f + scale * 0.2f;
//		
//		ViewHelper.setScaleX(mMenu, rightScale);
//		ViewHelper.setScaleY(mMenu, rightScale);
//		ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
		ViewHelper.setTranslationX(mMenu,-mMenuWidth *(1 - scale));

		ViewHelper.setPivotX(mContent, mContent.getWidth());//设置中心店
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, leftScale);//缩放
		ViewHelper.setScaleY(mContent, leftScale);

	}

}
