package com.mcz.douyin.script;

import static com.mcz.douyin.config.GlobalVariableHolder.*;
import static com.mcz.douyin.node.AccUtils.*;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ClipboardUtils;
import com.blankj.utilcode.util.Utils;
import com.mcz.douyin.bean.AutoDataBean;
import com.mcz.douyin.bean.AutoFollowDataBean;
import com.mcz.douyin.node.AccUtils;
import com.mcz.douyin.node.UiCollection;
import com.mcz.douyin.node.UiSelector;
import com.mcz.douyin.script.dyService.TaskItemDemo;
import com.mcz.douyin.ui.FunctionActivity;

import java.util.List;
import java.util.Random;

public class TaskDemo extends UiSelector {
    public static boolean scriptGrowthStart = false;
    public static boolean scriptMessageStart = false;
    public static boolean scriptGrowthRunning = false;
    public static boolean scriptMessageRunning = false;
    private AutoDataBean autoDataBean;
    private AutoFollowDataBean autoFollowDataBean;

    public static int videoNum = 0;


    /****************************************测试脚本***************************************************************************/

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startAutoGrowthOn(AutoDataBean bean) {
        autoDataBean = bean;
        /*************************/
        timeSleep(waitThreeSecond);
        jumpApp("com.ss.android.ugc.aweme");
        printLogMsg("抖音打开成功");
        while (true) {
            if (!scriptGrowthStart) {
                scriptGrowthRunning = false;
                scriptGrowthStart = false;
                printLogMsg("已关闭脚本养号功能");
                break;
            }
            scriptGrowthRunning = true;
            try {
                timeSleep(waitSixSecond);
                TaskItemDemo itemDemo = new TaskItemDemo();
                itemDemo.startAutoParenting(autoDataBean.getData());
            } catch (Exception e) {
                backToDesktop();
                timeSleep(waitSixSecond);
                TaskItemDemo itemDemo = new TaskItemDemo();
                itemDemo.startAutoParenting(autoDataBean.getData());
                throw new RuntimeException(e);
            }

            scriptGrowthRunning = false;
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startAutoParenting(AutoDataBean bean) {
        this.autoDataBean = bean;

        if (scriptMessageStart) {
            //私信
            sendMessage(bean);
            printLogMsg("私信脚本执行完毕");
            scriptMessageRunning = false;
            return;
        } else {
            scriptMessageRunning = false;
            scriptMessageStart = false;
            printLogMsg("已关闭");
            return;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sendMessage(AutoDataBean bean) {
        timeSleep(waitThreeSecond);
        //私信
        jumpApp("com.ss.android.ugc.aweme");
        printLogMsg("抖音打开成功");
        timeSleep(waitSixSecond);
        printLogMsg("开始执行私信操作");
        for (AutoDataBean.DataDTO.DeviceDTO doyinBean : bean.getData().getTargetUserList()) {
            scriptMessageRunning = true;
            if (!scriptMessageStart) {
                printLogMsg("已关闭脚本");
                scriptMessageRunning = false;
                break;
            }
            if (className("Button").text("拒绝").exists()) {
                //关闭通讯录
                className("Button").text("拒绝").findOne().click();
                printLogMsg("关闭通讯录");
            }
            timeSleep(waitTwoSecond);
            if (className("ImageView").desc("关闭").exists()) {
                //关闭通讯录
                className("ImageView").desc("关闭").findOne().click();
                printLogMsg("关闭通讯录");
            }
            timeSleep(waitTwoSecond);
            int loopTime = 0;
            while (true) {
                if (className("ImageView").desc("关闭").exists()) {
                    className("ImageView").desc("关闭").findOne().click();
                }
                timeSleep(500);
                loopTime++;
                if (loopTime > 10) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }
            timeSleep(waitThreeSecond);
            loopTime = 0;

            while (true) {
                if (className("TextView").desc("搜索").exists()) {
                    className("TextView").desc("搜索").findOne().click();
                    printLogMsg("点击搜索按TextView钮成功");
                    break;
                }
                if (className("Button").desc("搜索").exists()) {
                    className("Button").desc("搜索").findOne().click();
                    printLogMsg("点击搜索按钮Button成功");
                    break;
                }
                timeSleep(500);
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }


            timeSleep(waitTwoSecond);
            className("android.widget.EditText").findOne().setText(doyinBean.getAccount());
            printLogMsg("搜索抖音号：" + doyinBean.getAccount() + "成功");
            timeSleep(waitTwoSecond);
            click(291, 241);
            printLogMsg("点击搜索结果第一个");
            timeSleep(waitTwoSecond);
            loopTime = 0;
            while (true) {
                if (className("Button").text("用户").exists()) {
                    className("Button").text("用户").findOne().click();
                    printLogMsg("点击用户按钮成功");
                    break;
                }
                timeSleep(500);
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }
            printLogMsg("点击用户分类成功");
            timeSleep(waitSixSecond);


//            className("RecyclerView").find().filter(new UiCollection.FilterCondition<AccessibilityNodeInfo>() {
//                @Override
//                public boolean shouldKeep(AccessibilityNodeInfo item) {
//                    AccessibilityNodeInfo accessibilityNodeInfo = item.getChild(0);
//                    AccUtils.clickNodeByPoint(accessibilityNodeInfo);
//                    printLogMsg("点击搜索到的第一个用户头像成功");
//                    return false;
//                }
//            });
            click(520, 370);
            printLogMsg("点击搜索到的第一个用户头像成功");
            timeSleep(waitSixSecond);

            loopTime = 0;
            while (true) {
                if (className("TextView").text("关注").exists()) {
//                className("TextView").text("关注").findOne().click();
                    id("q1y").className("TextView").findOne().click();
                    printLogMsg("点击关注按钮成功");
                    break;
                }
                timeSleep(500);
                loopTime++;
                if (loopTime > 10) {
                    back();
                    timeSleep(waitTwoSecond);
                    if (className("TextView").text("关注").exists()) {
                        id("q1y").className("TextView").findOne().click();
                        printLogMsg("点击关注按钮成功");
                    }
                    break;
                }
            }


            timeSleep(waitSixSecond);
            loopTime = 0;
            while (true) {
                if (className("TextView").desc("私信").exists()) {
                    className("TextView").desc("私信").findOne().click();
                    printLogMsg("点击私信按钮成功");
                    break;
                }
                loopTime++;
                timeSleep(500);
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }

            timeSleep(waitSixSecond);

            loopTime = 0;
            while (true) {

                if (className("ImageView").descContains("拍摄").exists()) {
                    break;
                }
                if (className("ImageView").descContains("会话").exists()) {
                    className("ImageView").descContains("会话").findOne().click();
                    printLogMsg("点击会话按钮成功");
                    break;
                }
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }


            timeSleep(waitTwoSecond);
            click(395, 1810);
            printLogMsg("点击评论按钮成功");
            timeSleep(waitTwoSecond);
            int sixinNum = new Random().nextInt(bean.getData().getMsgList().size() - 1);
            className("android.widget.EditText").findOne().setText(bean.getData().getMsgList().get(sixinNum).getMsg());
            printLogMsg("输入私信内容成功");
            timeSleep(waitTwoSecond);
            loopTime = 0;
            while (true) {
                if (className("ImageView").desc("发送").exists()) {
                    className("ImageView").desc("发送").findOne().click();
                    printLogMsg("点击发送按钮成功");
                    break;
                }
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }
            timeSleep(waitTwoSecond);


            for (int i = 0; i < 6; i++) {
                if (className("LinearLayout").desc("已送达").exists()) {
                    printLogMsg("私信发送成功");
                    break;
                }
                timeSleep(waitTwoSecond);
            }


            if (className("TextView").text("刚刚").exists()) {
                printLogMsg("私信发送成功");
            }
            timeSleep(waitTwoSecond);


            printLogMsg("执行完当前私信脚本，即将返回到APP主页");
            for (int i = 0; i < 5; i++) {
                printLogMsg("执行第" + i + "次返回");
                back();
                timeSleep(waitOneSecond);
                scriptMessageRunning = false;
            }
        }
        printLogMsg("脚本执行完毕");
        scriptMessageRunning = false;
    }


    //


    //转发


    public void jumpApp(String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent it = packageManager.getLaunchIntentForPackage(packageName);
        ActivityUtils.getTopActivity().startActivity(it);
    }


}
