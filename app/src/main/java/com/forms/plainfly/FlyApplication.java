package com.forms.plainfly;

import android.app.Application;

import com.forms.plainfly.utils.ScreenUtils;

/**
 * Created by forms on 2017/12/9.
 */

public class FlyApplication extends Application {
    public static  int screenHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        screenHeight= ScreenUtils.getScreenHeight(this);
    }
}
