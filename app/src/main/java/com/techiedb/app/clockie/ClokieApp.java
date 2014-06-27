package com.techiedb.app.clockie;

import android.app.Application;
import android.content.res.Configuration;

/**
 * @author Larry Pham
 */
public class ClokieApp extends Application {
    public static final String TAG =Constant.APP_PREF + "App";

    public void onCreate(){

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
