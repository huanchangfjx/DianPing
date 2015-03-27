package com.brcorner.ddinaping.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Auser on 2015/3/23.
 */
public class DeviceParamsUtils {
    private static int screenWidth;// 设备宽度
    private static int screenHeight;// 设备高度
    private static float screenDensity;// 设备像素点

    public void init(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        screenHeight = outMetrics.heightPixels;
        screenDensity = outMetrics.scaledDensity;
    }


    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static float getScreenDensity() {
        return screenDensity;
    }

}
