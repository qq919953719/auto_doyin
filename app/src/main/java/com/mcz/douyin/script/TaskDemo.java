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
    private AutoDataBean autoDataBean;
    private AutoFollowDataBean autoFollowDataBean;


    /****************************************测试脚本***************************************************************************/

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startAutoParenting(AutoDataBean bean) {
        this.autoDataBean = bean;
        //私信
        sendMessage(bean);
        /*************************/

        jumpApp("com.ss.android.ugc.aweme");
        printLogMsg("抖音打开成功");
        timeSleep(waitSixSecond);
        while (true) {
            for (int num = 0; num < 1000000; num++) {

                try {
                    timeSleep(waitSixSecond);
                    TaskItemDemo itemDemo = new TaskItemDemo();
                    itemDemo.startAutoParenting(autoDataBean.getData().get(0));

                } catch (Exception e) {
                    home();
                    timeSleep(waitSixSecond);
                    TaskItemDemo itemDemo = new TaskItemDemo();
                    itemDemo.startAutoParenting(autoDataBean.getData().get(0));
                    throw new RuntimeException(e);
                }

//
//
//                viewVideo(1);
//                UiSelector uiSelector = new UiSelector();
//                printLogMsg("刷" + num + "个视频，点掉弹窗");
//                uiSelector.text("我知道了").findOne().click();
//                timeSleep(waitOneSecond);
//                uiSelector.text("关闭").findOne().click();
//                timeSleep(waitOneSecond);
//                uiSelector.text("以后再说").findOne().click();
//                timeSleep(waitOneSecond);
//                if (className("ImageView").desc("关闭").exists()) {
//                    uiSelector.className("ImageView").desc("关闭").findOne().click();
//                }
//                timeSleep(waitOneSecond);
////                if (uiSelector.className("TextView").text("上滑继续看视频").exists()) {
////                    swipe((int) (mWidth / 2), mHeight - 480, (int) (mWidth / 2) + 80, 200, 450);
////                    printLogMsg("当前为关注好友页面，划走");
////                    timeSleep(waitFiveSecond + new Random().nextInt(waitFiveSecond));
////                }
////                timeSleep(waitOneSecond);
//                //包含评论按钮，是视频
//                if (className("LinearLayout").descContains("未点赞").exists()) {
//                    printLogMsg("当前播放为短视频");
//                    int random = new Random().nextInt(100);
//                    if (random > 10) {
//                        if (className("LinearLayout").descContains("未选中").exists()) {
//                            className("LinearLayout").descContains("未选中").findOne().click();
////                            click(987, 1246);
//                            printLogMsg("点击收藏按钮成功");
//                            timeSleep(waitTwoSecond);
//                        }
//
//                    }
//
//                    if (random > 10) {
//                        if (className("LinearLayout").descContains("未点赞").exists()) {
//                            //首页点赞
//                            className("LinearLayout").descContains("未点赞").findOne().click();
//                            printLogMsg("点击点赞按钮成功");
//                        }
//                    }
//                    timeSleep(waitTwoSecond);
//
//                    if (random > 10) {
//                        if (className("LinearLayout").descContains("未选中").exists()) {
//                            className("LinearLayout").descContains("未选中").findOne().clickPoint();
//                            printLogMsg("点击收藏按钮成功");
//                        }
//
//                    }
//                    timeSleep(waitThreeSecond);
//
//                    if (random > 10) {
//                        //            //首页评论标签
//                        className("LinearLayout").descContains("评论").findOne().click();
//                        printLogMsg("点击评论按钮成功");
//                        timeSleep(waitTwoSecond);
//                        if (!className("TextView").textContains("条评论").exists()) {
//                            back();
//                            timeSleep(waitTwoSecond);
//                        }
//                        className("EditText").findOne().click();
//                        timeSleep(waitTwoSecond);
////                        className("EditText").findOne().setText("是你啊");
////                        printLogMsg("输入评论内容成功");
////                        timeSleep(waitTwoSecond);
////                        click(967, 1837);
////                        printLogMsg("坐标成功");
//
//                        int randomciclkNum = new Random().nextInt(6) + 2;
//                        for (int i = 0; i < randomciclkNum; i++) {
//                            int randomx = new Random().nextInt(1056) + 38;
//                            int randomy = new Random().nextInt(407) + 1280;
//                            click(randomx, randomy);
//                            printLogMsg("点击" + i + "次键盘");
//
//                        }
//                        click(989, 1831);
//                        printLogMsg("输入评论内容成功");
//                        timeSleep(waitTwoSecond);
//                        click(963, 777);
//                        printLogMsg("点击评论按钮成功");
//                        timeSleep(waitTwoSecond);
////                        className("EditText").findOne().setText("是你啊");
////
////                        timeSleep(waitTwoSecond);
////                        className("TextView").text("发送").findOne().clickPoint();
////                        printLogMsg("点击发布评论按钮成功");
////                        timeSleep(waitTwoSecond);
////
////                        className("EditText").findOne(0).clickPoint();
////                        timeSleep(waitThreeSecond);
////                        printLogMsg("点击评论框按钮成功");
////                        timeSleep(waitTwoSecond);
//////                        className("android.widget.EditText").findOne().setText("是你啊");
//////                        className("EditText").findOne(0).setText("是你啊");
//////                        printLogMsg("输入评论内容成功");
////                        int randomciclkNum = new Random().nextInt(6) + 2;
////                        for (int i = 0; i < randomciclkNum; i++) {
////                            int randomx = new Random().nextInt(1056) + 38;
////                            int randomy = new Random().nextInt(407) + 1280;
////                            click(randomx, randomy);
////                        }
////                        click(982, 1829);
////                        printLogMsg("输入评论内容成功");
////                        timeSleep(waitTwoSecond);
////                        back();
////                        printLogMsg("返回");
////                        timeSleep(waitTwoSecond);
////                        className("TextView").text("发送").findOne().clickPoint();
////                        printLogMsg("点击发布评论按钮成功");
////                        timeSleep(waitTwoSecond);
////                        back();
////                        printLogMsg("返回");
////                        timeSleep(waitTwoSecond);
////                        className("LinearLayout").descContains("评论").findOne().click();
////                        printLogMsg("点击评论按钮成功");
////                        timeSleep(waitTwoSecond);
////                        className("TextView").text("发送").findOne().clickPoint();
////                        printLogMsg("点击发布评论按钮成功");
//
//                        timeSleep(waitThreeSecond);
////                        click(967, 773);
////                        printLogMsg("点击发布评论按钮成功");
//                        back();
//                        printLogMsg("关闭评论列表成功");
//                        timeSleep(waitThreeSecond);
//
//                        swipe((int) (mWidth / 2), mHeight - 480, (int) (mWidth / 2) + 80, 200, 450);
//                        timeSleep(waitFiveSecond + new Random().nextInt(waitFiveSecond));
//                    }
//

//                } else {
//                    printLogMsg("当前播放为直播");
//
//                    swipe((int) (mWidth / 2), mHeight - 480, (int) (mWidth / 2) + 80, 200, 450);
//                    timeSleep(waitFiveSecond + new Random().nextInt(waitFiveSecond));
//                }


            }
            break;

        }

        printLogMsg("脚本之心完毕");

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sendMessage(AutoDataBean bean) {
        //私信
        jumpApp("com.ss.android.ugc.aweme");
        printLogMsg("抖音打开成功");
        timeSleep(waitSixSecond);
        printLogMsg("开始执行私信操作");
        for (AutoDataBean.DataDTO doyinBean : bean.getData()) {
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
                if (className("TextView").desc("首页，按钮").exists()) {
                    className("TextView").desc("首页，按钮").findOne().click();
                    printLogMsg("点击首页按钮成功");
                    break;
                }
                if (className("ImageView").desc("关闭").exists()) {
                    className("ImageView").desc("关闭").findOne().click();
                }
                loopTime++;
                if (loopTime > 20) {
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
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }


            timeSleep(waitTwoSecond);
            className("android.widget.EditText").findOne().setText(doyinBean.getDyName());
            printLogMsg("搜索抖音号：" + doyinBean.getDyName() + "成功");
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


//            while (true){
            if (className("TextView").text("关注").exists()) {
//                className("TextView").text("关注").findOne().click();
                id("qym").className("TextView").findOne().click();
                printLogMsg("点击关注按钮成功");
            }
//            }


            timeSleep(waitSixSecond);
            loopTime = 0;
            while (true) {
                if (className("TextView").desc("私信").exists()) {
                    className("TextView").desc("私信").findOne().click();
                    printLogMsg("点击私信按钮成功");
                    break;
                }
                loopTime++;
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

            className("android.widget.EditText").findOne().setText(doyinBean.getComment());
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


            for (int i = 0; i < 5; i++) {
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
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void start_run(AutoDataBean bean) {
        this.autoDataBean = bean;
        first();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void start_run_follow(AutoFollowDataBean bean) {
        this.autoFollowDataBean = bean;
        first_follow();
    }


    //

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void first_follow() {
        jumpApp("com.ss.android.ugc.aweme");
        timeSleep(waitSixSecond);
        //切换账号
        Random rand = new Random();
        int randomNumber1 = rand.nextInt(2);
        Log.e("切换账号随机数", String.valueOf(randomNumber1));
        if (randomNumber1 == 0) {
            //切换账号
            //changeAccount();
        }
        timeSleep(waitTwoSecond);
        //点击首页
//        click(110, 2274);
        while (true) {
            if (className("TextView").desc("首页，按钮").exists()) {
                className("TextView").desc("首页，按钮").findOne().click();
                break;
            }
        }
        timeSleep(waitThreeSecond);
//        printLogMsg("获取数据:"+autoFollowDataBean.getData().getFollowName());
//        String follow_name = autoFollowDataBean.getData().getFollowName();
        String follow_name = "蜘蛛馍";
        while (true) {
            if (className("Button").desc("搜索").exists()) {
                className("Button").desc("搜索").findOne().click();
                break;
            }
        }
        timeSleep(waitTwoSecond);
        //输入
        className("android.widget.EditText").findOne().setText(follow_name);
        timeSleep(waitTwoSecond);
        //点击搜索
        className("TextView").text("搜索").findOne().clickPoint();
        timeSleep(10000);
        //点第一个视频
        click(343, 1246);

        followAndLetter();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void first() {
        //打开抖音
        jumpApp("com.ss.android.ugc.aweme");
        timeSleep(waitSixSecond);
        //切换账号
        Random rand = new Random();
        int randomNumber1 = rand.nextInt(2);
        Log.e("切换账号随机数", String.valueOf(randomNumber1));
        if (randomNumber1 == 0) {
            //切换账号
            changeAccount();
        }
        timeSleep(10000);
        //点击首页
        click(110, 1843);
        timeSleep(waitThreeSecond);
        printLogMsg("获取数据:" + autoDataBean.getData().size());
        for (int i = 0; i < autoDataBean.getData().size(); i++) {
            AutoDataBean.DataDTO bean = autoDataBean.getData().get(i);
            String nickName = bean.getDyName();
            String comment = bean.getComment();
            printLogMsg("获取数据:" + nickName);
            timeSleep(waitTwoSecond);
            int loopTime = 0;
            while (true) {
                if (className("Button").desc("搜索").exists()) {
                    className("Button").desc("搜索").findOne().click();
                    break;
                }
                loopTime++;
                if (loopTime > 20) {
                    printLogMsg("循环次数过多，已停止查询");
                    break;
                }
            }
            timeSleep(waitTwoSecond);
            //输入
            className("android.widget.EditText").findOne().setText(nickName);
            timeSleep(waitTwoSecond);
            //点击搜索
            className("TextView").text("搜索").findOne().clickPoint();
            timeSleep(waitSixSecond);
            //点第一个视频
            click(346, 1205);
            timeSleep(waitSixSecond);

            rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                clickZan();
                timeSleep(waitFourSecond);
                clickShou();
                timeSleep(waitFourSecond);
                clickComment("哈哈哈哈哈");
                timeSleep(waitSixSecond);
                clickzhuan();
            }
            if (randomNumber == 1) {
                clickComment("哈哈哈哈哈");
                timeSleep(waitSixSecond);
                clickShou();
                timeSleep(waitFourSecond);
                clickZan();
                timeSleep(waitFourSecond);
                clickzhuan();
            }
            if (randomNumber == 2) {
                clickZan();
                timeSleep(waitFourSecond);
                clickComment("哈哈哈哈哈");
                timeSleep(waitSixSecond);
                clickShou();
                timeSleep(waitFourSecond);
                clickzhuan();
            }

        }
        ActivityUtils.startActivity(ActivityUtils.getTopActivity(), FunctionActivity.class);

    }

    //点赞
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clickZan() {
        className("TextView").findOne(0).click();
    }

    //收藏
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clickShou() {
        className("TextView").findOne(2).click();
    }

    //评论
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clickComment(String comment) {
        //点击评论图标
        className("TextView").findOne(1).click();
        timeSleep(waitThreeSecond);
        if (className("ImageView").desc("关闭").exists()) {
            className("ImageView").desc("关闭").findOne().click();
        }
        //点击输入框
        className("EditText").findOne().click();
        timeSleep(waitTwoSecond);
        //输入
        printLogMsg(comment);
        className("EditText").findOne(0).setText(comment);
        timeSleep(waitTwoSecond);
        //发送
        click(950, 800);
        timeSleep(waitTwoSecond);
        //点击关闭评论列表
        click(150, 150);
    }

    //转发
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clickzhuan() {
        //点击转发图标
        className("TextView").findOne(3).click();
        timeSleep(waitTwoSecond);
        //点击转发
        className("TextView").text("转发").findOne().click();
        timeSleep(waitSixSecond);
        //点击设置
        className("ImageView").desc("设置").findOne().click();
        timeSleep(waitTwoSecond);
        //点击仅自己可见
        className("TextView").text("私密 · 仅自己可见").findOne().click();
        timeSleep(waitTwoSecond);
        //点击转发
        className("Button").text("转发到日常").findOne().click();
        timeSleep(waitTwoSecond);
    }

    //切换账号
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void changeAccount() {
        //点击我的
        click(979, 1839);
        timeSleep(waitSixSecond);
        if (className("ImageView").desc("关闭").exists()) {
            className("ImageView").desc("关闭").findOne().click();
        }
//        className("ImageView").descContains("切换账号").boundsInScreen().findOne().click();
        //  id("com.ss.android.ugc.aweme:id/hd=").findOne().click();
        descContains("切换账号").findOne().click();
        if (className("ImageView").desc("关闭").exists()) {
            className("ImageView").desc("关闭").findOne().click();
        }
        timeSleep(waitSixSecond);
        final int[] num = {0};
        className("RecyclerView").find().filter(new UiCollection.FilterCondition<AccessibilityNodeInfo>() {
            @Override
            public boolean shouldKeep(AccessibilityNodeInfo item) {
                num[0] = item.getChildCount() - 2;
                return false;
            }
        });
        int rand1 = new Random().nextInt(num[0]);
        printLogMsg("rand:" + rand1);
        if (rand1 == 0) {
            rand1 = num[0];
        }
        className("RecyclerView").find().filterOne(item -> true).child(rand1).click();
    }

    //评论加关注和私信
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void followAndLetter() {
        timeSleep(waitThreeSecond);
        //点击评论图标
//        className("TextView").findOne(1).click();
        click(1002, 1579);
        timeSleep(waitThreeSecond);
        if (className("ImageView").desc("关闭").exists()) {
            className("ImageView").desc("关闭").findOne().click();
        }

//        for (int index = 0; index < 5; index++) {
//            List<AccessibilityNodeInfo> recyclerView = findElementListByContainClassName("androidx.recyclerview.widget.RecyclerView");
//            if (recyclerView != null) {
//                AccessibilityNodeInfo info = recyclerView.get(0);
//                for (int i = 0; i < info.getChildCount(); i++) {
//                    AccessibilityNodeInfo child = info.getChild(i);
////                    printLogMsg("昵称：" + info.getChild(i).getText());
//                    AccUtils.clickNodeByPoint(child);
//                    className("ImageView").desc("返回").findOne().click();
//                    timeSleep(waitThreeSecond);
//                }
//                scrollUp();
//            }
//            scrollUp();
//        }

        className("RecyclerView").find().foreachPrint();
//        ActivityUtils.removeActivityLifecycleCallbacks();

//        findElementById()
        className("RecyclerView").find().filter(new UiCollection.FilterCondition<AccessibilityNodeInfo>() {
            @Override
            public boolean shouldKeep(AccessibilityNodeInfo item) {
                int num = item.getChildCount();
                for (int index = 0; index < num; index++) {

                    AccessibilityNodeInfo accessibilityNodeInfo = item.getChild(index);
                    AccessibilityNodeInfo mbean = accessibilityNodeInfo.getChild(0);

                    AccessibilityNodeInfo mveanq = mbean.getChild(0);

//                    AccessibilityNodeInfo te = mveanq.findAccessibilityNodeInfosByText();
//                    AccUtils.clickNodeByPoint(te);


//                    item.getChild(index).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    timeSleep(waitThreeSecond);
                    className("ImageView").desc("返回").findOne().click();

                }
                return false;
            }
        });

        scrollUp();

    }


    public void jumpApp(String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent it = packageManager.getLaunchIntentForPackage(packageName);
        ActivityUtils.getTopActivity().startActivity(it);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loopVideo(int index, int target) {
        if (index >= target) {
            return;
        }
        index++;
        printLogMsg("index => " + index);

        // 这里写循环的里面的业务逻辑
        className("ImageView").descMatches("评论(\\d+)，按钮").boundsInScreen().findOne().click();
        timeSleep(waitFiveSecond);

        click(mWidth / 2 + 180, 390);
        timeSleep(waitOneSecond);
        click(mWidth / 2 + 180, 390);
        timeSleep(waitOneSecond);
        click(mWidth / 2 + 180, 390);
        timeSleep(waitThreeSecond);

        scrollUp();
        timeSleep(waitSixSecond);

        loopVideo(index, target);
    }
}
