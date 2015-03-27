package com.brcorner.ddinaping.view.DianPingPullRefresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.brcorner.ddinaping.R;


public class EatProgressBar extends ProgressBar {
	/**
	 * 画笔对象
	 */
	private Paint paint;
	/**
	 * 当前进度
	 */
	private int progress;
	/**
	 * 最大进度
	 */
	private int max;
	/**
	 * 图片
	 */
	private Bitmap bitmap;

	public EatProgressBar(Context context) {
		this(context, null);
	}

	public EatProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public EatProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.dropdown_anim_00);
		paint = new Paint();
	}

	public synchronized int getMax() {
		return max;
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 画图片
		int company = max / 10;
		if (progress <= company) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_01);
		} else if (progress > company && progress <= company * 2) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_02);
		} else if (progress > company * 2 && progress <= company * 3) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_03);
		} else if (progress > company * 3 && progress <= company * 4) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_04);
		} else if (progress > company * 4 && progress <= company * 5) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_05);
		} else if (progress > company * 5 && progress <= company * 6) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_06);
		} else if (progress > company * 6 && progress <= company * 7) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_07);
		} else if (progress > company * 7 && progress <= company * 8) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_08);
		} else if (progress > company * 8 && progress <= company * 9) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_09);
		} else if (progress > company * 9 && progress <= max) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dropdown_anim_10);
		}
		canvas.drawBitmap(bitmap, getWidth() / 2 - bitmap.getWidth() / 2, 0,
				paint);
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {
		/**
		 * 设置控件大小
		 */
		setMeasuredDimension(bitmap.getWidth(), bitmap.getHeight());
	}

	/**
	 * 设置进度的最大值
	 * 
	 * @param max
	 */
	public synchronized void setMax(int max) {
		if (max < 0) {
			throw new IllegalArgumentException("max not less than 0");
		}
		this.max = max;
	}

	/**
	 * 获取进度线程同步
	 * 
	 * @return
	 */
	public synchronized int getProgress() {
		return progress;
	}

	/**
	 * 设置进度，此为线程安全控件，由于考虑多线的问题，线程同步 刷新界面调用postInvalidate()能在非UI线程刷新
	 * 
	 * @param progress
	 */
	public synchronized void setProgress(int progress) {
		if (progress < 0) {
			throw new IllegalArgumentException("progress not less than 0");
		}
		if (progress > max) {
			progress = max;
		}
		if (progress <= max) {
			this.progress = progress;
			postInvalidate();
		}

	}

}
