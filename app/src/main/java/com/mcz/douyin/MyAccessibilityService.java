package com.mcz.douyin;

import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.RequiresApi;

import com.mcz.douyin.node.AccUtils;

public class MyAccessibilityService extends AccUtils {

    public MyAccessibilityService() {
    }

    /**
     * 监听事件的发生
     * @param accessibilityEvent
     */
    @Override
    @RequiresApi(24)
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

        // 刷新当前 Activity()
        super.refreshCurrentActivity(accessibilityEvent);
    }
}
