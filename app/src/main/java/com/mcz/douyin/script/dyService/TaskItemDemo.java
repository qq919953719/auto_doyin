package com.mcz.douyin.script.dyService;

import static com.mcz.douyin.config.GlobalVariableHolder.context;
import static com.mcz.douyin.config.GlobalVariableHolder.mHeight;
import static com.mcz.douyin.config.GlobalVariableHolder.mWidth;
import static com.mcz.douyin.config.GlobalVariableHolder.waitFiveSecond;
import static com.mcz.douyin.config.GlobalVariableHolder.waitOneSecond;
import static com.mcz.douyin.config.GlobalVariableHolder.waitSixSecond;
import static com.mcz.douyin.config.GlobalVariableHolder.waitThreeSecond;
import static com.mcz.douyin.config.GlobalVariableHolder.waitTwoSecond;
import static com.mcz.douyin.node.AccUtils.back;
import static com.mcz.douyin.node.AccUtils.click;
import static com.mcz.douyin.node.AccUtils.home;
import static com.mcz.douyin.node.AccUtils.printLogMsg;
import static com.mcz.douyin.node.AccUtils.swipe;
import static com.mcz.douyin.node.AccUtils.timeSleep;
import static com.mcz.douyin.node.AccUtils.viewVideo;
import static com.mcz.douyin.script.TaskDemo.scriptStart;
import static com.mcz.douyin.script.TaskDemo.videoNum;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.ActivityUtils;
import com.mcz.douyin.bean.AutoDataBean;
import com.mcz.douyin.node.UiSelector;

import java.util.Random;

/**
 * @name auto_mcz
 * @class name：com.mcz.douyin.script.dyService *
 * @class describe *
 * @anthor chuangcui 邮箱:919953719@qq.com
 * @time 2023/10/11 00:08
 */

public class TaskItemDemo extends UiSelector {


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startAutoParenting(AutoDataBean.DataDTO bean) {
        //养号
        /*************************/
        videoNum++;
        jumpApp("com.ss.android.ugc.aweme");
        printLogMsg("抖音打开成功");
        timeSleep(waitSixSecond);
        printLogMsg("开始执行养号操作");
        printLogMsg("刷" + videoNum + "个视频，点掉弹窗");
        text("我知道了").findOne().click();
        timeSleep(waitOneSecond);
        text("关闭").findOne().click();
        timeSleep(waitOneSecond);
        text("以后再说").findOne().click();
        timeSleep(waitOneSecond);
        if (className("ImageView").desc("关闭").exists()) {
            className("ImageView").desc("关闭").findOne().click();
        }
        timeSleep(waitOneSecond);
//                if (uiSelector.className("TextView").text("上滑继续看视频").exists()) {
//                    swipe((int) (mWidth / 2), mHeight - 480, (int) (mWidth / 2) + 80, 200, 450);
//                    printLogMsg("当前为关注好友页面，划走");
//                    timeSleep(waitFiveSecond + new Random().nextInt(waitFiveSecond));
//                }
//                timeSleep(waitOneSecond);
        //包含评论按钮，是视频
        if (className("LinearLayout").descContains("未点赞").exists()) {
            printLogMsg("当前播放为短视频");
            int random = new Random().nextInt(100);
            if (random > 10) {
                if (className("LinearLayout").descContains("未选中").exists()) {
                    className("LinearLayout").descContains("未选中").findOne().click();
//                            click(987, 1246);
                    printLogMsg("点击收藏按钮成功");
                    timeSleep(waitTwoSecond);
                }

            }

            if (random > 10) {
                if (className("LinearLayout").descContains("未点赞").exists()) {
                    //首页点赞
                    className("LinearLayout").descContains("未点赞").findOne().click();
                    printLogMsg("点击点赞按钮成功");
                }
            }
            timeSleep(waitTwoSecond);

            if (random > 10) {
                if (className("LinearLayout").descContains("未选中").exists()) {
                    className("LinearLayout").descContains("未选中").findOne().clickPoint();
                    printLogMsg("点击收藏按钮成功");
                }

            }
            timeSleep(waitThreeSecond);

            if (random > 10) {
                //            //首页评论标签
                className("LinearLayout").descContains("评论").findOne().click();
                printLogMsg("点击评论按钮成功");
                timeSleep(waitTwoSecond);
                if (!className("TextView").textContains("条评论").exists()) {
                    back();
                    timeSleep(waitTwoSecond);
                }
                className("EditText").findOne().click();
                timeSleep(waitTwoSecond);
//                        className("EditText").findOne().setText("是你啊");
//                        printLogMsg("输入评论内容成功");
//                        timeSleep(waitTwoSecond);
//                        click(967, 1837);
//                        printLogMsg("坐标成功");

                int randomciclkNum = new Random().nextInt(6) + 2;
                for (int i = 0; i < randomciclkNum; i++) {
                    int randomx = new Random().nextInt(1056) + 38;
                    int randomy = new Random().nextInt(407) + 1280;
                    click(randomx, randomy);
                    printLogMsg("点击" + i + "次键盘");

                }
                click(989, 1831);
                printLogMsg("输入评论内容成功");
                timeSleep(waitTwoSecond);
                click(963, 777);
                printLogMsg("点击评论按钮成功");
                timeSleep(waitTwoSecond);
//                        className("EditText").findOne().setText("是你啊");
//
//                        timeSleep(waitTwoSecond);
//                        className("TextView").text("发送").findOne().clickPoint();
//                        printLogMsg("点击发布评论按钮成功");
//                        timeSleep(waitTwoSecond);
//
//                        className("EditText").findOne(0).clickPoint();
//                        timeSleep(waitThreeSecond);
//                        printLogMsg("点击评论框按钮成功");
//                        timeSleep(waitTwoSecond);
////                        className("android.widget.EditText").findOne().setText("是你啊");
////                        className("EditText").findOne(0).setText("是你啊");
////                        printLogMsg("输入评论内容成功");
//                        int randomciclkNum = new Random().nextInt(6) + 2;
//                        for (int i = 0; i < randomciclkNum; i++) {
//                            int randomx = new Random().nextInt(1056) + 38;
//                            int randomy = new Random().nextInt(407) + 1280;
//                            click(randomx, randomy);
//                        }
//                        click(982, 1829);
//                        printLogMsg("输入评论内容成功");
//                        timeSleep(waitTwoSecond);
//                        back();
//                        printLogMsg("返回");
//                        timeSleep(waitTwoSecond);
//                        className("TextView").text("发送").findOne().clickPoint();
//                        printLogMsg("点击发布评论按钮成功");
//                        timeSleep(waitTwoSecond);
//                        back();
//                        printLogMsg("返回");
//                        timeSleep(waitTwoSecond);
//                        className("LinearLayout").descContains("评论").findOne().click();
//                        printLogMsg("点击评论按钮成功");
//                        timeSleep(waitTwoSecond);
//                        className("TextView").text("发送").findOne().clickPoint();
//                        printLogMsg("点击发布评论按钮成功");

                timeSleep(waitThreeSecond);
//                        click(967, 773);
//                        printLogMsg("点击发布评论按钮成功");
                back();
                printLogMsg("关闭评论列表成功");
                timeSleep(waitThreeSecond);
                back();
                timeSleep(waitThreeSecond);
                printLogMsg("再次返回保底！");
                swipe((int) (mWidth / 2), mHeight - 480, (int) (mWidth / 2) + 80, 200, 450);
                timeSleep(waitFiveSecond + new Random().nextInt(waitFiveSecond));
            }


        } else {
            printLogMsg("当前播放为直播");

            swipe((int) (mWidth / 2), mHeight - 480, (int) (mWidth / 2) + 80, 200, 450);
            timeSleep(waitFiveSecond + new Random().nextInt(waitFiveSecond));
        }


        printLogMsg("脚本之心完毕");


    }

    public void jumpApp(String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent it = packageManager.getLaunchIntentForPackage(packageName);
        ActivityUtils.getTopActivity().startActivity(it);
    }

}
