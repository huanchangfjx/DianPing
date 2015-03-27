package com.brcorner.ddinaping.application;

import android.app.Application;
import android.content.Context;

import com.brcorner.ddinaping.utils.DeviceParamsUtils;

/**
 * Created by Auser on 2015/3/9.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        new DeviceParamsUtils().init(this);
    }

    public static Context getContext()
    {
        return context;
    }

}
