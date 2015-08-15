package com.nuro.application;

import android.app.Application;

import com.nuror1.NuroCore;

/**
 * Created by nuro on 8/15/15.
 */
public class NuroApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NuroApplication.application = this;
        NuroCore.init();
    }

    private static NuroApplication application;
    public static NuroApplication getGlobal() {
        return application;
    }
}
