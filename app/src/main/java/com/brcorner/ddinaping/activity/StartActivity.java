package com.brcorner.ddinaping.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.application.MyApplication;
import com.brcorner.ddinaping.utils.CommonUtils;
import com.brcorner.ddinaping.utils.DeviceParamsUtils;
import com.brcorner.ddinaping.utils.ImageUtils;

public class StartActivity extends BaseActivity {

    /**
     *开始界面
     */
    private AlphaAnimation start_anima;
    private ImageView adv_imageview;

    @Override
    public void initView() {
        adv_imageview = (ImageView) findViewById(R.id.adv_imageview);
        start_anima = new AlphaAnimation(0.0f, 1.0f);
        start_anima.setDuration(2000);
        changeView();
    }

    @Override
    public void changeView() {
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.ic_splash_screen);
        bitmap = ImageUtils.zoomImgByWidth(bitmap, DeviceParamsUtils.getScreenWidth());
        adv_imageview.setImageBitmap(bitmap);
        start_anima.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        adv_imageview.startAnimation(start_anima);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        this.initView();
    }

    /**
     * 跳转到首页
     */
    private void redirectTo() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
