package com.mcz.douyin.reporter;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.mcz.douyin.config.GlobalVariableHolder;


/**
 * Generated application for tinker life cycle
 */
public class SampleApplication extends Application {


    private static final String TAG = GlobalVariableHolder.tag;


    @Override
    protected void attachBaseContext(Context base) {
        Log.i(TAG, "attachBaseContext: xxx");
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: MyApp --> onCreate Method");
        GlobalVariableHolder.context = this;
    }

}