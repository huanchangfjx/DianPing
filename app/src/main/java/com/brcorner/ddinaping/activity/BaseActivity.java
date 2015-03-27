package com.brcorner.ddinaping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.brcorner.ddinaping.utils.LogUtils;

/**
 * Created by Justin on 2015/3/9.
 */
public abstract class BaseActivity extends Activity {

    public abstract void initView();
    public abstract void changeView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //打印当前activity名称
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LogUtils.d("com.brcorner.ddinaping.activity.BaseActivity", getClass().getSimpleName());
    }

    public void doBack(View view)
    {
        this.finish();
    }
}
