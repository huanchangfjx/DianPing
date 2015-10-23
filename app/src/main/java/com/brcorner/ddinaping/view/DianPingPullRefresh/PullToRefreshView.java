package com.brcorner.ddinaping.view.DianPingPullRefresh;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.brcorner.ddinaping.R;


public class PullToRefreshView extends LinearLayout implements OnTouchListener {
	/**
	 * 下拉状态
	 */
	public static final int STATUS_PULL_TO_REFRESH = 0;

	/**
	 * 释放立即刷新状态
	 */
	public static final int STATUS_RELEASE_TO_REFRESH = 1;

	/**
	 * 正在刷新状态
	 */
	public static final int STATUS_REFRESHING = 2;

	/**
	 * 刷新完成或未刷新状态
	 */
	public static final int STATUS_REFRESH_FINISHED = 3;
	/**
	 * 当前处理什么状态，可选值有STATUS_PULL_TO_REFRESH, STATUS_RELEASE_TO_REFRESH,
	 * STATUS_REFRESHING 和 STATUS_REFRESH_FINISHED
	 */
	private int currentStatus = STATUS_REFRESH_FINISHED;;
	/**
	 * 记录上一次的状态是什么，避免进行重复操作
	 */
	private int lastStatus = currentStatus;
	/**
	 * 下拉头部回滚的速度
	 */
	public static final int SCROLL_SPEED = -80;
	/**
	 * 用于存储上次更新时间
	 */
	private SharedPreferences preferences;
	/**
	 * 下拉头的View
	 */
	private View header;
	/**
	 * 下拉刷新笑脸
	 */
	private EatProgressBar progress;
	/**
	 * 下拉完成动画图片
	 */
	private ImageView load;
	/**
	 * 在被判定为滚动之前用户手指可以移动的最大值。
	 */
	private int touchSlop;
	/**
	 * 是否已加载过一次layout，这里onLayout中的初始化只需加载一次
	 */
	private boolean loadOnce;
	/**
	 * 下拉头的布局参数
	 */
	private MarginLayoutParams headerLayoutParams;
	/**
	 * 下拉头的高度
	 */
	private int hideHeaderHeight;
	/**
	 * 需要去下拉刷新的listView
	 */
	private ListView listView;
	/**
	 * 当前是否可以下拉，只有ScrollView滚动到头的时候才允许下拉
	 */
	private boolean ableToPull;
	/**
	 * 手指按下时的屏幕纵坐标
	 */
	private float yDown;
	/**
	 * 上次更新时间的毫秒值
	 */
	private long lastUpdateTime;
	/**
	 * 为了防止不同界面的下拉刷新在上次更新时间上互相有冲突，使用id来做区分
	 */
	private int mId = -1;
	/**
	 * 一分钟的毫秒值，用于判断上次的更新时间
	 */
	public static final long ONE_MINUTE = 60 * 1000;

	/**
	 * 一小时的毫秒值，用于判断上次的更新时间
	 */
	public static final long ONE_HOUR = 60 * ONE_MINUTE;

	/**
	 * 一天的毫秒值，用于判断上次的更新时间
	 */
	public static final long ONE_DAY = 24 * ONE_HOUR;

	/**
	 * 一月的毫秒值，用于判断上次的更新时间
	 */
	public static final long ONE_MONTH = 30 * ONE_DAY;

	/**
	 * 一年的毫秒值，用于判断上次的更新时间
	 */
	public static final long ONE_YEAR = 12 * ONE_MONTH;

	/**
	 * 上次更新时间的字符串常量，用于作为SharedPreferences的键值
	 */
	private static final String UPDATED_AT = "updated_at";
	/**
	 * 下拉刷新的回调接口
	 */
	private PullToRefreshListener mListener;

	public PullToRefreshView(Context context) {
		super(context);
	}

	public PullToRefreshView(Context context, AttributeSet attrs) {
		super(context, attrs);

		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		header = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh,
				null, true);
		progress = (EatProgressBar) header.findViewById(R.id.progress);
		load = (ImageView) header.findViewById(R.id.load);
		AnimationDrawable aniDrawable = (AnimationDrawable) load.getDrawable();
		aniDrawable.start();
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		setOrientation(VERTICAL);
		addView(header, 0);
		listView = new ListView(context, attrs);
		addView(listView, 1);
		listView.setOnTouchListener(this);
	}


	/**
	 * 进行一些关键性的初始化操作，比如：将下拉头向上偏移进行隐藏，给ListView注册touch事件。
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed && !loadOnce) {
			hideHeaderHeight = -header.getHeight();
			progress.setMax(600);// 设置进度条最大值
			Log.i("hideHeaderHeight", hideHeaderHeight + "");
			headerLayoutParams = (MarginLayoutParams) header.getLayoutParams();
			headerLayoutParams.topMargin = hideHeaderHeight;
			loadOnce = true;
		}
	}

	public ListView getListView()
	{
		return listView;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		setIsAbleToPull(event);
		Log.v("ableToPull", String.valueOf(ableToPull));

		if (ableToPull) {
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					yDown = event.getRawY();
					break;
			}
		}
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		setIsAbleToPull(event);
		Log.v("ableToPull", String.valueOf(ableToPull));

		if (ableToPull) {
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					yDown = event.getRawY();

					Log.v("ACTION_DOWN", String.valueOf("yDown"));
					break;
				case MotionEvent.ACTION_MOVE:
					float yMove = event.getRawY();
					int distance = (int) (yMove - yDown);
					Log.v("yDown", String.valueOf(yDown));
					Log.v("distance", String.valueOf(distance));
					if (distance >= 0) {
						progress.setProgress(distance);
					} else {
						progress.setProgress(0);
					}
					// 如果手指是下滑状态，并且下拉头是完全隐藏的，就屏蔽下拉事件
					if (distance <= 0
							&& headerLayoutParams.topMargin <= hideHeaderHeight) {
						return false;
					}
					if (distance < touchSlop) {
						return false;
					}
					if (currentStatus != STATUS_REFRESHING) {
						if (headerLayoutParams.topMargin > 0) {
							currentStatus = STATUS_RELEASE_TO_REFRESH;
						} else {
							currentStatus = STATUS_PULL_TO_REFRESH;
						}
						// 通过偏移下拉头的topMargin值，来实现下拉效果
						headerLayoutParams.topMargin = (distance / 2)
								+ hideHeaderHeight;
						header.setLayoutParams(headerLayoutParams);
					}
					break;
				case MotionEvent.ACTION_UP:
				default:
					if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
						// 松手时如果是释放立即刷新状态，就去调用正在刷新的任务
						new RefreshingTask().execute();
					} else if (currentStatus == STATUS_PULL_TO_REFRESH) {
						// 松手时如果是下拉状态，就去调用隐藏下拉头的任务
						new HideHeaderTask().execute();
					}
					break;
			}
			// 时刻记得更新下拉头中的信息
			if (currentStatus == STATUS_PULL_TO_REFRESH
					|| currentStatus == STATUS_RELEASE_TO_REFRESH) {
				updateHeaderView();
				// 当前正处于下拉或释放状态，要让ScrollView失去焦点，否则被点击的那一项会一直处于选中状态
				listView.setPressed(false);
				listView.setFocusable(false);
				listView.setFocusableInTouchMode(false);
				lastStatus = currentStatus;
				// 当前正处于下拉或释放状态，通过返回true屏蔽掉ScrollView的滚动事件
				return true;
			}
		}
		return false;
	}

	/**
	 * 更新下拉头中的信息。
	 */
	private void updateHeaderView() {
		if (lastStatus != currentStatus) {
			if (currentStatus == STATUS_PULL_TO_REFRESH) {
				progress.setVisibility(VISIBLE);
				load.setVisibility(GONE);
			} else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
				progress.setVisibility(VISIBLE);
				load.setVisibility(GONE);
			} else if (currentStatus == STATUS_REFRESHING) {
				progress.setVisibility(GONE);
				load.setVisibility(VISIBLE);
			}
		}
	}






	/**
	 * 设置可以下拉
	 *
	 * @param event
	 */
	private void setIsAbleToPull(MotionEvent event) {

		// 是否滑动到顶部
		if (listView.getFirstVisiblePosition() == 0 && listView.getChildAt(0).getTop() >= 0) {
			if (!ableToPull) {
				yDown = event.getRawY();
			}
			// 如果首个元素的上边缘，距离父布局值为0，就说明ListView滚动到了最顶部，此时应该允许下拉刷新
			ableToPull = true;
		} else {
			if (headerLayoutParams.topMargin != hideHeaderHeight) {
				headerLayoutParams.topMargin = hideHeaderHeight;
				header.setLayoutParams(headerLayoutParams);
			}
			ableToPull = false;
		}
	}

	/**
	 * 正在刷新的任务，在此任务中会去回调注册进来的下拉刷新监听器。
	 *
	 * @author chenzhongzeng
	 */
	class RefreshingTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			int topMargin = headerLayoutParams.topMargin;
			while (true) {
				topMargin = topMargin + SCROLL_SPEED;
				if (topMargin <= 0) {
					topMargin = 0;
					break;
				}
				publishProgress(topMargin);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			currentStatus = STATUS_REFRESHING;
			publishProgress(0);
			if (mListener != null) {
				mListener.onRefresh();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... topMargin) {
			updateHeaderView();
			headerLayoutParams.topMargin = topMargin[0];
			header.setLayoutParams(headerLayoutParams);
		}

	}

	/**
	 * 隐藏下拉头的任务，当未进行下拉刷新或下拉刷新完成后，此任务将会使下拉头重新隐藏。
	 *
	 * @author guolin
	 */
	class HideHeaderTask extends AsyncTask<Void, Integer, Integer> {

		@Override
		protected Integer doInBackground(Void... params) {
			int topMargin = headerLayoutParams.topMargin;
			while (true) {
				hideHeaderHeight = - header.getHeight();
				topMargin = topMargin + SCROLL_SPEED;
				if (topMargin <= hideHeaderHeight) {
					topMargin = hideHeaderHeight;
					break;
				}
				publishProgress(topMargin);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return topMargin;
		}

		@Override
		protected void onProgressUpdate(Integer... topMargin) {
			headerLayoutParams.topMargin = topMargin[0];
			header.setLayoutParams(headerLayoutParams);
		}

		@Override
		protected void onPostExecute(Integer topMargin) {
			headerLayoutParams.topMargin = topMargin;
			header.setLayoutParams(headerLayoutParams);
			currentStatus = STATUS_REFRESH_FINISHED;
		}
	}

	/**
	 * 给下拉刷新控件注册一个监听器。
	 *
	 * @param listener
	 *            监听器的实现。
	 * @param id
	 *            为了防止不同界面的下拉刷新在上次更新时间上互相有冲突， 请不同界面在注册下拉刷新监听器时一定要传入不同的id。
	 */
	public void setOnRefreshListener(PullToRefreshListener listener, int id) {
		mListener = listener;
		mId = id;
	}

	/**
	 * 当所有的刷新逻辑完成后，记录调用一下，否则你的ListView将一直处于正在刷新状态。
	 */
	public void finishRefreshing() {
		currentStatus = STATUS_REFRESH_FINISHED;
		preferences.edit()
				.putLong(UPDATED_AT + mId, System.currentTimeMillis()).commit();
		new HideHeaderTask().execute();
	}

	/**
	 * 下拉刷新的监听器，使用下拉刷新的地方应该注册此监听器来获取刷新回调。
	 *
	 * @author guolin
	 */
	public interface PullToRefreshListener {

		/**
		 * 刷新时会去回调此方法，在方法内编写具体的刷新逻辑。注意此方法是在子线程中调用的， 你可以不必另开线程来进行耗时操作。
		 */
		void onRefresh();

	}


}
